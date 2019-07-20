package com.bijakpay.member.features.profile.bank;

import android.widget.ArrayAdapter;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.Bank;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 21/05/2017.
 */

public class BankAccountPresenterImpl implements BankAccountPresenter {

    private View mView;
    private int mBankId;

    public BankAccountPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getBankAccount() {
       BijakApps.getInstance().service(new ServiceInterface() {
           @Override
           public Call<BaseResponse> callBackResponse(ApiService apiService) {
               return apiService.getBankAccount();
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
                    ArrayList<Bank> banks = new ArrayList<Bank>();
                    for (JsonElement element : jsonRes){
                        Bank bank = Helper.getGsonInstance().fromJson(element, Bank.class);
                        banks.add(bank);
                    }
                    mView.successPrimary(banks);

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

   /* @Override
    public void deleteBank(int id) {
        mBankId = id;
        api(Consts.DELETE);
    }

    private void api(final int apiType){
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                if (apiType == Consts.GET){
                    return apiService.getBankAccount();
                } else if (apiType == Consts.DELETE){
                    return apiService.deleteBankAccount(mBankId);
                }
                return null;
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

                    if (apiType == Consts.GET){
                        JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                        ArrayList<Bank> banks = new ArrayList<Bank>();
                        for (JsonElement element : jsonRes){
                            Bank bank = Helper.getGsonInstance().fromJson(element, Bank.class);
                            banks.add(bank);
                        }
                        mView.successPrimary(banks);
                    } else if (apiType == Consts.DELETE){
                        mView.showSucces();
                    } else {
                        throw new Exception("api type not defined");
                    }

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
    }*/
}
