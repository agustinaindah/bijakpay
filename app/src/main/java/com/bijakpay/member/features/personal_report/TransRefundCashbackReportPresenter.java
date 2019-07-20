package com.bijakpay.member.features.personal_report;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemRefundCashback;

import java.util.List;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public interface TransRefundCashbackReportPresenter {

    void getTransRefundCashback(int page);

    interface View extends BaseView{
        void successListTransRefundCashback(List<ItemRefundCashback> itemRefundCashbacks, int page);
    }
}
