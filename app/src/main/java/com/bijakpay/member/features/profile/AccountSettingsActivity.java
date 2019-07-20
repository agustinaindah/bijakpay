package com.bijakpay.member.features.profile;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.profile.address.AddressFragment;
import com.bijakpay.member.features.profile.bank.BankAccountFragment;
import com.bijakpay.member.utils.Consts;

/**
 * Created by agustinaindah on 14/08/2017.
 */

public class AccountSettingsActivity extends BaseActivity {

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fm = getSupportFragmentManager();
        if (getIntent().getIntExtra(Consts.ARG_TYPE, 0) == Consts.TYPE_ADDRESS)
        {
            gotoFragment(fm, AddressFragment.newInstance());
        }
        else if (getIntent().getIntExtra(Consts.ARG_TYPE, 0) == Consts.TYPE_BANK_ACCOUNT)
        {
            gotoFragment(fm, BankAccountFragment.newInstance());
        }
        else
         {
            gotoFragment(fm, AccountSettingsFragment.newInstance());
        }
    }

    @Override
    protected int setView() {
        return R.layout.activity_account_settings;
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
