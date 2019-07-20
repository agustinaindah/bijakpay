package com.bijakpay.member.networks;

import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.Simple;
import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by agustinaindah on 26/04/2017.
 */

public interface ApiService {

    @POST("/login")
    Call<BaseResponse> postLogin(@Body JsonObject jsonRequest);

    @GET("/profile")
    Call<BaseResponse> getProfile();

    @PUT("/profile")
    Call<BaseResponse> updateProfile(@Body JsonObject jsonRequest);

    @POST("/register")
    Call<BaseResponse> postRegister(@Body JsonObject jsonRequest);

    @POST("/change_password")
    Call<BaseResponse> putPassword(@Body JsonObject jsonRequest);

    @PUT("/change_photo")
    Call<BaseResponse> updatePhoto(@Body JsonObject jsonRequest);

    @GET("/provinces")
    Call<BaseResponse> getProvinces();

    @GET("/cities")
    Call<BaseResponse> getCities(@Query("province_id") int provinceId);

    @GET("/subdistricts")
    Call<BaseResponse> getSubdistricts(@Query("city_id") int cityId);

    @POST("/recovery")
    Call<BaseResponse> postForgotPassword(@Body JsonObject jsonRequest);

    /*------------sys membership--------------*/
    @GET("sys_membership")
    Call<BaseResponse> getSysMembership();

    /*----------api address & address form-------*/

    @GET("address")
    Call<BaseResponse> getAddress();

    @POST("address")
    Call<BaseResponse> postAddress(@Body JsonObject jsonRequest);

    @PUT("address")
    Call<BaseResponse> updateAddress(@Body JsonObject jsonRequest);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "address", hasBody = true)
    Call<BaseResponse> deleteAddress(@Field("member_address_id") int id);

    /*----------api bank account & bank form--------*/

    @FormUrlEncoded
    @POST("bank_account_otp")
    Call<BaseResponse> postRequestOtp(@Field("member_mobile_phone_number") String phone);

    @FormUrlEncoded
    @PUT("bank_account_otp")
    Call<BaseResponse> putRequestOtp(
            @Field("member_mobile_phone_number") String phone,
            @Field("member_bank_account_id") int id
    );

    @GET("bank_account")
    Call<BaseResponse> getBankAccount();

    @POST("bank_account")
    Call<BaseResponse> postBankAccount(@Body JsonObject jsonRequest);

    @PUT("bank_account")
    Call<BaseResponse> updateBankAccount(@Body JsonObject jsonRequest);

    /*@FormUrlEncoded
    @HTTP(method = "DELETE", path = "bank_account", hasBody = true)
    Call<BaseResponse> deleteBankAccount(@Field("bank_account_id") int bankAccountId);*/

    /*-----api personal report------*/
    @GET("member/report/report_ppob")
    Call<BaseResponse> getReportPpob(@Query("pagenum") int page);

    @GET("member/report/report_cashback")
    Call<BaseResponse> getReportCasback(@Query("pagenum") int page);

    @GET("member/report/report_commission")
    Call<BaseResponse> getReportCommission(@Query("pagenum") int page);

    @GET("member/report/report_refund_cashback")
    Call<BaseResponse> getReportRefundCashback(@Query("pagenum") int page);

    /*-------api bussiness report-------*/

    @GET("member/report/report_trans_membership")
    Call<BaseResponse> getReportMembership(@Query("pagenum") int page);

    @GET("member/report/report_royalty")
    Call<BaseResponse> getReportRoyalty(@Query("pagenum") int page);

    @GET("member/report/report_refund_royalty")
    Call<BaseResponse> getReportRefundRoyalty(@Query("pagenum") int page);

    @GET("member/report/report_stockcard")
    Call<BaseResponse> getReportStockcard(@Query("pagenum") int page);

    /*-----------api register friend token & ewallet-------------*/

    @POST("register_friend")
    Call<BaseResponse> postRegToken(@Body JsonObject jsonRequest);

    @POST("register")
    Call<BaseResponse> postRegEwallet(@Body JsonObject jsonRequest);

    /*------------api upgrade member-------------------*/

    @PUT("upgrade")
    Call<BaseResponse> updateMember(@Body JsonObject jsonRequest);

    @PUT("upgrade_by_token")
    Call<BaseResponse> updateMemberToken(@Body JsonObject jsonRequest);

    /*-------------api message member-------------------*/

    @GET("member/message")
    Call<BaseResponse> getMessage(@Query("pagenum") int page);

    @GET("member/message/reply")
    Call<BaseResponse> getMessageDetail(@Query("message_id") int msgId);

    @POST("member/message/send_reply")
    Call<BaseResponse> postReplyMessage(@Body JsonObject jsonRequest);

    @POST("member/message/send")
    Call<BaseResponse> postFormMessage(@Body JsonObject jsonRequest);

    /*---------------My Point Rewards-------------------*/

    @GET("member/point_reward/history")
    Call<BaseResponse> getMyPoint(@Query("pagenum") int page);

    @GET("member/point_reward/saldo")
    Call<BaseResponse> getPointSaldo();

    @POST("redeem/bonus")
    Call<BaseResponse> postBonus(@Body JsonObject jsonRequest);

    /*---------------My Network-------------------*/

    @GET("member/network")
    Call<BaseResponse> getMyNetwork(@Query("pagenum") int page);

    @GET("member/channel")
    Call<BaseResponse> getMyChannel(@Query("pagenum") int page);

    /*---------------My Community-------------------*/

    @POST("create_community")
    Call<BaseResponse> postCreateComm(@Body JsonObject jsonRequest);

    @POST("join_community")
    Call<BaseResponse> postJoinComm(@Body JsonObject jsonRequest);

    @GET("community")
    Call<BaseResponse> getCommunity(@QueryMap Map<String, String> mapRequest);

    @GET("community/member")
    Call<BaseResponse> getCommMember(@QueryMap Map<String, String> mapRequest);

    /*---------------Material Download-------------------*/

    @GET("material")
    Call<BaseResponse> getMaterial();
}
