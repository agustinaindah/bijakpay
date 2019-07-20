package com.bijakpay.member.features.payment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import butterknife.OnClick;

/**
 * Created by agustinaindah on 17/05/2017.
 */

public class PaymentActivity extends BaseActivity implements ProfilePresenter.View {

    @BindView(R.id.txtIsiUsername)
    TextView txtIsiUsername;
    @BindView(R.id.btnLinkPayment)
    Button btnLinkPayment;

    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;

    private ProgressDialog progressDialog;
    private ProfilePresenter mPresenter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bijakpay Payment");
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

    @OnClick(R.id.btnLinkPayment)
    public void link(View view){
        Uri uri = Uri.parse("https:/play.google.com/store/apps/details?id=com.payment.bijakpay");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    @Override
    protected int setView() {
        return R.layout.activity_payment;
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
        try {
            txtIsiUsername.setText(results.get("member_code").getAsString());

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
