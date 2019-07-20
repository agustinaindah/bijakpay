package com.bijakpay.member.features.login.forgot_password;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 21/06/2017.
 */

public interface ForgotPasswordPresenter {

    void postForgotPassword(JsonObject jsonRequest);

    interface View extends BaseView{
        boolean validate();

        void success(JsonObject jsonRes);
    }
}
