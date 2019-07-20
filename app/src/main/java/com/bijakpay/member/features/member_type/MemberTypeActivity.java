package com.bijakpay.member.features.member_type;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.main.MainActivity;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.features.profile.ProfilePresenterImpl;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 28/04/2017.
 */

public class MemberTypeActivity extends BaseActivity implements ProfilePresenter.View {

    @BindView(R.id.rvMemberType)
    RecyclerView rvMemberType;
    @BindView(R.id.txtMembership)
    TextView txtMembership;
    /*@BindView(R.id.btnUpdateToken)
    Button btnUpdateToken;*/

    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.label_member_type)
    String strMemberType;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;

    private String memberType;
    private ProfilePresenter mPresenter;
    private ProgressDialog progressDialog;
    private SysMembership sys;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(strMemberType);

        initLoading();

        mPresenter = new ProfilePresenterImpl(this);
        mPresenter.getProfile();
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(strLoading);
    }

    /*@OnClick(R.id.btnUpdateToken)
    public void submitToken(View view){
        DialogFragment dialogFragment = UpdateMemberTokenDialog.newInstance(sys);
        dialogFragment.show(getSupportFragmentManager(), Consts.ARG_DATA);
    }*/

    @Override
    protected int setView() {
        return R.layout.activity_member_type;
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
            this.memberType = results.get("member_type").getAsString();
            txtMembership.setText(memberType);
            Log.d("Halo",this.memberType);

            mPresenter.getSysMembership();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void successListSys(List<SysMembership> sysMemberships) {
        MemberTypeAdapter memberTypeAdapter = new MemberTypeAdapter(sysMemberships, this);
        memberTypeAdapter.setCurrentType(memberType);

        rvMemberType.setLayoutManager(new LinearLayoutManager(this));
        rvMemberType.setAdapter(memberTypeAdapter);
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
