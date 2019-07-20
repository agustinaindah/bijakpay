package com.bijakpay.member.features.bussines_report;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemRoyalty;

import java.util.List;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public interface ReportRoyaltyPresenter {
    void getReportRoyalty(int page);

    interface View extends BaseView{
        void successListRoyalty(List<ItemRoyalty> itemRoyalties, int page);
    }
}
