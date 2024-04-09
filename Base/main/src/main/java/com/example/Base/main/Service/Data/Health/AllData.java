package com.example.Base.main.Service.Data.Health;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllData {

    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private List<DataDTO> data;
    @SerializedName("message")
    private String message;

    public AllData(String code, List<DataDTO> data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataDTO> getData() {
        return data;
    }
    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {
        @SerializedName("FieldName")
        private String fieldName;
        @SerializedName("FieldValue")
        private String fieldValue;

        public DataDTO(String fieldName, String fieldValue) {
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }
    }
}
