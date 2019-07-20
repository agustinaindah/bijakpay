package com.bijakpay.member.features.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.main.MainActivity;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.features.profile.ProfilePresenterImpl;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by agustinaindah on 14/08/2017.
 */

public class DashboardActivity extends BaseActivity implements ProfilePresenter.View {

    @BindView(R.id.txtSaldoEwallet)
    TextView txtSaldoEwallet;
    @BindView(R.id.txtCashback)
    TextView txtCashback;
    @BindView(R.id.txtCommission)
    TextView txtCommission;
    @BindView(R.id.txtRoyaltyBisnis)
    TextView txtRoyaltyBisnis;
    @BindView(R.id.txtRoyaltyDist)
    TextView txtRoyaltyDist;
    @BindView(R.id.txtRewardPoint)
    TextView txtRewardPoint;

    @BindString(R.string.label_dashboard)
    String strDashboard;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;

    private ProgressDialog progressDialog;
    private ProfilePresenter mPresenter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(strDashboard);
        initLoading();
        mPresenter = new ProfilePresenterImpl(this);

        initPresenter();
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(strLoading);
    }

    private void initPresenter() {
        mPresenter.getProfile();
    }

    @Override
    protected int setView() {
        return R.layout.activity_dashboard;
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, strInfoNotConnect, false, new CallbackInterface() {
            @Override
            public void callback() {
                gotoActivity(MainActivity.class);
            }
        });
    }

    @Override
    public void showProfile(JsonObject results) {
        try{
            txtSaldoEwallet.setText(Helper.numberCurrency(results.get("saldo_ewallet").getAsLong()));
            txtRewardPoint.setText(results.get("saldo_point").getAsString() + " pts");
            txtCashback.setText(Helper.numberCurrency(results.get("saldo_cashback").getAsLong()));
            txtRoyaltyBisnis.setText(Helper.numberCurrency(results.get("saldo_royalty").getAsLong()));
            txtCommission.setText(Helper.numberCurrency(results.get("saldo_commision").getAsLong()));
            txtRoyaltyDist.setText(Helper.numberCurrency(results.get("saldo_royalty_distributor").getAsLong()));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void successListSys(List<SysMembership> sysMemberships) {

    }

    @Override
    public void successUpdateProfile() {

    }

    @Override
    public void successUpdateEwallet() {

    }

    @Override
    public void successUpdateToken() {

    }
}
