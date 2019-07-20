package com.bijakpay.member.features;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.intro.IntroScreenActivity;
import com.bijakpay.member.features.login.LoginActivity;
import com.bijakpay.member.features.main.MainActivity;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.SharedPref;

/**
 * Created by agustinaindah on 26/04/2017.
 */

public class SplashscreenActivity extends BaseActivity {
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        }, 2500);
    }

    private void next() {
        Class<?> clazz = null;
        if (SharedPref.getBoolean(Consts.NOT_FIRST_RUN)){
            clazz = (BijakApps.getInstance().isLogin()) ? MainActivity.class : LoginActivity.class;
        } else {
            clazz = IntroScreenActivity.class;
            SharedPref.saveBoolean(Consts.NOT_FIRST_RUN, true);
        }
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected int setView() {
        return R.layout.activity_splashscreen;
    }
}
