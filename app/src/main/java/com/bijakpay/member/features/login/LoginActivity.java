package com.bijakpay.member.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.login.forgot_password.ForgotPasswordDialog;
import com.bijakpay.member.features.register.RegisterActivity;
import com.bijakpay.member.features.main.MainActivity;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 26/04/2017.
 */

public class LoginActivity extends BaseActivity implements LoginPresenter.View {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnToSignup)
    Button btnToSignup;
    @BindView(R.id.txtForgotPassword)
    TextView txtForgotPassword;

    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.label_login)
    String strLogin;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;

    private LoginPresenter mPresenter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        mPresenter = new LoginPresenterImpl(this);
    }

    @OnClick({R.id.btnLogin, R.id.txtForgotPassword, R.id.btnToSignup})
    public void login(View view){
        try{
            Helper.hideKeyboard(this);
        } catch (Exception e){
            e.printStackTrace();
        }
        switch (view.getId()){
            case R.id.btnLogin:
                if (!validate()){
                    mPresenter.login(getInput());
                }
                break;
            case R.id.txtForgotPassword:
                DialogFragment dialogFragment = ForgotPasswordDialog.newInstance();
                dialogFragment.show(getSupportFragmentManager(), Consts.DIALOG);
                break;
            case R.id.btnToSignup:
                gotoActivity(RegisterActivity.class, true);
                break;
        }
    }

    private boolean validate() {
        edtEmail.setError(null);
        edtPassword.setError(null);

        boolean cancel = false;
        View focus = null;

        if (TextUtils.isEmpty(getInput().get("password").getAsString())) {
            edtPassword.setError(strMsgEmpty);
            focus = edtPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("username").getAsString())) {
            edtEmail.setError(strMsgEmpty);
            focus = edtEmail;
            cancel = true;
        } else if (!Helper.isEmail(getInput().get("username").getAsString())) {
            edtEmail.setError(getString(R.string.msg_not_valid));
            focus = edtEmail;
            cancel = true;
        }
        if (cancel) {
            focus.requestFocus();
        }
        return cancel;
    }

    private JsonObject getInput() {
        JsonObject jsonInput = new JsonObject();
        try {
            jsonInput.addProperty("username", edtEmail.getText().toString());
            jsonInput.addProperty("password", edtPassword.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;
    }

    @Override
    protected int setView() {
        return R.layout.activity_login;
    }

    @Override
    public void showProgress() {
        setLoading(false);
    }

    @Override
    public void hideProgress() {
        setLoading(true);
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, strInfoNotConnect);
        setLoading(true);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, strInfoNotConnect);
    }

    private void setLoading(boolean isDisabled) {
        btnLogin.setText((isDisabled) ? strLogin : strLoading);
        btnLogin.setEnabled(isDisabled);

        edtEmail.setEnabled(isDisabled);
        edtPassword.setEnabled(isDisabled);
    }

    @Override
    public void successLogin(JsonObject user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
