package com.example.module_manage.pinyin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.module_manage.R;

public class SideBar extends View {
    // 触摸事件
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };
    private int choose = -1;
    private Paint paint = new Paint();

    private TextView mTextDialog;

    /**
     * 为SideBar设置显示字母的TextView
     * @param textDialog
     */
    public void setTextView(TextView textDialog) {
        this.mTextDialog = textDialog;
    }


    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / b.length;// 获取每一个字母的高度

        for (int i = 0; i < b.length; i++) {
            paint.setColor(Color.rgb(33, 65, 98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体样式为默认的粗体
            paint.setAntiAlias(true);//设置抗锯齿，这用于使图像和文本边缘更加平滑
            paint.setTextSize(30);
            if (i == choose) {// 选中的状态
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);//设置伪粗体效果，增加文本的视觉吸引力
            }
            // 使得字符在水平方向上居中，垂直方向上一次排列
            //并且根据是否被选中有不同的状态
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();//重置画笔状态，确保下一次绘制不会受到之前设置的影响
        }
    }

    /**
     * 用于处理触摸事件，用户可以通过话筒触摸来选择其中的字母
     * @param event
     * @return true表示这个view已经处理了触摸事件，不需要再向上传递
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction(); //触摸事件的类型（按下、触摸、抬起）

        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数，c为选中的字母索引

        switch (action) {
            //当用户的手指离开屏幕时
            case MotionEvent.ACTION_UP:
                setBackground(new ColorDrawable(0x00000000));//将背景设置为透明
                choose = -1;//表示没有选中的字母
                invalidate();// 请求重新绘制view

                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                setBackgroundResource(R.drawable.sidebar_background);
                if (oldChoose != c) {//检查当前触摸点对应的字母索引c是否与上一次的oldChoose不同
                    if (c >= 0 && c < b.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(b[c]);//通知监听器当前选中的字母已经改变
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(b[c]);//更新文本为当前选中的字母
                            mTextDialog.setVisibility(View.VISIBLE);//并设置其为可见
                        }

                        choose = c;//更新choose为当前的字母索引c
                        invalidate();//请求重新绘制view
                    }
                }

                break;
        }
        return true;
    }

    /**
     * 触摸事件
     * @param onTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**
     * @author coder
     *
     */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }
}
