package com.bijakpay.member.features.network.my_network;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.ItemMembership;
import com.bijakpay.member.model.ItemMyNetwork;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 23/08/2017.
 */

public class MyUnilevelPresenterImpl implements MyUnilevelPresenter {

    private View mView;

    public MyUnilevelPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getMyNetwork(final int page) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getMyNetwork(page);
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
                    ArrayList<ItemMyNetwork> itemMyNetworks = new ArrayList<ItemMyNetwork>();
                    for (JsonElement element : jsonRes){
                        ItemMyNetwork itemMyNetwork =
                                Helper.getGsonInstance().fromJson(element, ItemMyNetwork.class);
                        itemMyNetworks.add(itemMyNetwork);
                    }
                    mView.showListUnilevel(itemMyNetworks, page);
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
