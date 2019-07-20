package com.bijakpay.member.features.payment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.features.profile.ProfilePresenterImpl;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public class PaymentFragment extends BaseFragment implements ProfilePresenter.View{

    @BindView(R.id.txtIsiUsername)
    TextView txtIsiUsername;
    /*@BindView(R.id.btnLinkPayment)
    Button btnLinkPayment;*/

    @BindString(R.string.loading)
    String strLoading;

    private ProfilePresenter mPresenter;
    private ProgressDialog progressDialog;

    public static PaymentFragment newInstance() {

        Bundle args = new Bundle();

        PaymentFragment fragment = new PaymentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new ProfilePresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Payment Bijakpay");
        mPresenter.getProfile();

    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    /* @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bijak Payment");
        mPresenter = new ProfilePresenterImpl(this);
        mPresenter.getProfile();
    }*/

//    @OnClick(R.id.btnLinkPayment)
//    public void btnLinkPayment(View view){
//        Uri uri = Uri.parse("http://payment.bijakpay.com");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
//    }

    @Override
    protected int setView() {
        return R.layout.fragment_payment;
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
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
