package com.bijakpay.member.features.register_friends;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.SysMembership;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by agustinaindah on 08/05/2017.
 */

public interface RegisterFriendEwalletPresenter {

    void postRegEwallet(JsonObject jsonRequest);

    void getSysMember();

    interface View extends BaseView{

        boolean validate();

        void success(JsonObject jsonRes);

        void showSysMember(List<SysMembership> sysMemberships);
    }
}
