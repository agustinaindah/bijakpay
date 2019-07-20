package com.bijakpay.member.features.profile;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.SysMembership;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by agustinaindah on 28/04/2017.
 */

public interface ProfilePresenter {

    void getProfile();

    void getSysMembership();

    void updateProfile(JsonObject jsonRequest);

    void updateMemberEwallet(JsonObject jsonRequest);

    void updateMemberToken(JsonObject jsonRequest);

     void updatePhotoProfile(JsonObject jsonRequest);

    interface View extends BaseView{

        void showProfile(JsonObject results);

        void successListSys(List<SysMembership> sysMemberships);

        void successUpdateProfile();

        void successUpdateEwallet();

        void successUpdateToken();
    }
}
