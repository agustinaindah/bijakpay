package com.bijakpay.member.features.network;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.features.profile.ProfilePresenterImpl;
import com.bijakpay.member.model.SysMembership;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindView;

/**
 * Created by agustinaindah on 22/08/2017.
 */

public class NetworkActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tabsNetwork)
    TabLayout tabsNetwork;
    @BindView(R.id.vpNetwork)
    ViewPager vpNetwork;

    private NetworkFragmentAdapter mAdapter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("My Network");

        mAdapter = new NetworkFragmentAdapter(getSupportFragmentManager());
        vpNetwork.setAdapter(mAdapter);
        tabsNetwork.setupWithViewPager(vpNetwork);

    }

    @Override
    protected int setView() {
        return R.layout.activity_tab_my_network;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vpNetwork.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
