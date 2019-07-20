package com.bijakpay.member.features.register_friends;

import android.os.Bundle;
import android.view.MenuItem;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;

/**
 * Created by agustinaindah on 14/08/2017.
 */

public class RegisterFriendActivity2 extends BaseActivity {
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gotoFragment(getSupportFragmentManager(), RegisterFriendEwalletFragment.newInstance());
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
