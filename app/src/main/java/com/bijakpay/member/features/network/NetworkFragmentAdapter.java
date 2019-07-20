package com.bijakpay.member.features.network;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bijakpay.member.features.network.my_channel.MyChannelFragment;
import com.bijakpay.member.features.network.my_network.MyUnilevelFragment;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.model.SysMembership;
import com.google.gson.JsonObject;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by agustinaindah on 23/08/2017.
 */

public class NetworkFragmentAdapter extends FragmentPagerAdapter implements ProfilePresenter.View{

    private String tabTitles[]= new String[]{ "Unilevel", "My Channel"};
    private ProfilePresenter mPresenter;
    private String memberType;

    public NetworkFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Hashtable<Integer, Fragment> ht = new Hashtable<>();
        ht.put(0, MyUnilevelFragment.newInstance());
        ht.put(1, MyChannelFragment.newInstance());
        return ht.get(position);
    }

    public void initview(){

    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
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