package com.bijakpay.member.features.message;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemMessageDetail;
import com.bijakpay.member.model.ItemMessageHeader;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public interface MessageDetailPresenter {

    void postReplyMessage(JsonObject jsonRequest);

    void getMessageDetail(int id);

    interface View extends BaseView{

        boolean validate();

        void successReply();

        void succesMessage(List<ItemMessageDetail> itemMessageDetails);
    }
}
