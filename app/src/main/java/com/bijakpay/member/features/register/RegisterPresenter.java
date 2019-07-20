package com.bijakpay.member.features.register;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public interface RegisterPresenter {

    void postRegsiter(JsonObject jsonRequest);

    interface View extends BaseView{
        boolean validate();

        void success(JsonObject jsonRes);
    }
}
