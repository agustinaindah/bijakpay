package com.bijakpay.member.features.profile.address;

import android.content.SharedPreferences;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.model.City;
import com.bijakpay.member.model.Province;
import com.bijakpay.member.model.Subdistrict;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.google.gson.Gson;
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
 * Created by agustinaindah on 04/05/2017.
 */

public class AddressFormPresenterImpl implements AddressFormPresenter {

    private View mView;
    private SharedPreferences mPref;

    public AddressFormPresenterImpl(View mView) {
        this.mView = mView;
        mPref = BijakApps.getInstance().Prefs();
    }

    @Override
    public void getProvinces() {

        String job = mPref.getString(Consts.PROVINCES, null);

        if (job != null) {
            JsonArray jsonRes = Helper.parseToJsonArray(job);
            mView.showProvinces(parseProvince(jsonRes));
        }

        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getProvinces();
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
                    mPref.edit().putString(Consts.PROVINCES, jsonRes.toString()).commit();
                    mView.showProvinces(parseProvince(jsonRes));
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
    public void getCities(final int provinceId) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getCities(provinceId);
            }

            @Override
            public void showProgress() {
            }

            @Override
            public void hideProgress() {
            }

            @Override
            public void responseSuccess(Response<BaseResponse> response) {
                try {
                    String data = Helper.getGsonInstance().toJson(response.body().getData());
                    JsonObject jsonData = Helper.parseToJsonObject(data);
                    JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                    ArrayList<City> cityArrayList = new ArrayList<City>();
                    for (JsonElement element : jsonRes) {
                        City city = (new Gson()).fromJson(element, City.class);
                        cityArrayList.add(city);
                    }
                    mView.showCities(cityArrayList);
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
    public void getSubdistricts(final int cityId) {
        BijakApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getSubdistricts(cityId);
            }

            @Override
            public void showProgress() {
            }

            @Override
            public void hideProgress() {
            }

            @Override
            public void responseSuccess(Response<BaseResponse> response) {
                try {
                    String data = Helper.getGsonInstance().toJson(response.body().getData());
                    JsonObject jsonData = Helper.parseToJsonObject(data);
                    JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                    ArrayList<Subdistrict> subdistrictArrayList = new ArrayList<Subdistrict>();
                    for (JsonElement element : jsonRes) {
                        Subdistrict subdistrict = (new Gson()).fromJson(element, Subdistrict.class);
                        subdistrictArrayList.add(subdistrict);
                    }
                    mView.showSubdistricts(subdistrictArrayList);
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
    public void postAddress(final JsonObject jsonRequest) {
        boolean cancel = mView.validate();
        if (!cancel) {
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.postAddress(jsonRequest);
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
    public void updateAddress(final JsonObject jsonRequest) {
        boolean cancel = mView.validate();
        if (!cancel) {
            BijakApps.getInstance().service(new ServiceInterface() {
                @Override
                public Call<BaseResponse> callBackResponse(ApiService apiService) {
                    return apiService.updateAddress(jsonRequest);
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

    private List<Province> parseProvince(JsonArray jsonRes) {
        List<Province> provinceList = new ArrayList<Province>();
        Type listProvince = new TypeToken<List<Province>>() {
        }.getType();
        provinceList = new Gson().fromJson(jsonRes, listProvince);
        return provinceList;
    }
}
