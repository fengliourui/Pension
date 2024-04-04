    package UI.Adapter;

    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;
    import com.example.Base.main.Service.Data.Activity.ActivityDTO;
    import com.example.Base.main.Service.Data.Activity.Activtyall;
    import com.example.Business.elders.R;


    import java.util.List;

    import UI.Show.Two.Ativitydetail;


    public class ActivtyAdapter extends RecyclerView.Adapter<ActivtyAdapter.VideoViewHolder>{
       private Activtyall activtyall; // 表示活动数据

       private List<ActivityDTO> data;
        private Context mContext;
       private String imageurl;

        public ActivtyAdapter(Activtyall activtyall1, Context context) {
            this.activtyall=activtyall1;
            data=activtyall1.getData();
            this.mContext=context;
        }
        @NonNull
        @Override
        public ActivtyAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
            return new ActivtyAdapter.VideoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
            ActivityDTO activityDTO =activtyall.getData().get(position);
            String max= String.valueOf(activityDTO.getMaxParticipants());
            String min= String.valueOf(activityDTO.getCurParticipants());

            holder.Endtextview.setText(max);     //最大人数
            holder.nameTextview.setText(activityDTO.getTitle());    //活动名称
            holder.newtextview.setText(min);    //目前人数
            if(activityDTO.getImages()==null)//    图片
            {
                imageurl=activityDTO.getImages().get(0).getUrl();
                Glide.with(mContext)
                        .load(imageurl)
                        .into(holder.touxiangimageview);
            }
            String time = activityDTO.getStartTime()+"-----"+activityDTO.getEndTime();
            holder.timeTextview.setText(time);//报名时间处理

            holder.itemView.setOnClickListener(new View.OnClickListener() {//点击打开详细页
                @Override
                public void onClick(View v) {
                    // 点击视频项时，跳转到新的页面
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, Ativitydetail.class);
                    intent.putExtra("id",activityDTO.getId());
                    intent.putExtra("imageurl",imageurl);
                    context.startActivity(intent);
                }
            });

            }

        @Override
        public int getItemCount() {
            return data.size();
        }

        static class VideoViewHolder extends RecyclerView.ViewHolder {
            ImageView touxiangimageview;//活动图片
            TextView nameTextview;//活动名称
            TextView timeTextview;//活动名称
            TextView newtextview;//活动目前人数
            TextView Endtextview;//活动最后人数
            public VideoViewHolder(@NonNull View itemView) {
                super(itemView);
                touxiangimageview=itemView.findViewById(R.id.item21);
                nameTextview=itemView.findViewById(R.id.item22);
                timeTextview=itemView.findViewById(R.id.item23);
                newtextview=itemView.findViewById(R.id.item24);
                Endtextview=itemView.findViewById(R.id.item25);
            }
        }
    }
