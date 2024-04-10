package com.example.nurse.ShowUi.Three;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.Base.main.Service.Data.add.Binddat;
import com.example.Base.main.Service.Data.add.nameAndIdentifyId;
import com.example.Base.main.Service.Network;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.dependents.R;
import com.example.nurse.MainActivityTTT;


public class SumBmit extends AppCompatActivity {
   private EditText  post1;
   private EditText  post2;
   private Button post3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_bmit);
        // 检查是否之前设置了透明状态栏，如果是，则恢复默认状态
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        getWindow().setBackgroundDrawableResource(R.color.grue); // 设置状态栏背景
        post1 =findViewById(R.id.editTextName);
        // 限制第一个输入框最多只能输入6个字符
        post1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        post2 =findViewById(R.id.editTextID);
        post2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        post3 =findViewById(R.id.submitButton);
        post3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=post1.getText().toString();
                post1.setText("");
                String s2=post2.getText().toString();
                post2.setText("");
                OlderViewModel olderViewModel = new ViewModelProvider(SumBmit.this).get(OlderViewModel.class);
                nameAndIdentifyId ss= new nameAndIdentifyId(s2,s1);
                olderViewModel.PostOlerBind(ss, MainActivityTTT.auth);
                olderViewModel.PostOlderBindLiveData.observe(SumBmit.this, new Observer<Binddat>() {
                    @Override
                    public void onChanged(Binddat binddat) {
                        if(binddat==null)
                        {
                            Toast.makeText(SumBmit.this, Network.message,Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(SumBmit.this,binddat.getData(),Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
            }
        });
    }
}