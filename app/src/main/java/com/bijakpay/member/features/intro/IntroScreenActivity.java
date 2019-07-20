package com.bijakpay.member.features.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.View;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.R;
import com.bijakpay.member.features.login.LoginActivity;
import com.bijakpay.member.features.main.MainActivity;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 06/06/2017.
 */

public class IntroScreenActivity extends MaterialIntroActivity {

    @BindString(R.string.intro_title_business)
    String strIntroTitleBusiness;
    @BindString(R.string.intro_desc_business)
    String strIntroDescBusiness;
    @BindString(R.string.intro_title_upgrade)
    String strIntroTitleUpgrade;
    @BindString(R.string.intro_desc_upgrade)
    String strIntroDescUpgrade;
    @BindString(R.string.intro_title_payment)
    String strIntroTitlePayment;
    @BindString(R.string.intro_desc_payment)
    String strIntroDescPayment;
    @BindString(R.string.intro_title_community)
    String strIntroTitleCommunity;
    @BindString(R.string.intro_desc_community)
    String strIntroDescCommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0.0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.material_red)
                .buttonsColor(R.color.material_red_light)
                .image(R.drawable.businescenter)
                .title(strIntroTitleBusiness)
                .description(strIntroDescBusiness)
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.material_green)
                .buttonsColor(R.color.material_green_light)
                .image(R.drawable.upgrdaeaccount)
                .title(strIntroTitleUpgrade)
                .description(strIntroDescUpgrade)
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.material_orange)
                .buttonsColor(R.color.material_orange_deep)
                .image(R.drawable.paymentgateway)
                .title(strIntroTitlePayment)
                .description(strIntroDescPayment)
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.material_blue_light)
                .buttonsColor(R.color.material_blue)
                .image(R.drawable.communityselection)
                .title(strIntroTitleCommunity)
                .description(strIntroDescCommunity)
                .build());
    }

    @Override
    public void onFinish() {
        super.onFinish();
        Class<?> cls = (BijakApps.getInstance().isLogin()) ? MainActivity.class : LoginActivity.class;
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}
