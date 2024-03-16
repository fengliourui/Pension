package com.example.module_login.Util;

import android.net.Uri;

public class Internet {
    public static String addURLParam(String url, String name, String value){
        url += (url.indexOf('?')==-1 ? '?' : '&');
        url += Uri.encode(name) + '=' + Uri.encode(value);
        return url;
    }

}
