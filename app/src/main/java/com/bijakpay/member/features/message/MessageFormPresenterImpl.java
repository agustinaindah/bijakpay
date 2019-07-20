package com.bijakpay.member.features.message;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public class MessageFormPresenterImpl implements MessageFormPresenter {

    private View mView;

    public MessageFormPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void postFormMessage(final JsonObject jsonRequest) {
        boolean cancel = mView.validate();
        if (!cancel){
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.postFormMessage(jsonRequest);
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
                    mView.successMessageForm();
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
