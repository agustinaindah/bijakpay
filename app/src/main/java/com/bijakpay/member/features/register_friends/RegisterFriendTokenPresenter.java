package com.bijakpay.member.features.register_friends;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 05/05/2017.
 */

public interface RegisterFriendTokenPresenter {

    void postRegToken(JsonObject jsonRequest);

    interface View extends BaseView{

        boolean validate();

        void success(JsonObject jsonRes);
    }
}
