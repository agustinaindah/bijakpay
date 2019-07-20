package com.bijakpay.member.features.bussines_report;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Hashtable;

/**
 * Created by agustinaindah on 09/08/2017.
 */

public class MyBusinessReportFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Report Membership", "Report Royalty", "Report Royalty Refund"
    ,"Report Stock Card"};

    public MyBusinessReportFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Hashtable<Integer, Fragment> ht = new Hashtable<>();
        ht.put(0,ReportMembershipFragment.newInstance());
        ht.put(1,ReportRoyaltyFragment.newInstance());
        ht.put(2,ReportRefundRoyaltyFragment.newInstance());
        ht.put(3,ReportStockCardFragment.newInstance());
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
