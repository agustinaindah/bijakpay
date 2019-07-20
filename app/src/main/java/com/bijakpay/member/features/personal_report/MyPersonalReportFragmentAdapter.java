package com.bijakpay.member.features.personal_report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Hashtable;

/**
 * Created by agustinaindah on 09/08/2017.
 */

public class MyPersonalReportFragmentAdapter extends FragmentPagerAdapter{

    private String tabTitles[] = new String[]{"Report Transaksi PPOB", "Report Cashback", "Report Commission",
    "Report Cashback Refund"};

    public MyPersonalReportFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Hashtable<Integer, Fragment> ht = new Hashtable<>();
        ht.put(0, TransPpobReportFragment.newInstance());
        ht.put(1,TransCashbackReportFragment.newInstance());
        ht.put(2,TransCommissionReportFragment.newInstance());
        ht.put(3, TransRefundCashbackReportFragment.newInstance());
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
