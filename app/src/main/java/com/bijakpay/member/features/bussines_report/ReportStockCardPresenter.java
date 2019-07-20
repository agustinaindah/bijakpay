package com.bijakpay.member.features.bussines_report;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemStockCard;

import java.util.List;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public interface ReportStockCardPresenter {

    void getReportStockCard(int page);

    interface View extends BaseView{
        void successListStockCard(List<ItemStockCard> itemStockCards, int page);
    }
}
