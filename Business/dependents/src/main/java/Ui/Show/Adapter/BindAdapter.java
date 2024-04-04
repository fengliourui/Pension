package Ui.Show.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.Base.main.Service.Data.add.OlderBind;
import com.example.Base.main.Service.Data.add.OlderDTO;
import com.example.Business.dependents.R;




public class BindAdapter extends RecyclerView.Adapter<BindAdapter.BindHolder> {

    OlderBind olderBind;
    Context context;
    public BindAdapter(Context context,OlderBind olderBind) {
        this.olderBind = olderBind;
        this.context = context;
    }

    @NonNull
    @Override
    public BindHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item22, parent, false);
        return new BindHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BindHolder holder, int position) {
        OlderDTO olderDTO = olderBind.getData().get(position);
        Glide.with(context)
                .load(olderDTO.getAvatarUrl()) // 加载指定URL的图片
                .placeholder(R.drawable.qth) // 设置占位图，可选
                .error(R.drawable.error) // 加载失败时显示的图片，可选
                .into(holder.imageView); // 将图片加载到指定的ImageView中
        holder.name.setText(olderDTO.getOlderName());
    }

    @Override
    public int getItemCount() {
        return olderBind.getData().size();
    }

    static class BindHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private   TextView  name;
        public BindHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.it1);
            name=itemView.findViewById(R.id.it2);
        }
    }
}
