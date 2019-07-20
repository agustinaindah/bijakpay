package com.bijakpay.member.features.network;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.network.NetworkFragment;
import com.bijakpay.member.features.network.my_network.MyUnilevelFragment;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.features.profile.ProfilePresenterImpl;
import com.bijakpay.member.model.SysMembership;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by agustinaindah on 04/09/2017.
 */

public class MyNetworkActivity extends BaseActivity implements ProfilePresenter.View{

    private ProfilePresenter mPresenter;
    private String memberType;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new ProfilePresenterImpl(this);
        mPresenter.getProfile();

        FragmentManager fm = getSupportFragmentManager();
        if ("agen".equalsIgnoreCase(memberType) || "dealer".equalsIgnoreCase(memberType)){
            gotoFragment(fm, NetworkFragment.newInstance());

        } else if ("netizen".equalsIgnoreCase(memberType) || "lifestyler".equalsIgnoreCase(memberType)) {
            gotoFragment(fm, MyUnilevelFragment.newInstance());
        }
    }

    @Override
    protected int setView() {
        return R.layout.activity_my_network;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void notConnect(String msg) {

    }

    @Override
    public void showProfile(JsonObject results) {
        try {
            memberType = results.get("member_type").getAsString();
            /*mPresenter.getProfile();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void successListSys(List<SysMembership> sysMemberships) {

    }

    @Override
    public void successUpdateProfile() {

    }

    @Override
    public void successUpdateEwallet() {

    }

    @Override
    public void successUpdateToken() {

    }
}
