package com.bijakpay.member.features.message;

import android.os.Bundle;
import android.view.MenuItem;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;

/**
 * Created by agustinaindah on 22/06/2017.
 */

public class MessageActivity extends BaseActivity {
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gotoFragment(getSupportFragmentManager(), MessageFragment.newInstance());
    }

    @Override
    protected int setView() {
        return R.layout.activity_message;
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
