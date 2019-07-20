package com.bijakpay.member.features.point_reward;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 22/06/2017.
 */

public interface RedeemBonusPresenter {

    void postRedeemPoint(JsonObject jsonRequest);

    interface View extends BaseView{
        boolean validate();

        void success(JsonObject jsonRes);
    }
}
