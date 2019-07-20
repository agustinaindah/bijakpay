package com.bijakpay.member.features.download;

import com.bijakpay.member.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 30/08/2017.
 */

public interface DownloadPresenter {

    void getDownloadMateri();

    interface View extends BaseView{
        void showMaterial(JsonObject results);
    }

}
