package com.bijakpay.member.features.point_reward.history;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemMyPoint;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by agustinaindah on 22/06/2017.
 */

public interface HistoryMyPointPresenter {

    void getPointSaldo();

    void getMyPoint(int page);

    interface View extends BaseView{

        void successListMyPoint(List<ItemMyPoint> itemMyPoints, int page);

        void showSaldo(JsonObject results);
    }
}
