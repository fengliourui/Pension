package Ui.Show.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Base.main.Service.Data.Health.AllData;
import com.example.Base.main.Service.Data.Health.ShowData;
import com.example.Business.dependents.R;

public class OlderHeathAdapter extends RecyclerView.Adapter<OlderHeathAdapter.OlderHeathHolder> {
    Context mContext;
    AllData allData;
    ShowData showData;


    public OlderHeathAdapter(Context context, AllData allData, ShowData showData) {
        this.mContext = context;
        this.allData = allData;
        this.showData = showData;
    }

    @NonNull
    @Override
    public OlderHeathAdapter.OlderHeathHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        return new OlderHeathHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OlderHeathAdapter.OlderHeathHolder holder, int position) {
        String name = allData.getData().get(position).getFieldName();
        String id = allData.getData().get(position).getFieldValue();
        holder.textView1.setText(name);
        if (!showData.getData().getValueByFieldName(id).equals("")) {
            holder.textView2.setText(showData.getData().getValueByFieldName(id));
        } else {
            holder.textView2.setText("暂无数据");
        }
        if (showData.getData().getValueByAvg(id)!= null) {
            holder.textView3.setText(showData.getData().getValueByAvg(id));
        } else {
            holder.textView3.setText("暂无数据");
        }

    }

    @Override
    public int getItemCount() {
        return allData.getData().size();
    }

    public class OlderHeathHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;

        public OlderHeathHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }
}
