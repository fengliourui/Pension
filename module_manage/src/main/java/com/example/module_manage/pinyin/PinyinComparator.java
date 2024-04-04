package com.example.module_manage.pinyin;

import java.util.Comparator;

public class PinyinComparator implements Comparator<SortModel> {
    public int compare(SortModel o1, SortModel o2) {
        if (o1.getLetters().equals("@")                                       //@是干嘛的-------------------------------------
                || o2.getLetters().equals("#")) {
            return -1;
        } else if (o1.getLetters().equals("#")
                || o2.getLetters().equals("@")) {
            return 1;
        } else {
            return o1.getLetters().compareTo(o2.getLetters());//compareTo是实现Comparator的类override的函数，String已经重写
        }
    }

}
