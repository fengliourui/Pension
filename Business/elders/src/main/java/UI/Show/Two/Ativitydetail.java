package UI.Show.Two;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.Base.main.Service.Data.Activity.ActivityOne;
import com.example.Base.main.Service.Data.Activity.Postactivity;
import com.example.Base.main.Service.Network;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.MainActivity1;
import com.example.Business.elders.R;

public class Ativitydetail extends AppCompatActivity {

    private TextView name;//活动标题
    private TextView newpeople;//目前活动人数
    private TextView endpeople;//最大活动人数
    private TextView activityTime;//活动时间
    private TextView applyTime;//报名时间
    private TextView mainnew;//活动简介
    private Button apply ;//报名按钮
    private LinearLayout background ;//背景
    private  String id ;
    private  String url ;
    private OlderViewModel olderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativitydetail);
        id = getIntent().getStringExtra("id");
        url = getIntent().getStringExtra("url");
        olderViewModel  = new ViewModelProvider(this).get(OlderViewModel.class);
        getWindow().setStatusBarColor(Color.TRANSPARENT); // 透明状态栏
        if(url!=null)
        {
            ImageView imageView = new ImageView(this);
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            getWindow().setBackgroundDrawable(resource);
                        }
                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
        }
        getWindow().setBackgroundDrawableResource(R.drawable.img); // 设置状态栏背景
        initView();
        initClick();
        getMessage();
    }

    private void initClick() {
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                olderViewModel.postActivity(id, MainActivity1.auth);
            }
        });
        olderViewModel.postactivityLiveData.observe(this, new Observer<Postactivity>() {
            @Override
            public void onChanged(Postactivity postactivity) {
                if (postactivity != null) {
                    Toast.makeText(Ativitydetail.this,postactivity.getData(),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Ativitydetail.this, Network.message,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void initView() {
        name=findViewById(R.id.name);
        newpeople=findViewById(R.id.newpeople);
        endpeople= findViewById(R.id.endpeople);
        activityTime=findViewById(R.id.activityTime);
        applyTime=findViewById(R.id.applytime);
        mainnew=findViewById(R.id.mainnew);
        apply=findViewById(R.id.apply);
        background=findViewById(R.id.tool);
    }
    private void  getMessage() {
        olderViewModel.getOneActivity(id, MainActivity1.auth);
        olderViewModel.ActivityOneLiveDate.observe(this, new Observer<ActivityOne>() {
            @Override
            public void onChanged(ActivityOne activityOne) {
                name.setText(activityOne.getData().getTitle());
                int min = activityOne.getData().getCurParticipants();
                int max = activityOne.getData().getMaxParticipants();
                newpeople.setText(String.valueOf(min));
                endpeople.setText( String.valueOf(max));
                String activitytime=activityOne.getData().getApplyStart()+"---"+activityOne.getData().getApplyEnd();
                activityTime.setText(activitytime);
                String applytime=activityOne.getData().getStartTime()+"---"+activityOne.getData().getEndTime();
                applyTime.setText(applytime);
                mainnew.setText(activityOne.getData().getIntroduce());
                if(url!=null)
                {
                    Drawable drawable = Drawable.createFromPath(url);
                    background.setBackground(drawable);
                }
            }
        });
    }
}