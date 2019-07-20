package com.bijakpay.member.features.login;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 26/04/2017.
 */

public interface LoginPresenter {

    void login(JsonObject jsonRequest);

    interface View extends BaseView {
        void successLogin(JsonObject user);
    }
}
