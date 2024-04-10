package com.example.module_manage.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 将中文转化为拼音的工具类
 * 提供汉字转拼音的方法和获取首字母的方法
 */
public class PinyinUtils {
    /**
     * 将一个字符串中的中文字符转换为对应的汉语拼音
     * @param inputString
     * @return
     */
    public static String getPingYin(String inputString){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();//pinyin4j.jar里的工具
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//设置拼音为小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//设置不带声调
        format.setVCharType(HanyuPinyinVCharType.WITH_V);//使用v代替'ü'

        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for (char curChar : input) {
                if (Character.toString(curChar).matches("[\\u4E00-\\u9FA5]+")) { //首先检查是否为中文字符，中文字符的unicode范围在[\u4E00-\u9FA5]之间
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curChar, format); //即对于多音字，默认取第一个拼音
                    output += temp[0];
                } else
                    output += Character.toString(curChar);//如果不是汉字则直接加到结果中
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * 获取第一个字的拼音首字母
     * @param chinese
     * @return
     */
    public static String getFirstSpell(String chinese) {
        StringBuffer pinYinBF = new StringBuffer();
        char[] arr = chinese.toCharArray();

        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);//小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不包含声调

        for (char curChar : arr) {              //这个函数到底是干嘛的？？？？ 我服了-------------------------------------
            if (curChar > 128) {
                //转换中文字符为拼音
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curChar, defaultFormat);
                    if (temp != null) {
                        pinYinBF.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                //非中文字符
                pinYinBF.append(curChar);
            }
        }
        return pinYinBF.toString().replaceAll("\\W", "").trim();
    }
}
