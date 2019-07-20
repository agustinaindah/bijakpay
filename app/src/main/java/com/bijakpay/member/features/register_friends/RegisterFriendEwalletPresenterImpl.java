package com.bijakpay.member.features.register_friends;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.HEAD;

/**
 * Created by agustinaindah on 08/05/2017.
 */

public class RegisterFriendEwalletPresenterImpl implements RegisterFriendEwalletPresenter {

    private View mView;

    public RegisterFriendEwalletPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void postRegEwallet(final JsonObject jsonRequest) {
        boolean cancel = mView.validate();
        if (cancel != true) {
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.postRegEwallet(jsonRequest);
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
                        JsonObject jsonRes = Helper.parseToJsonObject(data);
                        mView.success(jsonRes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void responseFailed(Response<BaseResponse> response) {
                    try {
                        JsonObject jsonRes = Helper.parseToJsonObject(response.errorBody().string());
                        mView.showMessage(jsonRes.get("msg").getAsString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable t) {
                    mView.notConnect(t.getLocalizedMessage());
                }
            });
        }
    }

    @Override
    public void getSysMember() {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getSysMembership();
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
                    JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                    ArrayList<SysMembership> sysMemberships = new ArrayList<SysMembership>();
                    for (JsonElement element : jsonRes){
                        SysMembership sysMembership = Helper.getGsonInstance().fromJson(element, SysMembership.class);
                        sysMemberships.add(sysMembership);
                    }
                    mView.showSysMember(sysMemberships);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void responseFailed(Response<BaseResponse> response) {
                try {
                    JsonObject jsonRes = Helper.parseToJsonObject(response.errorBody().string());
                    mView.showMessage(jsonRes.get("msg").getAsString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable t) {
                mView.notConnect(t.getLocalizedMessage());
            }
        });
    }

}
