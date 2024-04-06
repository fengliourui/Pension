package UI.Show.four;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Base.main.Service.Chat.ChatNetwork;
import com.example.Base.main.Service.Chat.Data.message;
import com.example.Business.elders.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import UI.Adapter.ChatAdapter;

public class Fragment4  extends Fragment {
    private ProgressDialog progressDialog;
    ExecutorService executorService;
    ChatNetwork chatNetwork = new ChatNetwork();
    RecyclerView recyclerView;

    EditText editTextl;
    TextView textView;
    View view;
    ChatAdapter chatAdapter;
    List<message> messages = new ArrayList<>();
    int times=1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_chat1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initData();
        initListener();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatAdapter =new ChatAdapter(messages,getContext());
        recyclerView.setAdapter(chatAdapter);
        showProgressDialog(getContext(), "正在加载");
    }

    public void init()
    {
     recyclerView = view.findViewById(R.id.chatrecycleview);
     editTextl = view.findViewById(R.id.chatEdit);
     textView = view.findViewById(R.id.chattext);
    }
    private void initData() {
        // 使用线程池执行耗时操作
        executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                String s = chatNetwork.addAndCall("做一下自我介绍" );
                if (isAdded()) { // 检查Fragment是否仍然与Activity关联
                    getActivity().runOnUiThread(() -> {
                        messages.add(new message(2, removeBeforeFirstNewLine(s)));
                        chatAdapter.notifyDataSetChanged();
                        dismissProgressDialog();
                        //关闭加载框
                    });
                }
            } catch (InterruptedException e) {
                // 这里可以添加更合适的异常处理，比如更新UI提示用户错误信息
                e.printStackTrace();
            }
        });
    }
    public String removeBeforeFirstNewLine(String input) {
        int index = input.indexOf("\n\n");
        if (index >= 0) {
            return input.substring(index + 2);
        }
        return input;
    }
    private void initListener() {
        textView.setOnClickListener(v -> {
            String msg = editTextl.getText().toString();
            if (!msg.isEmpty()) {
                // 清空输入框
                editTextl.setText("");
                // 立即将用户消息添加到列表中并更新RecyclerView
                addMessageAndUpdate(new message(times++, msg));
                // 在后台线程中处理网络请求
                executorService.execute(() -> {
                    // 发送消息并等待响应
                    String answer = null;
                    try {
                        answer = chatNetwork.addAndCall(msg);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (answer != null && !answer.isEmpty()) {
                        // 收到回复后更新RecyclerView
                        addMessageAndUpdate(new message(times++, answer));
                    }
                });
            }
        });
    }
    private void addMessageAndUpdate(message message) {
        getActivity().runOnUiThread(() -> {
            messages.add(message);
            chatAdapter.notifyItemInserted(messages.size() - 1);
            scrollToBottom(); // 滚动到新消息位置
        });
    }

    // 滚动RecyclerView到底部的方法
    private void scrollToBottom() {
        if (chatAdapter.getItemCount() > 0) {
            recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
        }
    }
    public void showProgressDialog(Context mContext, String text) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(text);	//设置内容
        progressDialog.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        progressDialog.show();

    }
    public Boolean dismissProgressDialog() {
        if (progressDialog != null){
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                return true;//取消成功
            }
        }
        return false;//已经取消过了，不需要取消
    }
}
