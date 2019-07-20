package com.bijakpay.member.features.profile.change_password;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 12/05/2017.
 */

public class ChangePasswordPresenterImpl implements ChangePasswordPresenter {
    private View mView;

    public ChangePasswordPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void changePassword(final JsonObject jsonRequest) {
        boolean cancel=mView.validate();
        if (!cancel) {
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.putPassword(jsonRequest);
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
                        JsonObject jsonRes= Helper.parseToJsonObject(data);
                        mView.success(jsonRes);
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
}
