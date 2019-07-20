package com.bijakpay.member.features.personal_report;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.ItemCashback;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public class TransCashbackReportPresenterImpl implements TransCashbackReportPresenter {

    private View mView;

    public TransCashbackReportPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getTransCashback(final int page) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getReportCasback(page);
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
                        ArrayList<ItemCashback> itemCashbacks = new ArrayList<ItemCashback>();
                        for (JsonElement element : jsonRes){
                            ItemCashback itemCashback =
                                    Helper.getGsonInstance().fromJson(element, ItemCashback.class);
                            itemCashbacks.add(itemCashback);
                        }
                        mView.successListTransCashback(itemCashbacks, page);
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
