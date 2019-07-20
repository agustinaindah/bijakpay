package com.bijakpay.member.features.community;

import android.content.SharedPreferences;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.ItemCommMember;
import com.bijakpay.member.model.ItemMembership;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Consts;
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
 * Created by agustinaindah on 29/08/2017.
 */

public class MyCommunityMemberPresenterImpl implements MyCommunityMemberPresenter {

    private View mView;

    public MyCommunityMemberPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getCommMember(final Map<String, String> queryRequest) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getCommMember(queryRequest);
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
                    ArrayList<ItemCommMember> itemCommMembers = new ArrayList<ItemCommMember>();
                    for (JsonElement element : jsonRes){
                        ItemCommMember itemCommMember =
                                Helper.getGsonInstance().fromJson(element, ItemCommMember.class);
                        itemCommMembers.add(itemCommMember);
                    }
                    mView.successListMember(itemCommMembers,
                            Integer.valueOf(queryRequest.get("start")));
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
