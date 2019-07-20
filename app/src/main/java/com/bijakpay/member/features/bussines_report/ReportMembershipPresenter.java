package com.bijakpay.member.features.bussines_report;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemMembership;

import java.util.List;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public interface ReportMembershipPresenter {

    void getReportMembership(int page);

    interface View extends BaseView{
        void successListMembership(List<ItemMembership> itemMemberships, int page);
    }
}
