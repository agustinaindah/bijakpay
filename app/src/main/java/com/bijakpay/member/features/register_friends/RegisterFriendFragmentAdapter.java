package com.bijakpay.member.features.register_friends;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Hashtable;

/**
 * Created by agustinaindah on 05/05/2017.
 */

public class RegisterFriendFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Ewallet"};

    public RegisterFriendFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Hashtable<Integer, Fragment> ht = new Hashtable<>();
       /* ht.put(0, RegisterFriendTokenFragment.newInstance());*/
        ht.put(1, RegisterFriendEwalletFragment.newInstance());
        return ht.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
