package com.bijakpay.member.features.personal_report;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemCashback;

import java.util.List;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public interface TransCashbackReportPresenter {

    void getTransCashback(int page);

    interface View extends BaseView{
        void successListTransCashback(List<ItemCashback> itemCashbacks, int page);
    }
}
