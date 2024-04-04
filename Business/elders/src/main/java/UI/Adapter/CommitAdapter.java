package UI.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Base.main.Service.Data.add.CommentDto;
import com.example.Base.main.Service.Data.add.GetCommentld;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.MainActivity1;
import com.example.Business.elders.R;



import UI.Show.first.Fragmentvide;



public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.VideoViewHolder> implements ViewModelStoreOwner {
    private GetCommentld getCommentld; // 评论数据
    private Boolean a1 = false;//表示这个评论不是他发表的
    private Boolean a2 = false;//表示它没有点赞过这个评论
    private Context context;
    private  String  videold;//视频id
    private  int s1;
    OlderViewModel olderViewModel;

    public CommitAdapter( String videold,Context context, GetCommentld getCommentld) {
        this.context = context;
        this.getCommentld = getCommentld;
        this.videold=videold;
        olderViewModel = Fragmentvide.olderViewModel;
    }

    @NonNull
    @Override
    public CommitAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item3, parent, false);

        return new CommitAdapter.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder,int position1) {
        int color = Color.parseColor("#FF0000");
        int color1 = Color.parseColor("#00000000");

        int position = holder.getAdapterPosition();
        CommentDto commentld = getCommentld.getData().get(position);
        //判断删除按钮是否展示
        if (commentld.getIs_owner() == 0) {
            holder.deleteView.setVisibility(View.GONE);//如果不是它发表的评论则不显示
        }
        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 删除成功后更新 RecyclerView
                getCommentld.getData().remove(position);
                notifyItemRemoved(position);
                olderViewModel.Deletecommentld(MainActivity1.auth,videold,String.valueOf(commentld.getId()));
                Log.d("ttttt", MainActivity1.auth);
                Log.d("ttttt",videold);
                Log.d("ttttt",String.valueOf(commentld.getId()));
            }
        });
        //评论头像处理
        Glide.with(context)
                .load(commentld.getUrl()) // 加载指定URL的图片
                .placeholder(R.drawable.qth) // 设置占位图，可选
                .error(R.drawable.error) // 加载失败时显示的图片，可选
                .into(holder.imageView); // 将图片加载到指定的ImageView中
        //喜欢的初始状态
        if (commentld.getIs_like().equals("no")) {
         Fragmentvide.changeImageColor(holder.likeView, color1);
            a2 = false;
        } else {
            Fragmentvide.animateImageColorChange(holder.likeView, color1, color);

            a2 = true;
        }
        //喜欢的数量
        holder.likeText.setText(String.valueOf(commentld.getLikes()));
        //喜欢的点击效果
        holder.likeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a2 == false)//表示他现在是未选中状态
                {
                    olderViewModel.getCommentLike(videold,String.valueOf(commentld.getId()),"0", MainActivity1.auth);
                    Fragmentvide.animateImageColorChange(holder.likeView, color1, color);
                    holder.likeText.setText(String.valueOf(commentld.getLikes()+1));
                    a2 = !a2;
                } else {
                    olderViewModel.getCommentLike(videold,String.valueOf(commentld.getId()),"1", MainActivity1.auth);
                    holder.likeText.setText(String.valueOf(commentld.getLikes()-1));
                    Fragmentvide.changeImageColor(holder.likeView, color1);
                    a2 = !a2;
                }
            }
        });
        if (commentld.getNickname().isEmpty()) {
            holder.nameText.setText("该用户暂时还未取名");
        } else {
            holder.nameText.setText(commentld.getNickname());
        }
        holder.commectText.setText(commentld.getContent());
    }


    @Override
    public int getItemCount() {
        return getCommentld.getData().size();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return null;
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView commectText;//评论
        TextView nameText;//昵称
        TextView likeText;
        ImageView imageView;//头像
        ImageView deleteView;//删除
        ImageView likeView;//喜欢

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            commectText = itemView.findViewById(R.id.commectText2);
            nameText = itemView.findViewById(R.id.commectText1);
            likeText = itemView.findViewById(R.id.liketext);
            imageView = itemView.findViewById(R.id.toux2);
            deleteView = itemView.findViewById(R.id.shanchu);
            likeView = itemView.findViewById(R.id.dian);
        }
    }
}
