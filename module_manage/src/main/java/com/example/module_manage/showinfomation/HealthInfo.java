package com.example.module_manage.showinfomation;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;


public class HealthInfo {

    @SerializedName("338051")
    private String $338051;
    @SerializedName("422085")
    private String $422085;
    @SerializedName("481612")
    private String $481612;
    @SerializedName("656425")
    private String $656425;
    @SerializedName("673263")
    private String $673263;
    @SerializedName("715035")
    private String $715035;
    @SerializedName("781058")
    private String $781058;
    @SerializedName("786248")
    private String $786248;
    @SerializedName("894678")
    private String $894678;
    @SerializedName("905756")
    private String $905756;
    @SerializedName("avg-338051")
    private String avg338051;
    @SerializedName("avg-422085")
    private String avg422085;
    @SerializedName("avg-481612")
    private String avg481612;
    @SerializedName("avg-656425")
    private String avg656425;
    @SerializedName("avg-673263")
    private String avg673263;
    @SerializedName("avg-715035")
    private String avg715035;
    @SerializedName("avg-781058")
    private String avg781058;
    @SerializedName("avg-786248")
    private String avg786248;
    @SerializedName("avg-894678")
    private String avg894678;
    @SerializedName("avg-905756")
    private String avg905756;
    @SerializedName("history-338051")
    private String history338051;
    @SerializedName("history-422085")
    private String history422085;
    @SerializedName("history-481612")
    private String history481612;
    @SerializedName("history-656425")
    private String history656425;
    @SerializedName("history-673263")
    private String history673263;
    @SerializedName("history-715035")
    private String history715035;
    @SerializedName("history-781058")
    private String history781058;
    @SerializedName("history-786248")
    private String history786248;
    @SerializedName("history-894678")
    private String history894678;
    @SerializedName("history-905756")
    private String history905756;
    @SerializedName("olderid")
    private String olderid;

    public String getValueByFieldName(String fieldName) {
        Field[] fields = this.getClass().getDeclaredFields(); // 获取当前类的所有字段,这里用到了反射
        for (Field field : fields) {
            // 检查字段上是否有@SerializedName注解
            if (field.isAnnotationPresent(SerializedName.class)) {
                SerializedName annotation = field.getAnnotation(SerializedName.class);
                // 检查注解的值是否与给定的变量名匹配
                if (annotation.value().equals(fieldName)) {
                    try {
                        field.setAccessible(true); // 确保私有字段可访问
                        return (String) field.get(this); // 获取字段值
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null; // 如果没有找到匹配的字段，返回null
    }

    public String getValueByAvg(String input) {
        String formattedName = "avg-" + input;
        return getValueByFieldName(formattedName);
    }

    public String getValueByHistory(String input) {
        String formattedName = "history-" + input;
        return getValueByFieldName(formattedName);
    }

}

