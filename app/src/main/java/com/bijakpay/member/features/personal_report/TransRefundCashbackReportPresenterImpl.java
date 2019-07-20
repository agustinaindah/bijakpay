package com.bijakpay.member.features.personal_report;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.ItemRefundCashback;
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

public class TransRefundCashbackReportPresenterImpl implements TransRefundCashbackReportPresenter {

    private View mView;

    public TransRefundCashbackReportPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getTransRefundCashback(final int page) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getReportRefundCashback(page);
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
                    ArrayList<ItemRefundCashback> itemRefundCashbacks = new ArrayList<ItemRefundCashback>();
                    for (JsonElement element : jsonRes){
                        ItemRefundCashback itemRefundCashback =
                                Helper.getGsonInstance().fromJson(element, ItemRefundCashback.class);
                        itemRefundCashbacks.add(itemRefundCashback);
                    }
                    mView.successListTransRefundCashback(itemRefundCashbacks, page);
                }catch (Exception e){
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
