package com.bijakpay.member.features.personal_report;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemCommission;

import java.util.List;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public interface TransCommissionReportPresenter {

    void getTransCommission(int page);

    interface View extends BaseView{
        void successListTransCommission(List<ItemCommission> itemCommissions, int page);
    }
}
