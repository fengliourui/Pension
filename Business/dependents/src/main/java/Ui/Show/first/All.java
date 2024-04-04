package Ui.Show.first;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Business.dependents.R;


public class All extends AppCompatActivity {
    private LinearLayout controlsLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.all);
        String path = getIntent().getStringExtra("videopath");
        String data = getIntent().getStringExtra("videodata");

        textView = findViewById(R.id.backtext);
        textView.setText(data);
        VideoView videoView = findViewById(R.id.fullscreenVideo);
        videoView.setVideoURI(Uri.parse(path));
        videoView.start();

        controlsLayout = findViewById(R.id.controlsLayout);
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (videoView.isPlaying()) {
                    videoView.pause(); // 视频正在播放，暂停视频
                } else {
                    videoView.start(); // 视频已暂停，开始播放视频
                }
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float x = e.getX();
                float width = videoView.getWidth();

                if (x < width / 3) {
                    // 点击屏幕左侧区域，向前调整视频进度
                    int currentPosition = videoView.getCurrentPosition();
                    videoView.seekTo(currentPosition - 8000); // 向前调整2秒
                } else if (x > 2 * width / 3) {
                    // 点击屏幕右侧区域，向后调整视频进度
                    int currentPosition = videoView.getCurrentPosition();
                    videoView.seekTo(currentPosition + 8000); // 向后调整2秒
                } else {
                    // 点击屏幕中间区域，显示/隐藏控件
                    if (controlsLayout.getVisibility() == View.GONE) {
                        controlsLayout.setVisibility(View.VISIBLE);
                    } else {
                        controlsLayout.setVisibility(View.GONE);
                    }
                }

                return super.onSingleTapUp(e);
            }
        });

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        // 创建 MediaPlayer 对象
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.void_01);

// 设置循环播放
        mediaPlayer.setLooping(true);

// 准备完成时开始播放
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.pause();
            }
        });

// 在长按事件中设置播放速度
        videoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    float playbackSpeed = 2.0f; // 设置播放速度为两倍
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PlaybackParams params = mediaPlayer.getPlaybackParams();
                        params.setSpeed(playbackSpeed);
                        mediaPlayer.setPlaybackParams(params);
                    }
                }
                return true;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}