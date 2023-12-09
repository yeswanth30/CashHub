package com.payment.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {
    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private String code;

    @SerializedName("data")

    private ArrayList<UserData> data;
    @SerializedName("message")
    private String message;

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

    public ArrayList<UserData> getData() {
        return data;
    }

    public void setData(ArrayList<UserData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public class UserData {
        private int Id;
        @SerializedName("User_id")
        private int userId;
        @SerializedName("Account_number")
        private String accountNumber;
        @SerializedName("Bank_name")
        private String bankName;
        @SerializedName("IFSC_Code")
        private String ifscCode;
        @SerializedName("Account_type")
        private String accountType;
        @SerializedName("Balance")
        private String Balance;
        private String UPI;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getIfscCode() {
            return ifscCode;
        }

        public void setIfscCode(String ifscCode) {
            this.ifscCode = ifscCode;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getBalance() {
            return Balance;
        }

        public void setBalance(String balance) {
            Balance = balance;
        }

        public String getUPI() {
            return UPI;
        }

        public void setUPI(String UPI) {
            this.UPI = UPI;
        }
    }
}



