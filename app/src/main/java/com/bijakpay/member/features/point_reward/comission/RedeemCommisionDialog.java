package com.bijakpay.member.features.point_reward.comission;

import android.app.Dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.features.point_reward.BonusPresenterImpl;
import com.bijakpay.member.features.point_reward.RedeemBonusPresenter;
import com.bijakpay.member.features.point_reward.cashback.RedeemCashbackDialog;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 22/06/2017.
 */

public class RedeemCommisionDialog extends DialogFragment implements RedeemBonusPresenter.View{

    // TODO: 22/06/2017 sabas sek yaa

    @BindView(R.id.edtNominal)
    EditText edtNominal;

    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.label_commission)
    String strTitleCommission;
    @BindString(R.string.msg_success)
    String strSuccess;

    private ProgressDialog progressDialog;
    private BonusPresenterImpl mPresenter;

    public static RedeemCommisionDialog newInstance() {
        Bundle args = new Bundle();
        RedeemCommisionDialog fragment = new RedeemCommisionDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new BonusPresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_point, null);
        ButterKnife.bind(this, view);

        builder.setView(view)
                .setTitle(strTitleCommission)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RedeemCommisionDialog.this.getDialog().cancel();
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
                        if(!validate()){
                            mPresenter.postRedeemPoint(getInput());
                        }
                    }
                });
            }
        });
        return alertDialog;
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
    public boolean validate() {
        edtNominal.setError(null);

        boolean cancel = false;
        View focus = null;
        if (TextUtils.isEmpty(getInput().get("nominal").getAsString())){
            edtNominal.setError("empty");
            focus = edtNominal;
            cancel = true;
        }
        if (cancel){
            focus.requestFocus();
        }
        return cancel;
    }

    private JsonObject getInput(){
        JsonObject jsonRequest = new JsonObject();
        try {
            jsonRequest.addProperty("type","commission");
            jsonRequest.addProperty("nominal", edtNominal.getText().toString());

        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonRequest;
    }

    @Override
    public void success(JsonObject jsonRes) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, strSuccess, false, new CallbackInterface() {
            @Override
            public void callback() {
                RedeemCommisionDialog.this.getDialog().cancel();
            }
        });
    }
}
