package com.bijakpay.member.features.community;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.ItemCommMember;

import java.util.List;
import java.util.Map;

/**
 * Created by agustinaindah on 29/08/2017.
 */

public interface MyCommunityMemberPresenter {

    void getCommMember(Map<String, String> queryRequest);

    interface View extends BaseView{
        void successListMember(List<ItemCommMember> itemCommMembers, Integer start);
    }

}
