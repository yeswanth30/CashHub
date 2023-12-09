package com.payment;

import com.payment.models.CardModel;
import com.payment.models.ResponseData;
import com.payment.models.bankk;
import com.payment.models.bankk;
import com.payment.models.login;
import com.payment.models.loginn;
import com.payment.models.payment;
import com.payment.models.signmodel;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http://192.168.29.108:3003/";
    @POST("signin")
    Call<signmodel> signup(@Body signmodel data);

    @POST("login")
    Call<login> login(@Body loginn data);

    @POST("addaccount")
    Call<bankk> banklink(@Body bankk data);

//        @GET("getaccdetail")
//    Call<ResponseData> getaccdetail();

    @GET("getaccdetail/{Id}")
    Call<ResponseData> getaccdetail(@Path("Id") int userId);

    @POST("addhistory")
    Call<payment>payment(@Body payment data);
}
