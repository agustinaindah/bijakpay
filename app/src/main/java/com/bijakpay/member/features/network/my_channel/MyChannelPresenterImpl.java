package com.bijakpay.member.features.network.my_channel;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.ItemMyChannel;
import com.bijakpay.member.model.ItemMyNetwork;
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
 * Created by agustinaindah on 24/08/2017.
 */

public class MyChannelPresenterImpl implements MyChannelPresenter {

    private View mView;

    public MyChannelPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getMyChannel(final int page) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getMyChannel(page);
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
                    JsonArray jsonRes = jsonData.get("data").getAsJsonArray();
                    ArrayList<ItemMyChannel> itemMyChannels = new ArrayList<ItemMyChannel>();
                    for (JsonElement element : jsonRes){
                        ItemMyChannel itemMyChannel =
                                Helper.getGsonInstance().fromJson(element, ItemMyChannel.class);
                        itemMyChannels.add(itemMyChannel);
                    }
                    mView.showListChannel(itemMyChannels, page);
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
