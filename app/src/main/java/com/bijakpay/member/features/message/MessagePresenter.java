package com.bijakpay.member.features.message;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemMessage;
import com.bijakpay.member.model.ItemMessageHeader;

import java.util.List;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public interface MessagePresenter {

    void getMessage(int page);

    interface View extends BaseView{
        void successMessage(List<ItemMessage> itemMessages, int page);
    }
}
