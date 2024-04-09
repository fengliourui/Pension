package UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.Business.elders.MainActivity1;
import com.example.Business.elders.R;


import com.example.Base.main.Service.Data.photo.Imageview;
import UI.Show.first.Fragmentvide;

public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.VideoViewHolder>{
    private Imageview imageview; // 假设有一个Video类来表示视频数据

    private Context context;
    public ImageAdapter(Context context,Imageview imageview ) {
        if(imageview==null)
        {
            Log.d("ttttt", String.valueOf(1234567890));
        }
        this.context = context;
        this. imageview =  imageview;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new VideoViewHolder(view);
    }
    String data;
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        //得到数据
      String url = imageview.getImage().getData().get(position).getUrl();
      if (imageview .getText().get(position)!=null)
      {
          data =imageview .getText().get(position).getData();
      }
      String id = imageview.getImage().getData().get(position).getId();

// 设置视频标题等信息
        if(data==null)
        {
            holder.videoText.setText("暂无文案");
        }
        else
        {
            holder.videoText.setText(data);
        }
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().frame(0))
                .load(url)
                .into(new ImageViewTarget<Drawable>(holder.imageView) {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        holder.imageView.setImageDrawable(resource);
                    }
                    @Override
                    protected void setResource(@Nullable Drawable resource) {
                    }
                });
      //为视频项设置点击事件监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击视频项时，跳转到新的页面
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, Fragmentvide.class);
//                 intent.putExtra("videodata", data);
                 intent.putExtra("videopath", url);
                 intent.putExtra("id",id);
                 intent.putExtra("anth", MainActivity1.auth);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageview.getImage().getData().size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView videoText;
        ImageView imageView;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoText = itemView.findViewById(R.id.videotext);
            imageView = itemView.findViewById(R.id.qwth);
        }
    }
}
