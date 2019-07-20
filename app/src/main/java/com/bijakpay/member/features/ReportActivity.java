package com.bijakpay.member.features;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.bussines_report.BussinesReportFragment;
import com.bijakpay.member.features.personal_report.PersonalReportFragment;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.features.profile.ProfilePresenterImpl;
import com.bijakpay.member.model.ItemProfile;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.Consts;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by agustinaindah on 21/08/2017.
 */

public class ReportActivity extends BaseActivity implements ProfilePresenter.View {

    private ProfilePresenter mPresenter;
    private String memberType;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new ProfilePresenterImpl(this);
        mPresenter.getProfile();

        FragmentManager fm = getSupportFragmentManager();
        if ("agen".equalsIgnoreCase(memberType) || "dealer".equalsIgnoreCase(memberType))
        {
            gotoFragment(fm, ReportFragment.newInstance());
        } else if ("netizen".equalsIgnoreCase(memberType) || "lifestyler".equalsIgnoreCase(memberType))
        {
            gotoFragment(fm, PersonalReportFragment.newInstance());
        }
    }

   /* private void initData(){
        mItem =(ItemProfile) getIntent().getSerializableExtra(Consts.ARG_DATA);
    }*/

    @Override
    protected int setView() {
        return R.layout.activity_report;
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
