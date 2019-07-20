package com.bijakpay.member.features.community;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.features.profile.ProfilePresenterImpl;
import com.bijakpay.member.model.ItemGetCommunity;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public class JoinCommunityDialog extends DialogFragment implements
        JoinCommunityPresenter.View, ProfilePresenter.View{

    @BindView(R.id.edtCommName)
    EditText edtCommName;
    @BindView(R.id.edtCommType)
    EditText edtCommType;
    @BindView(R.id.edtPrice)
    EditText edtPrice;
    @BindView(R.id.edtSaldo)
    EditText edtSaldo;
    @BindView(R.id.btnSubmitJoin)
    Button btnSubmitJoin;

    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.join_comm)
    String strTitleJoinComm;
    @BindString(R.string.msg_join_comm)
    String strMsgSuccessJoin;
    @BindString(R.string.msg_saldo)
    String strSaldoNotEnough;

    private JoinCommunityPresenter mPresenter;
    private ProfilePresenter mPresennterProfile;
    private ProgressDialog progressDialog;
    private ItemGetCommunity itemGetCommunity;
    private Long Saldo;

    public static JoinCommunityDialog newInstance(ItemGetCommunity itemGetCommunity) {
        Bundle args = new Bundle();
        args.putSerializable(Consts.ARG_DATA,itemGetCommunity);
        JoinCommunityDialog fragment = new JoinCommunityDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        itemGetCommunity = (ItemGetCommunity) getArguments().getSerializable(Consts.ARG_DATA);
        mPresenter = new JoinCommunityPresenterImpl(this);
        mPresennterProfile = new ProfilePresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_join_community, null);
        ButterKnife.bind(this, view);

        edtCommName.setText(itemGetCommunity.getCommunityTitle());
        edtCommType.setText(itemGetCommunity.getCommunityMemberType());
        edtPrice.setText(Helper.numberCurrency(itemGetCommunity.getCommunityPrice()));

        mPresennterProfile.getProfile();

        builder.setView(view)
                .setTitle(strTitleJoinComm);
        final AlertDialog alertDialog = builder.create();

        return alertDialog;
    }

    private JsonObject getData(){
        JsonObject jsonRequest = new JsonObject();
        try {
            jsonRequest.addProperty("community_id", itemGetCommunity.getCommunityId());
            jsonRequest.addProperty("member_type", itemGetCommunity.getCommunityMemberType());
            jsonRequest.addProperty("price", itemGetCommunity.getCommunityPrice());
            jsonRequest.addProperty("quota", itemGetCommunity.getSysMembershipTypeQuota());

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonRequest;
    }

    @OnClick(R.id.btnSubmitJoin)
    public void submitJoin() {
        if (Saldo == 0){
            Helper.createAlert(getActivity(), Consts.STR_INFO, strSaldoNotEnough);
        } else {
            mPresenter.postJoin(getData());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getDialog() == null)
            return;

        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, strInfoNotConnect, false, new CallbackInterface() {
            @Override
            public void callback() {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void success(JsonObject jsonRes) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, strMsgSuccessJoin, false, new CallbackInterface() {
            @Override
            public void callback() {
                JoinCommunityDialog.this.getDialog().cancel();
            }
        });
    }

    @Override
    public void showProfile(JsonObject results) {
        edtSaldo.setText(Helper.numberCurrency(results.get("saldo_ewallet").getAsLong()));

        Saldo = results.get("saldo_ewallet").getAsLong();
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
