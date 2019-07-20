package com.bijakpay.member.features.download;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 30/08/2017.
 */

public class DownloadPresenterImpl implements DownloadPresenter{

    private View mView;

    public DownloadPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getDownloadMateri() {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getMaterial();
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
                String data = Helper.getGsonInstance().toJson(response.body().getData());
                JsonObject jsonRes = Helper.parseToJsonObject(data).get("results").getAsJsonArray()
                        .getAsJsonObject();

                mView.showMaterial(jsonRes);
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
