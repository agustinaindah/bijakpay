package com.bijakpay.member.features.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.login.LoginActivity;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 28/04/2017.
 */

public class RegisterActivity extends BaseActivity implements RegisterPresenter.View{

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.btnToLogin)
    Button btnToLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.msg_success_register)
    String strSuccessRegister;
    @BindString(R.string.label_signup)
    String strSignup;
    @BindString(R.string.loading)
    String strLoading;

    private RegisterPresenter mPresenter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        mPresenter = new RegisterPresenterImpl(this);
    }

    @Override
    protected int setView() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.btnRegister, R.id.btnToLogin})
    public void register(View view){
        try{
            Helper.hideKeyboard(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        switch (view.getId()){
            case R.id.btnRegister:
                if (!validate()){
                    mPresenter.postRegsiter(getInput());
                }
                break;
            case R.id.btnToLogin:
                goToLogin();
                break;
        }
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
        Helper.createAlert(this, Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, msg);
    }

    @Override
    public boolean validate() {
        edtName.setError(null);
        edtEmail.setError(null);
        edtPassword.setError(null);
        edtPhone.setError(null);

        boolean cancel = false;
        View focus = null;
        if (TextUtils.isEmpty(getInput().get("handphone").getAsString())){
            edtPhone.setError(strMsgEmpty);
            focus = edtPhone;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("password").getAsString())){
            edtPassword.setError(strMsgEmpty);
            focus = edtPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("email").getAsString())){
            edtEmail.setError(strMsgEmpty);
            focus = edtEmail;
            cancel = true;
        } else if (!Helper.isEmail(getInput().get("email").getAsString())){
            edtEmail.setError(getString(R.string.msg_not_valid));
            focus = edtEmail;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("nama").getAsString())){
            edtName.setError(strMsgEmpty);
            focus = edtName;
            cancel = true;
        }
        if (cancel){
            focus.requestFocus();
        }
        return cancel;
    }

    private JsonObject getInput(){
        JsonObject jsonInput = new JsonObject();
        try{
            jsonInput.addProperty("nama",edtName.getText().toString());
            jsonInput.addProperty("email", edtEmail.getText().toString());
            jsonInput.addProperty("password", edtPassword.getText().toString());
            jsonInput.addProperty("handphone", edtPhone.getText().toString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonInput;
    }

    @Override
    public void success(JsonObject jsonRes) {
        edtName.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
        edtPhone.setText("");
        Helper.createAlert(this, Consts.STR_INFO, strSuccessRegister, false, new CallbackInterface() {
            @Override
            public void callback() {
                goToLogin();
            }
        });
    }

    private void setLoading(boolean isDisabled){
        btnRegister.setText((isDisabled) ? strSignup : strLoading);
        btnRegister.setEnabled(isDisabled);
        edtEmail.setEnabled(isDisabled);
        edtPassword.setEnabled(isDisabled);
        edtName.setEnabled(isDisabled);
        edtPhone.setEnabled(isDisabled);
    }

    public void goToLogin(){
        gotoActivity(LoginActivity.class, true);
    }
}
