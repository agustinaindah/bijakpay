package com.bijakpay.member.features.community;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemGetCommunity;

import java.util.List;
import java.util.Map;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public interface ListGetCommunityPresenter {

    void getCommunity(Map<String, String> queryRequest);

    interface View extends BaseView{
        void showGetCommunity(List<ItemGetCommunity> itemGetCommunityList, Integer pagenum);
    }
}
