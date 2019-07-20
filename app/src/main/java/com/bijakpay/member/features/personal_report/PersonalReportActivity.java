package com.bijakpay.member.features.personal_report;

import android.os.Bundle;
import android.view.MenuItem;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;

/**
 * Created by agustinaindah on 21/06/2017.
 */

public class PersonalReportActivity extends BaseActivity {
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gotoFragment(getSupportFragmentManager(), PersonalReportFragment.newInstance());
    }

    @Override
    protected int setView() {
        return R.layout.activity_report;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }
}
