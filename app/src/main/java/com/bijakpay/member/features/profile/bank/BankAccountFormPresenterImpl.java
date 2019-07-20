package com.bijakpay.member.features.profile.bank;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 12/05/2017.
 */

public class BankAccountFormPresenterImpl implements BankAccountFormPresenter {

    private View mView;

    public BankAccountFormPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void requestCode(final int mType, final int id) {
        final String phone = BijakApps.getInstance().Prefs().getString(Consts.PHONE, "");
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                if (mType == Consts.ADD){
                    return apiService.postRequestOtp(phone);
                } else {
                    return apiService.putRequestOtp(phone, id);
                }
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
                    mView.successSendCode();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void responseFailed(Response<BaseResponse> response) {
                try {
                    String data = response.errorBody().string();
                    JsonObject jsonRes = Helper.parseToJsonObject(data);
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

    @Override
    public void postAccountBank(final JsonObject jsonRequest) {
        boolean cancel = mView.validate();
        if (!cancel){
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.postBankAccount(jsonRequest);
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
                    mView.success();
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

    @Override
    public void updateAccountBank(final JsonObject jsonRequest) {
        boolean cancel = mView.validate();
        if (!cancel){
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.updateBankAccount(jsonRequest);
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
                    mView.success();
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
}
