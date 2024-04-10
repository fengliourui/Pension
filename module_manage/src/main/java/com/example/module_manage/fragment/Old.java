package com.example.module_manage.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.module_manage.Main;
import com.example.module_manage.R;
import com.example.module_manage.pinyin.ClearEditText;
import com.example.module_manage.pinyin.PinyinComparator;
import com.example.module_manage.pinyin.PinyinUtils;
import com.example.module_manage.pinyin.SideBar;
import com.example.module_manage.pinyin.SortAdapter;
import com.example.module_manage.pinyin.SortModel;
import com.example.module_manage.util.Internet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Old extends Fragment {

    private static final String TAG = "Old";
    private RecyclerView mRecyclerView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;
    LinearLayoutManager manager;//负责管理RecyclerVIew的布局和滚动

    private List<SortModel> SourceDateList;
    private PinyinComparator pinyinComparator;
    private final OkHttpClient client = new OkHttpClient();
    //                                    token 这里写死了 后面要改一下！！！
    String token = Main.token;
    String getURL;
    //public static int load_old = 0;
    List<Map<String,String>> oldersList = new ArrayList<>();
    //List<Map<String,String>> nursesList = new ArrayList<>();

    //这个方法在fragment被创建时调用，此时fragment的视图还没有被创建
    //在这个方法中应该初始化fragment所需的数据或执行其他非ui的操作
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //这个方法用于fragment创建和返回她的视图
    //但此时返回的view可能还没有被附加到activity上，因此不应该直接操作它
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_old, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oldersList.clear();
        //应该先请求，然后再通过initViews设置数据，设置的时候要把数据通过参数传进去
        getURL = Internet.addURLParam("https://beadhouse.81jcpd.cn/master/getall/nurse-older","auth",token);
        Request request = new Request.Builder()
                        .url(getURL)
                        .build();
        Log.i(TAG, "请求的URL是" + getURL);
        //if(load_old == 0){
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.i(TAG, "onFailure: 网络请求失败");
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    Log.i(TAG, "onResponse: 网络请求成功！");
                    String res = response.body().string();
                    Log.i(TAG, res);
                    //load_old = 1;
                    try {
                        JSONObject jsonObject = new JSONObject(res);
                        JSONObject dataObject = jsonObject.getJSONObject("data");
                        JSONArray oldersArray = dataObject.getJSONArray("olders");
                        Log.i(TAG, "onResponse: 执行1");
                        //将其转化为map
                        for (int i = 0; i < oldersArray.length(); i++) {
                            JSONObject older = oldersArray.getJSONObject(i);
                            Map<String,String> olderMap = new HashMap<>();
                            Log.i(TAG, "第"+i+"个数据"+older.getString("userId")+older.getString("username")+"\n");
                            olderMap.put("userId",older.getString("userId"));
                            olderMap.put("username",older.getString("username"));
                            oldersList.add(olderMap);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initViews(view,oldersList);
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
       // }

    }
    //初始化：对比较器的初始化、对数据排序、对recyclerview的初始化、设置监听
    private void initViews(View view,List<Map<String,String>> data){


        //初始化比较器
        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) view.findViewById(R.id.sideBar);
        dialog = (TextView) view.findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        //设置右侧SideBar触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }

            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //SourceDateList = filledData(getResources().getStringArray(R.array.date)); //--------------------------这里设置了数据，要重写
        SourceDateList = filledData(data);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        //RecyclerView设置manager
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        adapter = new SortAdapter(getContext(), SourceDateList);
        //定义adapter的时候，实现之前定义的点击监听器接口

        mRecyclerView.setAdapter(adapter);
        mClearEditText = (ClearEditText) view.findViewById(R.id.filter_edit);
        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //当输入框里面的值为空，更新为原理啊的列表，否则为过滤数据列表
                filterData(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //为recyclerview填充数据
    private List<SortModel> filledData(List<Map<String,String>> data) {
        List<SortModel> mSortList = new ArrayList<>();

        for(Map<String,String> map: data){
            SortModel sortModel = new SortModel();
            String name = map.get("username");
            sortModel.setName(name);
            String id = map.get("userId");
            sortModel.setId(id);
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(name);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            //正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setLetters(sortString.toUpperCase());
            } else {
                sortModel.setLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;
    }

    //根据输入框中的值来过滤数据并更新recyclerview
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                //检查名称是否包括filterStr，或者名称的拼音首字母是否以filterStr开头--不区分大小写
                if (name.indexOf(filterStr.toString()) != -1 ||
                        PinyinUtils.getFirstSpell(name).startsWith(filterStr.toString())
                        //不区分大小写
                        || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                        || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())
                ) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateList(filterDateList);
    }

}