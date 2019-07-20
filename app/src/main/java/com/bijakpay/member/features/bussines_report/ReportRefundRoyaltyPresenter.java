package com.bijakpay.member.features.bussines_report;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemRefundRoyalty;

import java.util.List;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public interface ReportRefundRoyaltyPresenter {

    void getReportRefundRoyalty(int page);

    interface View extends BaseView{
        void successListRefundRoyalty(List<ItemRefundRoyalty> itemRefundRoyalties, int page);
    }
}
