package UI.Show.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.Base.main.Service.Data.add.CommentDto;
import com.example.Base.main.Service.Data.add.Commentlikes;
import com.example.Base.main.Service.Data.add.Deletecommentld;
import com.example.Base.main.Service.Data.add.GetCommentld;
import com.example.Base.main.Service.Data.add.Numdeals;
import com.example.Base.main.Service.Data.add.VideoMessage;
import com.example.Base.main.Service.Data.add.publishCommentVo;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.MainActivity1;
import com.example.Business.elders.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import UI.Adapter.CommitAdapter;


public class Fragmentvide extends AppCompatActivity {

    private VideoView videoView;
    private TextView textView;

    private LinearLayout fullscreenButton;
    private LinearLayout controlsLayout;
    private TextView backtext;
    private ImageView backButton;
    private ImageView reviewView;
    private ImageView dotView;
    private ImageView collectionView;
    private String path;
    private String data;
    private String id;

    private Boolean a2 = false;
    private Boolean a3 = false;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private int t1 = 12;//评论数量
    private int t2 = 13;//点赞数量
    private int t3 = 14;//收藏数量
    private VideoMessage videoMessage;//获取点赞评论数量
    private GetCommentld getCommentld;//获取评论
    private Deletecommentld deletecommentld;//删除评论的返回结果
    public static OlderViewModel olderViewModel;
    public BottomSheetDialog bottomSheetDialog;//底层抽屉
    public View bottomSheetView;
    public EditText editText;
    public Button sendText;
    public   RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fragmentvide);
        path = getIntent().getStringExtra("videopath");
        data = getIntent().getStringExtra("videodata");
        id = getIntent().getStringExtra("id");

        //从服务器获取数据
        getCloudData();
        //获取实例
        initView();
        //设置点击事件，视图展示等
        initClick();
        //视频的基本处理，如暂停，快进等
        viDeoDeal();
    }


    private void getCloudData() {
        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        olderViewModel.getNewCommentNums(id, MainActivity1.auth);//获取点赞评论数量
        olderViewModel.getNewCommentNumsLiveData.observe(this, new Observer<VideoMessage>() {
            @Override
            public void onChanged(VideoMessage videoMessage1) {
                videoMessage=videoMessage1;
                //这里收到数据会比展示默认数据慢，所以这里也添加了数据的展示
                if(videoMessage1==null)
                {
                }
                else {
                    t1= Integer.parseInt(videoMessage1.getData().getComment_num());
                    t2= Integer.parseInt(videoMessage1.getData().getLike_num());
                    t3= Integer.parseInt(videoMessage1.getData().getCollect_num());
                    textView1.setText(String.valueOf(t1));
                    textView2.setText(String.valueOf(t2));
                    textView3.setText(String.valueOf(t3));
                    int color = Color.parseColor("#FF0000");//红色
                    int color1 = Color.parseColor("#00000000");//无色
                    int color2 = Color.parseColor("#f7c114");//黄色
                    if(videoMessage1.getData().getIs_like().equals("yes"))
                    {
                        changeImageColor(dotView, color);
                        a2=true;
                    }
                    else {
                        changeImageColor(dotView, color1);
                        a2=false;
                    }
                    if(videoMessage1.getData().getIs_collect().equals("yes"))
                    {
                        changeImageColor(collectionView, color2);
                        a3=true;
                    }
                    else
                    {
                        changeImageColor(collectionView, color1);
                        a3=false;
                    }

                }
            }
        });
        olderViewModel.getNewComments(MainActivity1.auth,id,"1");//获取评论
        //评论区的处理设置
        // 初始化 BottomSheetDialog
        bottomSheetDialog = new BottomSheetDialog(Fragmentvide.this);
        bottomSheetDialog.setContentView(R.layout.review);
        // 设置点击外部区域关闭
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        if (bottomSheetDialog != null && bottomSheetDialog.getWindow() != null) {
            bottomSheetView = bottomSheetDialog.findViewById(R.id.coordinatorLayout);
        }
        recyclerView = bottomSheetView.findViewById(R.id.thtttt);
        recyclerView.setLayoutManager(new GridLayoutManager(Fragmentvide.this, 1));
        olderViewModel.getCommentldLiveData.observe(this, new Observer<GetCommentld>() {
            @Override
            public void onChanged(GetCommentld getCommentld1) {
                CommitAdapter adapter = new CommitAdapter(id,Fragmentvide.this,getCommentld1);
                if (getCommentld1.getData() != null && !getCommentld1.getData().isEmpty()) {
                    CommentDto firstComment = getCommentld1.getData().get(0);
                } else {

                }
                recyclerView.setAdapter(adapter); // 设置适配器
                getCommentld=getCommentld1;
            }
        });
        olderViewModel.DeletecommentldLiveData.observe(this, new Observer<Deletecommentld>() {
            @Override
            public void onChanged(Deletecommentld deletecommentld1) {
                deletecommentld=deletecommentld1;
            }
        });
        olderViewModel.goodDealLiveData.observe(this, new Observer<Numdeals>() {
            @Override
            public void onChanged(Numdeals numdeals) {
                if (numdeals==null)
                {
                    Log.d("qqqqq","收藏网络申请出现问题");
                }
            }
        });
        olderViewModel.likeDealLiveData.observe(this, new Observer<Numdeals>() {
            @Override
            public void onChanged(Numdeals numdeals) {
                if (numdeals==null)
                {
                    Log.d("qqqqq","点赞网络申请出现问题");
                }
            }
        });
        olderViewModel.CommentlikesLiveData.observe(this, new Observer<Commentlikes>() {
            @Override
            public void onChanged(Commentlikes commentlikes) {
                if (commentlikes==null)
                {
                    Log.d("qqqqq","评论点赞网络申请出现问题");
                }
            }
        });
        olderViewModel.postCommentLiveData.observe(this, new Observer<publishCommentVo>() {
            @Override
            public void onChanged(publishCommentVo publishCommentVo) {
                if(publishCommentVo==null)
                {
                    Log.d("qqqqq","评论提交网络申请出现问题");
                }
            }
        });
    }

    private void viDeoDeal() {
        videoView.setVideoURI(Uri.parse(path));
//这里是视频控制器
// 创建并设置MediaController
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(null);
//        videoView.setMediaController(mediaController);
// 可选：监听视频准备完成，隐藏预览图
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 开始播放视频
                videoView.start();
                int videoWidth = mp.getVideoWidth(); // 获取视频的宽度
                int videoHeight = mp.getVideoHeight(); // 获取视频的高度

                if (videoWidth < videoHeight) {
                    fullscreenButton.setVisibility(View.GONE);
                }
//                videoView.pause(); // 暂停视频
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // 视频播放完成，重新播放视频
                videoView.seekTo(0);
                videoView.start();
                videoView.pause(); // 暂停视频
                // 监听视频播放开始事件
                videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            // 视频开始渲染，暂停视频
                            videoView.pause();
                            return true;
                        }
                        return false;
                    }
                });
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
    }

    private void initClick() {
        controlsLayout.setVisibility(View.GONE);
        textView.setText(data);
        textView1.setText(String.valueOf(t1));
        textView2.setText(String.valueOf(t2));
        textView3.setText(String.valueOf(t3));
        backtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fragmentvide.this, All.class);
                intent.putExtra("videopath", path);
                intent.putExtra("videodata", data);
                startActivity(intent);
            }
        });
        dotView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.parseColor("#FF0000");
                int color1 = Color.parseColor("#00000000");
                if (a2 == true)//表示他现在是在选中状态，点击变为不选中状态
                {
                    olderViewModel.likeDeal(id,"1", MainActivity1.auth);
                    changeImageColor(dotView, color1);
                    t2--;
                    textView2.setText(String.valueOf(t2));
                    a2 = !a2;
                } else {
                    olderViewModel.likeDeal(id,"0", MainActivity1.auth);
                    animateImageColorChange(dotView, color1, color);
                    t2++;
                    textView2.setText(String.valueOf(t2));
                    a2 = !a2;
                }
            }
        });
        collectionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.parseColor("#f7c114");
                int color1 = Color.parseColor("#00000000");
                if (a3 == true)//表示他现在是在选中状态，点击变为不选中状态
                {
                    olderViewModel.goodDeal(id, MainActivity1.auth);
                    changeImageColor(collectionView, color1);
                    t3--;
                    textView3.setText(String.valueOf(t3));
                    a3 = !a3;
                } else {
                    olderViewModel.goodDeal(id, MainActivity1.auth);
                    animateImageColorChange(collectionView, color1, color);
                    t3++;
                    textView3.setText(String.valueOf(t3));
                    a3 = !a3;
                }
            }
        });
        reviewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });
        sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                editText.setText("");
                if(s.isEmpty())
                {

                }
                else
                {
                    publishCommentVo s1= new publishCommentVo(s,id);
                    olderViewModel.postComment(s1, MainActivity1.auth);
                    olderViewModel.getNewComments(MainActivity1.auth,id,"1");//获取评论
                }
            }
        });
    }

    private void initView() {
        sendText= bottomSheetView.findViewById(R.id.try1);
        editText = bottomSheetView.findViewById(R.id.searchPlaceEdit);
        videoView = findViewById(R.id.video);
        textView = findViewById(R.id.videotext);
        backtext = findViewById(R.id.backtext);
        backButton = findViewById(R.id.backButton);
        fullscreenButton = findViewById(R.id.fullscreenButton);
        controlsLayout = findViewById(R.id.controlsLayout);
        reviewView = findViewById(R.id.pingl);
        dotView = findViewById(R.id.dianz);
        collectionView = findViewById(R.id.good);
        textView1 = findViewById(R.id.tqq1);
        textView2 = findViewById(R.id.tqq2);
        textView3 = findViewById(R.id.tqq3);
    }

    public static void changeImageColor(ImageView imageView, int color) {

        // 获取ImageView的Drawable
        Drawable drawable = imageView.getDrawable();
        if( drawable==null)
        {
            return;
        }
        // 设置颜色滤镜效果
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        // 重新设置改变后的Drawable
        imageView.setImageDrawable(drawable);
    }

    public static void animateImageColorChange(final ImageView imageView, final int startColor, final int endColor) {
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int animatedValue = (int) animator.getAnimatedValue();
                changeImageColor(imageView, animatedValue);
            }
        });
        // 改变大小的动画，先放大再缩小
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 1.5f, 1.0f);
        scaleXAnimator.setDuration(1000); // 总时长为1000ms

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 1.5f, 1.0f);
        scaleYAnimator.setDuration(1000);

        // 同步动画
        AnimatorSet finalAnimatorSet = new AnimatorSet();
        finalAnimatorSet.playTogether(colorAnimator, scaleXAnimator, scaleYAnimator);
        finalAnimatorSet.start();
    }

}