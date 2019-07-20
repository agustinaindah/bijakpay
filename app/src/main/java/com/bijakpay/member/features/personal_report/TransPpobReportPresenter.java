package com.bijakpay.member.features.personal_report;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemPpob;

import java.util.List;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public interface TransPpobReportPresenter {

    void getTransPpob(int page);

    interface View extends BaseView{
        void successlistTransPpob(List<ItemPpob> itemPpobs, int page);
    }
}
