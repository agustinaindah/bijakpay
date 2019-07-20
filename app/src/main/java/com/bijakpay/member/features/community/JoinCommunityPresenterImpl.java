package com.bijakpay.member.features.community;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public class JoinCommunityPresenterImpl implements JoinCommunityPresenter {

    private View mView;

    public JoinCommunityPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void postJoin(final JsonObject jsonRequest) {
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.postJoinComm(jsonRequest);
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
