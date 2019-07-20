package com.bijakpay.member;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ServiceInterface;
import com.bijakpay.member.utils.SharedPref;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by agustinaindah on 26/04/2017.
 */

public class BijakApps extends Application {

    private static BijakApps ourInstance;
    private static Context context;
    private Call<BaseResponse> mRequest = null;

    public static synchronized Context getContext() {
        return context;
    }

    public static BijakApps getInstance() {
        return ourInstance;
    }

    public static JsonObject getCommMember() {
        return Helper
                .getGsonInstance()
                .fromJson(SharedPref.getString(Consts.MYCOMMUNITY), JsonObject.class);
    }

    /*public static void getBanner() {
        SuperpromoApp.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getBanner();
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
                    JsonArray jsonRes = Helper.parseToJsonObject(data).get("results").getAsJsonArray();
                    SuperpromoApp
                            .getInstance()
                            .Prefs()
                            .edit()
                            .putString(Consts.BANNER, jsonRes.toString())
                            .commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void responseFailed(Response<BaseResponse> response) {
            }

            @Override
            public void failed(Throwable t) {
                t.printStackTrace();
            }
        });
    }*/

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ourInstance = this;
        /*mDataManager = new DataManager(this, Schedulers.io());*/
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ourInstance = null;
    }

    /**
     * get SharedPreferences
     *
     * @return SharedPreferences
     */
    public SharedPreferences Prefs() {
        return getSharedPreferences(getPackageName(), MODE_PRIVATE);
    }

    /**
     * @return String base_url
     */
    public String getBaseUrl() {
        return isLogin() ? getBaseUrlMember() : getString(R.string.base_url);
    }

    /**
     * @return String base_url /web
     */
    public String getBaseUrlWeb() {
        return getString(R.string.base_url) + "/";
    }

    /**
     * @return String base_url /member
     */
    public String getBaseUrlMember() {
        return getString(R.string.base_url) + "/";
    }

    /**
     * @return String X-API-KEY value
     */
    public String getApiKey() {
        return getString(R.string.X_API_KEY);
    }

    /**
     * @return String token from preferences
     */
    public String getToken() {
        return Prefs().getString(Consts.TOKEN, null);
    }

    /**
     * @return String email from preferences
     */
    public String getEmail() {
        return Prefs().getString(Consts.EMAIL, null);
    }

    /**
     * @return boolean login
     */
    public boolean isLogin() {
        return (getToken() != null);
    }

    /**
     * logout | remove token
     */
    public void logout() {
        //save banner again
        /*String banner = Prefs().getString(Consts.BANNER, "");*/
        SharedPreferences.Editor edit = Prefs().edit();
        edit.clear().commit();
        edit.putBoolean(Consts.FIRST_RUN, false).commit();
        /*edit.putString(Consts.BANNER, banner).commit();*/
    }

    public int getSaldo() {
        return Prefs().getInt(Consts.SALDO, 0);
    }

    public int getPoint(){
        return Prefs().getInt(Consts.POINT, 0);
    }

    /**
     * method for get DataManager instance
     *
     * @return DataManager
     */
    /*public DataManager getmDataManager() {
        return mDataManager;
    }*/

    /**
     * @return basic auth
     */
    public String getBasicAuth() {
        return Helper.basicAuth(Consts.BASIC_USERNAME, Consts.BASIC_PASSWORD);
    }

    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BijakApps.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {

                                Request ori = chain.request();

                                Request.Builder reqBuilder = ori.newBuilder()
                                        .addHeader(Consts.X_API_KEY,
                                                BijakApps.getInstance().getApiKey())
                                        .addHeader(Consts.AUTHORIZATION,
                                                BijakApps.getInstance().getBasicAuth())
                                        .addHeader("Content-Type", "application/json");

                                String token = BijakApps.getInstance().getToken();

                                if (token != null) {
                                    reqBuilder.addHeader(Consts.TOKEN, token);
                                }

                                Request req = reqBuilder.build();

                                return chain.proceed(req);
                            }
                        }).build()
                ).build();
    }


    public void service(final ServiceInterface callApiService) {
        callApiService.showProgress();
        ApiService apiService = retrofit().create(ApiService.class);
        mRequest = callApiService.callBackResponse(apiService);
        mRequest.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (!response.isSuccessful()) {
                    callApiService.responseFailed(response);
                    callApiService.hideProgress();
                    return;
                }
                if (response.body().equals(null)) {
                    return;
                } else {
                    callApiService.responseSuccess(response);
                    callApiService.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callApiService.failed(t);
                callApiService.hideProgress();
            }
        });

    }

    public Call<BaseResponse> getRequest() {
        return mRequest;
    }

    public String getPhone() {
        return Prefs().getString(Consts.PHONE, null);
    }

   /* public static String parseErrorMsg(Throwable e) {
        String msg = "Terjadi kesalahan";
        try {
            String response = ((HttpException) e).response().errorBody().string();
            JsonObject jsonResponse = Helper.parseToJsonObject(response);
            msg = jsonResponse.get("msg").getAsString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return msg;
    }*/

}
