package com.bijakpay.member.features.profile.address;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.Address;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public interface AddressPresenter {

    interface View extends BaseView {

        void successPrimary(List<Address> addresses);

        /*void successOther(List<Address> addresses);*/

        void showSuccess();

    }

    void getAddress();

    void deleteAddress(int id);
}
