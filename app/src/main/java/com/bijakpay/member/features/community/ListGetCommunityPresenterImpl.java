package com.bijakpay.member.features.community;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.ItemGetCommunity;
import com.bijakpay.member.model.ItemMyChannel;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public class ListGetCommunityPresenterImpl implements ListGetCommunityPresenter {

    private View mView;

    public ListGetCommunityPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getCommunity(final Map<String, String> queryRequest) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getCommunity(queryRequest);
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
                    ArrayList<ItemGetCommunity> itemGetCommunities = new ArrayList<ItemGetCommunity>();
                    for (JsonElement element : jsonRes){
                        ItemGetCommunity itemGetCommunity =
                                Helper.getGsonInstance().fromJson(element, ItemGetCommunity.class);
                        itemGetCommunities.add(itemGetCommunity);
                    }
                    mView.showGetCommunity(itemGetCommunities,
                            Integer.valueOf(queryRequest.get("pagenum")));
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
