package com.bijakpay.member.features.network.my_channel;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemMyChannel;

import java.util.List;

/**
 * Created by agustinaindah on 24/08/2017.
 */

public interface MyChannelPresenter {

    void getMyChannel(int page);

    interface View extends BaseView{

        void showListChannel(List<ItemMyChannel> itemMyChannels, int page);
    }

}
