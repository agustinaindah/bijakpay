package com.bijakpay.member.features.profile.bank;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 12/05/2017.
 */

public interface BankAccountFormPresenter {

    interface View extends BaseView {

        boolean validate();

        void success();

        void successSendCode();
    }

    void requestCode(int mType, int id);

    void postAccountBank(JsonObject jsonRequest);

    void updateAccountBank(JsonObject jsonRequest);
}
