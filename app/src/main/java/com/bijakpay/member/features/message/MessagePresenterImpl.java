package com.bijakpay.member.features.message;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.ItemMessage;
import com.bijakpay.member.model.ItemMessageHeader;
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
 * Created by agustinaindah on 16/05/2017.
 */

public class MessagePresenterImpl implements MessagePresenter {

    private View mView;

    public MessagePresenterImpl(View mView) {
        this.mView = mView;
    }


    @Override
    public void getMessage(final int page) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getMessage(page);
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
                    ArrayList<ItemMessage> itemMessages = new ArrayList<ItemMessage>();
                    for (JsonElement element : jsonRes){
                        ItemMessage itemMessage = Helper.getGsonInstance()
                                .fromJson(element, ItemMessage.class);
                        itemMessages.add(itemMessage);
                    }
                    mView.successMessage(itemMessages,page);
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
