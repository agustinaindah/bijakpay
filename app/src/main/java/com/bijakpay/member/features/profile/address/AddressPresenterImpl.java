package com.bijakpay.member.features.profile.address;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.Address;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class AddressPresenterImpl implements AddressPresenter {

    private View mView;
    private int mAddressId;

    public AddressPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getAddress() {
        api(Consts.GET);
    }

    @Override
    public void deleteAddress(int id) {
        mAddressId = id;
        api(Consts.DELETE);
    }

    private void api(final int apiType) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                if (apiType == Consts.GET) {
                    return apiService.getAddress();
                } else if (apiType == Consts.DELETE) {
                    return apiService.deleteAddress(mAddressId);
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
                    String data =  Helper.getGsonInstance().toJson(response.body().getData());
                    JsonObject jsonData = Helper.parseToJsonObject(data);

                    /*if (apiType == Consts.GET) {
                        JsonArray jsonRes = jsonData.get("results").getAsJsonArray();

                        if (mAddressType == "Utama") {
                            Address primaryAddress = (new Gson())
                                    .fromJson(jsonRes.get(0).getAsJsonObject(), Address.class);
                            mView.successPrimary(primaryAddress);
                        } else {
                            ArrayList<Address> addresses = new ArrayList<Address>();
                            if (jsonRes.size() != 0) {
                                for (JsonElement element : jsonRes) {
                                    Address address = (new Gson()).fromJson(element, Address.class);
                                    addresses.add(address);
                                }
                            }
                            mView.successOther(addresses);
                        }
                    } else if (apiType == Consts.DELETE) {
                        mView.showSuccess();
                    } else {
                        throw new Exception("api type not defined");
                    }*/
                    if (apiType == Consts.GET) {
                        JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                        ArrayList<Address> addresses = new ArrayList<Address>();
                        for (JsonElement element : jsonRes){
                            Address address =
                                    Helper.getGsonInstance().fromJson(element, Address.class);
                            addresses.add(address);
                        }
                        mView.successPrimary(addresses);
                    } else if (apiType == Consts.DELETE) {
                        mView.showSuccess();
                    } else {
                        throw new Exception("api type not defined");
                    }
                } catch (Exception e) {
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
    public void getAddress() {
        api(Consts.GET);
         BijakApps.getInstance().service(new ServiceInterface() {
             @Override
             public Call<BaseResponse> callBackResponse(ApiService apiService) {
                 if (apiType == Consts.GET) {
                     return apiService.getAddress();
                 } else if (apiType == Consts.DELETE) {
                     return apiService.deleteAddress(mAddressId);
                 }
                 return null;
                 return apiService.getAddress();
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
                     String data =  Helper.getGsonInstance().toJson(response.body().getData());
                     JsonObject jsonData = Helper.parseToJsonObject(data);
                     JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                     ArrayList<Address> addresses = new ArrayList<Address>();
                     for (JsonElement element : jsonRes){
                         Address address =
                                 Helper.getGsonInstance().fromJson(element,Address.class);
                         addresses.add(address);
                     }
                     mView.successPrimary(addresses);

                    if (apiType == Consts.GET) {
                        JsonArray jsonRes = jsonData.get("results").getAsJsonArray();

                        if (mAddressType == "Utama") {
                            Address primaryAddress = (new Gson())
                                    .fromJson(jsonRes.get(0).getAsJsonObject(), Address.class);
                            mView.successPrimary(primaryAddress);
                        } else {
                            ArrayList<Address> addresses = new ArrayList<Address>();
                            if (jsonRes.size() != 0) {
                                for (JsonElement element : jsonRes) {
                                    Address address = (new Gson()).fromJson(element, Address.class);
                                    addresses.add(address);
                                }
                            }
                            mView.successOther(addresses);
                        }
                    } else if (apiType == Consts.DELETE) {
                        mView.showSuccess();
                    } else {
                        throw new Exception("api type not defined");
                    }
                    if (apiType == Consts.GET) {
                        JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                        ArrayList<Address> addresses = new ArrayList<Address>();
                        for (JsonElement element : jsonRes){
                            Address address =
                                    Helper.getGsonInstance().fromJson(element, Address.class);
                            addresses.add(address);
                        }
                        mView.successPrimary(addresses);
                    } else if (apiType == Consts.DELETE) {
                        mView.showSuccess();
                    } else {
                        throw new Exception("api type not defined");
                    }
                 } catch (Exception e) {
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

    @Override
    public void deleteAddress(int id) {
        mAddressId = id;
        api(Consts.DELETE);
    }*/
}
