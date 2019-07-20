package com.bijakpay.member.features.profile.change_password;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 12/05/2017.
 */

public interface ChangePasswordPresenter {

    interface View extends BaseView {

        boolean validate();

        void success(JsonObject jsonRes);

    }

    void changePassword(JsonObject jsonRequest);
}
