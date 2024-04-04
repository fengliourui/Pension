package com.example.module_manage.util;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.module_manage.R;
import com.example.module_manage.fragment.FirstPage;

import java.util.List;

/**
 * 这个adapter是用于首页展示活动的RecyclerView的
 * 这里的要改一下，先把基本框架搭起来再说吧
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyRecyclerViewAdapter";
    private Context context;


    private List<ActivityEvent> mData;

    public MyRecyclerViewAdapter(Context context,List<ActivityEvent> data){
        this.context = context;
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: 11111111");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: 222222222222");
        holder.mTextView.setText(mData.get(position).getIntroduce());
        //holder.mTextView.setBackground(mData.get(position).getImages().getId());
        //因为资源没有在本地，所以需要用glide，别忘了添加依赖和注解器
        String imageUrl = mData.get(position).getImages().get(0).getUrl();
        Log.i(TAG, "onBindViewHolder: 图片的介绍" + mData.get(position).getIntroduce());
        Log.i(TAG, "onBindViewHolder: 字符串的url" + imageUrl);
        Glide.with(context)
                .load(imageUrl)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View itemVIew){
            super(itemVIew);
            Log.i(TAG, "ViewHolder: 333333333333333333333333");
            mImageView = itemVIew.findViewById(R.id.imageview);
            mTextView = itemVIew.findViewById(R.id.textview);
        }
    }

}
