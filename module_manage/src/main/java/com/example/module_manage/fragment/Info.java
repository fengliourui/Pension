package com.example.module_manage.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.module_manage.R;
import com.example.module_manage.addinfo.addActivity;
import com.example.module_manage.addinfo.addNurse;
import com.example.module_manage.addinfo.addOld;

public class Info extends Fragment {
    private static final String TAG = "Info";
    Button addOld;
    Button addNurse;
    Button addActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        addOld = view.findViewById(R.id.addOld);
        addNurse = view.findViewById(R.id.addNurse);
        addActivity = view.findViewById(R.id.addActivity);

        addOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动addOld activity
                Intent intent = new Intent(getActivity(),com.example.module_manage.addinfo.addOld.class);
                startActivity(intent);
            }
        });
        addNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动addNurse activity
                Intent intent = new Intent(getActivity(),com.example.module_manage.addinfo.addNurse.class);
                startActivity(intent);
            }
        });
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),com.example.module_manage.addinfo.addActivity.class);
                startActivity(intent);
            }
        });
    }
}