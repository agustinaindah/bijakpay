package com.bijakpay.member.utils;

import com.bijakpay.member.model.BaseResponse;
import com.bijakpay.member.networks.ApiService;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 27/04/2017.
 */

public interface ServiceInterface {

    Call<BaseResponse> callBackResponse(ApiService apiService);

    void showProgress();

    void hideProgress();

    void responseSuccess(Response<BaseResponse> response);

    void responseFailed(Response<BaseResponse> response);

    void failed(Throwable t);
}
