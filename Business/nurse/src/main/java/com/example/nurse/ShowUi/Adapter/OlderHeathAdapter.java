package com.example.nurse.ShowUi.Adapter;

import static com.example.Base.main.Service.Repository.getActivity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.Base.main.Service.Data.Health.AllData;
import com.example.nurse.R;
import com.example.nurse.ShowUi.SharedViewModel;
import com.example.nurse.ShowUi.Two.DetailData;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class OlderHeathAdapter extends RecyclerView.Adapter<OlderHeathAdapter.OlderHeathHolder> {
    Context mContext;
    AllData allData;
    String  authid;
    private static Map<String, String> healthDataMap = new HashMap<>();
    private static boolean isIdAdded = false;
    public static void addHealthData(String key, String value, String id) {
        healthDataMap.put(key, value);

        // 只在首次添加数据时添加"id"
        if (!isIdAdded) {
            healthDataMap.put("id", id);
            isIdAdded = true;
        }
    }

    public static String getFinalJsonString() {
        JSONObject jsonObject = new JSONObject(healthDataMap);
        return jsonObject.toString();
    }
    public OlderHeathAdapter(Context context, AllData allData,String id) {
        this.mContext = context;
        this.allData = allData;
        this.authid= id;
    }

    @NonNull
    @Override
    public OlderHeathHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        return new OlderHeathHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OlderHeathHolder holder, int position) {
        String name = allData.getData().get(position).getFieldName();
        String id = allData.getData().get(position).getFieldValue();
        holder.textView1.setText(name);
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 不需要在此方法中做任何处理
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 实时验证文本内容，确保在0到1000之间
                String inputText = s.toString().trim();
                if (!isValidInput(inputText)) {
                    holder.editText.setError("请输入0到1000之间的整数");
                } else {
                    holder. editText.setError(null); // 清除错误提示
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String title = String.valueOf(holder.editText.getText());
                addHealthData(id,title,authid);
                DetailData.viewModel.setReturnedData(getFinalJsonString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return allData.getData().size();
    }

    public class OlderHeathHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        EditText editText;

        public OlderHeathHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            editText = itemView.findViewById(R.id.textView2);
        }
    }
    private boolean isValidInput(String input) {
        try {
            int value = Integer.parseInt(input);
            return value >= 0 && value <= 1000;
        } catch (NumberFormatException e) {
            return false; // 非数字输入，视为无效
        }
    }
}
