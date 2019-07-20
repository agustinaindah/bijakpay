package com.bijakpay.member.features.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.login.LoginActivity;
import com.bijakpay.member.features.profile.AccountSettingsActivity;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindString;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    /*@BindView(R.id.bottomBar)
    BottomBar bottomBar;*/
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindString(R.string.action_back_pressed)
    String strBackPressed;

    private long backPressedTime = 0;
    private boolean isHome = true;
    private Fragment mFragment = null;
    private FragmentManager fm;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        fm = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        setupActionBarDrawer();
        setupNavigationView();
        navigationView.setNavigationItemSelectedListener(this);
        /*bottomBar.setOnTabSelectListener(this);*/
        goHome();
    }

    private void setupNavigationView() {
        View header = navigationView.getHeaderView(0);

        CircularImageView imgPhoto = (CircularImageView) header.findViewById(R.id.imgProfileHead);
        TextView txtName = (TextView) header.findViewById(R.id.txtName);
        TextView txtNoBijak = (TextView) 
        header.findViewById(R.id.txtNoBijak);
        /*TextView txtSaldoWallet = (TextView)header.findViewById(R.id.txtSaldoWallet);*/

        // TODO: 08/08/2017 kui sek yooo suk njaluk di cek e apine
        SharedPreferences sPref = BijakApps.getInstance().Prefs();
        Helper.displayImage(getApplicationContext(), sPref.getString(Consts.PHOTO, null), imgPhoto);
        txtName.setText(sPref.getString(Consts.NAME, "#member user"));
        txtNoBijak.setText(sPref.getString(Consts.TYPE,"#type"));
        /*txtSaldoWallet.setText(sPref.getString(Consts.SALDO, "#saldo"));*/
    }

    private void setupActionBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setupNavigationView();
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected int setView() {
        return R.layout.activity_main;
    }

    private void goHome() {
        gotoFragment(fm, MainFragment.newInstance());
        setRemoveBackStack();
    }

   /* private void goHomeBar() {
        bottomBar.selectTabWithId(R.id.tab_dashboard);
    }*/

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
                super.onBackPressed();
            } else {
                long t = System.currentTimeMillis();
                if (t - backPressedTime > 2000) {
                    backPressedTime = t;
                    Helper.createToast(this, strBackPressed);
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                mFragment = MainFragment.newInstance();
                break;
            case R.id.nav_profile:
                intent = new Intent(this, AccountSettingsActivity.class);
                break;
           /* case R.id.nav_payment:
                intent = new Intent(this, PaymentActivity.class);
                break;
            case R.id.nav_message:
                intent = new Intent(this, MessageActivity.class);
                break;*/
            case R.id.nav_logout:
                BijakApps.getInstance().logout();
                BijakApps.getInstance().getRequest().cancel();
                finish();
                intent = new Intent(this, LoginActivity.class);
                intent.setFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK
                );
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
        if (mFragment != null) {
            gotoFragment(fm, mFragment);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* @Override
    public void onTabSelected(@IdRes int tabId) {
        Intent intent = null;
        switch (tabId) {
            case R.id.tab_dashboard:
                isHome = true;
                mFragment = MainFragment.newInstance();
                setRemoveBackStack();
                break;
            case R.id.tab_personal_report:
                isHome = false;
                intent = new Intent(this, MyPersonalReportActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_bussines_report:
                isHome = false;
                intent = new Intent(this, MyBusinessReportActivity.class);
                startActivity(intent);
                break;
            *//*case R.id.tab_download:
                isHome = false;
                break;*//*
        }
        appBarLayout.setExpanded(isHome);
        if (mFragment != null) {
            gotoFragment(fm, mFragment);
        }
    }*/

    private void setRemoveBackStack() {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
