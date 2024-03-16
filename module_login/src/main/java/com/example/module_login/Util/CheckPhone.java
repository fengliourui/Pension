package com.example.module_login.Util;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.EditText;

import com.example.module_login.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPhone {
    private static final String TAG = "CheckPhone";
    public static void checkPhone(EditText account, Context context) {
        String phoneNumber = account.getText().toString();
        if (!validatePhoneNumber(phoneNumber)) {// 验证手机号是否合法

            Drawable drawable = context.getResources().getDrawable(R.drawable.warning);//错误时要显示的图片
            drawable.setBounds(new Rect(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight()));
            //图片的大小，可调，四个参数为左上右下
            Log.i(TAG, "checkPhone 已绘制");
            account.setError("请输入正确的手机号码", drawable);//设置错误的时候不显示文本，只显示图片在android 4.4上的机必须要有类似err的文本才会显示
        }
    }

     public static boolean validatePhoneNumber(String number) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");//正则表达式
        Matcher m = p.matcher(number);
        return m.matches();
    }
}
