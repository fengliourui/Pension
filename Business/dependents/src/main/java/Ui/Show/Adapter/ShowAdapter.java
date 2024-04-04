package Ui.Show.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Business.dependents.R;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.Hollder> {
    @NonNull
    @Override
    public Hollder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        return new Hollder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hollder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Hollder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        public Hollder(@NonNull View itemView) {
            super(itemView);
            textView1= itemView.findViewById(R.id.leftTextView);
            textView2= itemView.findViewById(R.id.rightTextView);
        }
    }
}
