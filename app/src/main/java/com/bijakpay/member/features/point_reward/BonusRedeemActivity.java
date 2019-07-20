package com.bijakpay.member.features.point_reward;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.main.MainActivity;
import com.bijakpay.member.features.point_reward.cashback.RedeemCashbackDialog;
import com.bijakpay.member.features.point_reward.comission.RedeemCommisionDialog;
import com.bijakpay.member.features.point_reward.history.HistoryMyPointActivity;
import com.bijakpay.member.features.point_reward.royalty.RedeemRoyaltyDialog;
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
 * Created by agustinaindah on 28/04/2017.
 */

public class BonusRedeemActivity extends BaseActivity implements ProfilePresenter.View{

    // TODO: 22/06/2017 sementara gtu dluu

    @BindView(R.id.txtSaldoEwallet)
    TextView txtSaldoEwallet;
    @BindView(R.id.txtRewardPoint)
    TextView txtRewardPoint;
    @BindView(R.id.txtCashback)
    TextView txtCashback;
    @BindView(R.id.txtRoyalty)
    TextView txtRoyalty;
    @BindView(R.id.txtCommission)
    TextView txtCommission;
    /*@BindView(R.id.btnRedeem)
    Button btnRedeem;*/
    @BindView(R.id.btnCashback)
    Button btnCashback;
    @BindView(R.id.btnRoyalty)
    Button btnRoyalty;
    @BindView(R.id.btnComission)
    Button btnComission;

    @BindString(R.string.label_bonus_redeem)
    String strBonusRedeem;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;

    @BindString(R.string.info_saldo_cash)
    String strSaldoCashbackNol;
    @BindString(R.string.info_saldo_royalty)
    String strSaldoRoyaltyNol;
    @BindString(R.string.info_saldo_commisi)
    String strSaldoComissiNol;

    private ProgressDialog progressDialog;
    private ProfilePresenter mPresenter;
    private Long Cashback;
    private Long Commission;
    private Long Royalty;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(strBonusRedeem);
        initLoading();
        mPresenter = new ProfilePresenterImpl(this);
        mPresenter.getProfile();

        initPresenter();
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(strLoading);
    }

    @OnClick({R.id.btnCashback, R.id.btnComission, R.id.btnRoyalty})
    public void bonus(View view){
        switch (view.getId()){
            case R.id.btnCashback:
                if(Cashback == 0){
                    Helper.createAlert(this, Consts.STR_INFO, strSaldoCashbackNol);
                } else {
                    DialogFragment dialogFragment = RedeemCashbackDialog.newInstance();
                    dialogFragment.show(getSupportFragmentManager(), Consts.DIALOG);
                }
                break;
            case R.id.btnComission:
                if (Commission == 0){
                    Helper.createAlert(this, Consts.STR_INFO, strSaldoComissiNol);
                } else {
                    DialogFragment dialogFragment = RedeemCommisionDialog.newInstance();
                    dialogFragment.show(getSupportFragmentManager(), Consts.DIALOG);
                }
                break;
            case R.id.btnRoyalty:
                if (Royalty == 0){
                    Helper.createAlert(this, Consts.STR_INFO, strSaldoRoyaltyNol);
                }else {
                    DialogFragment dialogFragment = RedeemRoyaltyDialog.newInstance();
                    dialogFragment.show(getSupportFragmentManager(), Consts.DIALOG);
                }
                break;
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_member, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_history_point:
                Intent intent = new Intent(this, HistoryMyPointActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void initPresenter() {
        mPresenter.getProfile();
    }

    @Override
    protected int setView() {
        return R.layout.activity_income;
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
            txtRoyalty.setText(Helper.numberCurrency(results.get("saldo_royalty").getAsLong()));
            txtCommission.setText(Helper.numberCurrency(results.get("saldo_commision").getAsLong()));

            Cashback = results.get("saldo_cashback").getAsLong();
            Royalty = results.get("saldo_royalty").getAsLong();
            Commission = results.get("saldo_commision").getAsLong();

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
