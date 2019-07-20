package com.bijakpay.member.features.register_friends;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 05/05/2017.
 */

public class RegisterFriendTokenPresenterImpl implements RegisterFriendTokenPresenter {

    private View mView;

    public RegisterFriendTokenPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void postRegToken(final JsonObject jsonRequest) {
        boolean cancel = mView.validate();
        if (cancel != true) {
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.postRegToken(jsonRequest);
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


}
