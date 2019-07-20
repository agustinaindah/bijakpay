package com.bijakpay.member.features.register_friends;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 05/05/2017.
 */

public class RegisterFriendTokenFragment extends BaseFragment
        implements RegisterFriendTokenPresenter.View{

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtToken)
    EditText edtToken;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.msg_success_register)
    String strSuccessRegister;

    private ProgressDialog progressDialog;
    private RegisterFriendTokenPresenter mPresenter;

    public static RegisterFriendTokenFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFriendTokenFragment fragment = new RegisterFriendTokenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgress();
        mPresenter =  new RegisterFriendTokenPresenterImpl(this);
    }

    private void initProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @OnClick(R.id.btnSubmit)
    public void submitRegToken(View view){
        if (!validate()){
            mPresenter.postRegToken(getInput());
        }
    }

    @Override
    protected int setView() {
        return R.layout.fragment_reg_via_token;
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, strInfoNotConnect);
    }

    @Override
    public boolean validate() {
        edtName.setError(null);
        edtEmail.setError(null);
        edtPassword.setError(null);
        edtPhone.setError(null);
        edtToken.setError(null);

        boolean cancel = false;
        View focus = null;
        if (TextUtils.isEmpty(getInput().get("token").getAsString())){
            edtToken.setError(strMsgEmpty);
            focus = edtToken;
            cancel = true;
        }
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
        try {
            jsonInput.addProperty("nama",edtName.getText().toString());
            jsonInput.addProperty("email", edtEmail.getText().toString());
            jsonInput.addProperty("password", edtPassword.getText().toString());
            jsonInput.addProperty("handphone", edtPhone.getText().toString());
            jsonInput.addProperty("token", edtToken.getText().toString());

        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonInput;
    }

    @Override
    public void success(JsonObject jsonRes) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, strSuccessRegister, false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });
    }
}
