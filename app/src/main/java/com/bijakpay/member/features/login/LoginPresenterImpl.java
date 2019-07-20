package com.bijakpay.member.features.login;

import android.content.SharedPreferences;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 26/04/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private View mView;

    public LoginPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void login(final JsonObject jsonRequest) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.postLogin(jsonRequest);
            }

            @Override
            public void showProgress() {
                mView.showProgress();
            }

            @Override
            public void hideProgress() {
                mView.hideProgress();
            }

            @Override
            public void responseSuccess(Response<BaseResponse> response) {
                try {
                    String data = Helper.getGsonInstance().toJson(response.body().getData());
                    JsonObject jsonData = Helper.parseToJsonObject(data);
                    
                    saveDataToPref(jsonData);
                    
                    mView.successLogin(jsonData);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void responseFailed(Response<BaseResponse> response) {
                try {
                    JsonObject jsonRes = Helper.parseToJsonObject(response.errorBody().string());
                    mView.showMessage(jsonRes.get("Terjadi Kesalahan").getAsString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable t) {
                mView.showMessage(t.getLocalizedMessage());
            }
        });

    }

    private void saveDataToPref(JsonObject jsonData) {
        String token        = jsonData.get("token").getAsString();
        JsonObject user     = jsonData.get("user").getAsJsonObject();
        String email        = user.get("member_email").getAsString();
        String name         = user.get("member_name").getAsString();
        String photo        = user.get("member_photo").getAsString();
        String phone        = "0" + user.get("member_mobile_phone_number").getAsLong();
        String memberType   = user.get("member_type").getAsString();
        /*int saldo           = user.get("saldo_ewallet").getAsInt();
        int pointReward     = user.get("saldo_point").getAsInt();*/

        SharedPreferences.Editor editor = BijakApps
                .getInstance()
                .Prefs()
                .edit();

        editor.putString(Consts.TOKEN, token);
        editor.putString(Consts.EMAIL, email);
        editor.putString(Consts.NAME, name);
        editor.putString(Consts.PHONE, phone);
        editor.putString(Consts.PHOTO, photo);
        editor.putString(Consts.TYPE, memberType);
        /*editor.putInt(Consts.SALDO, saldo);
        editor.putInt(Consts.POINT, pointReward);*/
        editor.commit();
    }
}
