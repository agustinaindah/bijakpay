package com.bijakpay.member.features.message;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public interface MessageFormPresenter {

    void postFormMessage(JsonObject jsonRequest);

    interface View extends BaseView{
        boolean validate();

        void successMessageForm();
    }
}
