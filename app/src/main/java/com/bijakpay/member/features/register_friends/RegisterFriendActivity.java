package com.bijakpay.member.features.register_friends;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by agustinaindah on 28/04/2017.
 */

public class RegisterFriendActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tabsRegFriend)
    TabLayout tabsRegFriend;
    @BindView(R.id.vpRegFriend)
    ViewPager vpRegFriend;

    @BindString(R.string.label_register_friend)
    String strRegFriends;

    private RegisterFriendFragmentAdapter mAdapter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(strRegFriends);

        mAdapter = new RegisterFriendFragmentAdapter(getSupportFragmentManager());
        vpRegFriend.setAdapter(mAdapter);
        tabsRegFriend.setupWithViewPager(vpRegFriend);
    }

    @Override
    protected int setView() {
        return R.layout.activity_register_friend;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vpRegFriend.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
