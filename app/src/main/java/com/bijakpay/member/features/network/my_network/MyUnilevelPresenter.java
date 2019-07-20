package com.bijakpay.member.features.network.my_network;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemMyChannel;
import com.bijakpay.member.model.ItemMyNetwork;

import java.util.List;

/**
 * Created by agustinaindah on 23/08/2017.
 */

public interface MyUnilevelPresenter {

    void getMyNetwork(int page);

    interface View extends BaseView{
        void showListUnilevel(List<ItemMyNetwork> myNetworks, int page);
    }
}
