package com.bijakpay.member.features.login.forgot_password;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 21/06/2017.
 */

public class ForgotPasswordDialog extends DialogFragment implements ForgotPasswordPresenter.View {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.btnResetPassword)
    Button btnResetPassword;

    private ForgotPasswordPresenter mPresenter;
    private ProgressDialog progressDialog;

    public static ForgotPasswordDialog newInstance() {
        Bundle args = new Bundle();
        ForgotPasswordDialog fragment = new ForgotPasswordDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new ForgotPasswordPresenterImpl(this);
    }

    private void initLoading() {
        progressDialog= new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_forgot_password,null);
        ButterKnife.bind(this, view);

        builder.setView(view).setTitle("Recover Password");
        final AlertDialog alertDialog = builder.create();
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

        edtEmail.setError(null);

        boolean cancel = false;
        View focus = null;
        if(TextUtils.isEmpty(getInput().get("email").getAsString())){
            edtEmail.setError("empty");
            focus = edtEmail;
            cancel = true;
        } else if (!Helper.isEmail(getInput().get("email").getAsString())){
            edtEmail.setError("not valid");
            focus = edtEmail;
            cancel = false;
        }
        if (cancel) {
            focus.requestFocus();
        }
        return cancel;
    }

    @Override
    public void success(JsonObject jsonRes) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, "Konfirmasi ke email", false, new CallbackInterface() {
            @Override
            public void callback() {
                ForgotPasswordDialog.this.getDialog().cancel();
            }
        });
    }
    private JsonObject getInput(){
        JsonObject jsonRequest = new JsonObject();
        try {

            jsonRequest.addProperty("email", edtEmail.getText().toString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonRequest;
    }

    @OnClick(R.id.btnResetPassword)
    public void forgot(View view){
        if(!validate()){
            mPresenter.postForgotPassword(getInput());
        }
    }

}
