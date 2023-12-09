package com.payment.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class login {
    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private List<DataItem1> data;

    @SerializedName("message")
    private String message;

    // Getters and setters for the fields

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataItem1> getData() {
        return data;
    }

    public void setData(List<DataItem1> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataItem1 {
        @SerializedName("Id")
        private int id;

        @SerializedName("Email")
        private String email;


        @SerializedName("Password")
        private String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }
}



