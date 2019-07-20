package com.bijakpay.member.features.bussines_report;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by agustinaindah on 09/08/2017.
 */

public class MyBusinessReportActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tabsReport)
    TabLayout tabsReport;
    @BindView(R.id.vpReport)
    ViewPager vpReport;

    private MyBusinessReportFragmentAdapter mAdapter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Business Report");

        mAdapter = new MyBusinessReportFragmentAdapter(getSupportFragmentManager());
        vpReport.setAdapter(mAdapter);
        tabsReport.setupWithViewPager(vpReport);
    }

    @Override
    protected int setView() {
        return R.layout.activity_tab_report;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vpReport.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
