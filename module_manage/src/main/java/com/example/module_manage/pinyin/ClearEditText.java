package com.example.module_manage.pinyin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.example.module_manage.R;

//自定义的edittext，可以清除所有内容
public class ClearEditText extends androidx.appcompat.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearDrawable;

    public ClearEditText(Context context) {
        this(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context,attrs,android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mClearDrawable = getCompoundDrawables()[2];//获取右边的drawable对象
        if(mClearDrawable == null){
            mClearDrawable = getResources().getDrawable(R.drawable.emotionstore_progresscancelbtn);
        }
        //表示将drawable对象放在（0，0）的位置，并且大小为本身的大小
        mClearDrawable.setBounds(0,0,mClearDrawable.getIntrinsicWidth(),mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);//清除按钮一开始是不可见的
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(getCompoundDrawables()[2] != null){
            if(event.getAction() == MotionEvent.ACTION_UP){
                //计算触摸点是否在drawable的区域内
                boolean touchable = event.getX() > (getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if(touchable){
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }


    /**
     * 设置右侧清除图标的可见性
     * @param visible
     */
    protected void setClearIconVisible(boolean visible){
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],right,getCompoundDrawables()[3]);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setClearIconVisible(charSequence.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(hasFocus){
            setClearIconVisible(getText().length() > 0);
        }else{
            setClearIconVisible(false);
        }
    }

    public void setShakeAnimation(){
        this.setAnimation(shakeAnimation(5));
    }

    /**
     * 一个简单的水平摇晃动画，用于移动视图在一个水平方向上
     * @param counts 用于定义动画的摇晃次数
     * @return TranslateAnimation是Animation的子类，用于在使用上应用平移效果
     */
    public static Animation shakeAnimation(int counts){
        //四个参数分别代表动画开始和结束时的xy坐标
        //这里向右移动10像素，垂直方向不移动
        Animation translateAnimation = new TranslateAnimation(0,10,0,0);
        //`Interpolator` 用于定义动画的速度曲线。`CycleInterpolator` 使得动画在指定的次数内循环。
        translateAnimation.setInterpolator(new CycleInterpolator((counts)));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
