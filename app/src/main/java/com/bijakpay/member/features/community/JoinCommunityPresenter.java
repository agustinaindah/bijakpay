package com.bijakpay.member.features.community;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public interface JoinCommunityPresenter {

    void postJoin(JsonObject jsonRequest);

    interface View extends BaseView{

        void success(JsonObject jsonRes);
    }
}
