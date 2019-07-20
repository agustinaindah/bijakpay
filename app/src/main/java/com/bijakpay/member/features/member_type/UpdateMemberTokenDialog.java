package com.bijakpay.member.features.member_type;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
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
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 08/05/2017.
 */

public class UpdateMemberTokenDialog extends DialogFragment implements ProfilePresenter.View{

    @BindView(R.id.edtToken)
    EditText edtToken;
    @BindView(R.id.edtPinNumber)
    EditText edtPinNumber;

    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.token_ver)
    String strTokenVerification;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;

    private ProgressDialog progressDialog;
    private SysMembership sys;
    private ProfilePresenterImpl mPresenter;

    public static UpdateMemberTokenDialog newInstance(SysMembership sys) {
        Bundle args = new Bundle();
        args.putSerializable(Consts.ARG_DATA, sys);
        UpdateMemberTokenDialog fragment = new UpdateMemberTokenDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        sys = (SysMembership) getArguments().getSerializable(Consts.ARG_DATA);
        mPresenter = new ProfilePresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_update_member_token, null);
        ButterKnife.bind(this, view);

        builder.setView(view)
                .setTitle(strTokenVerification)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UpdateMemberTokenDialog.this.getDialog().cancel();
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnOk = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.updateMemberToken(getInput());
                    }
                });
            }
        });
        return alertDialog;
    }

    private JsonObject getInput(){
        JsonObject jsonInput = new JsonObject();
        try {

            jsonInput.addProperty("stock_card_token", edtToken.getText().toString());
            jsonInput.addProperty("stock_card_pin", edtPinNumber.getText().toString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonInput;
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
    public void showProfile(JsonObject results) {

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
        getDialog().dismiss();
        Helper.createAlert(getActivity(), "Success", "Berhasil update member");
    }
}
