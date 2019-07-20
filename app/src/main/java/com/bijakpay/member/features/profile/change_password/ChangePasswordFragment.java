package com.bijakpay.member.features.profile.change_password;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
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
 * Created by agustinaindah on 12/05/2017.
 */

public class ChangePasswordFragment extends BaseFragment implements ChangePasswordPresenter.View{

    @BindView(R.id.edtOldPassword)
    EditText edtOldPassword;
    @BindView(R.id.edtNewPassword)
    EditText edtNewPassword;
    @BindView(R.id.edtConfirmNewPassword)
    EditText edtConfirmNewPassword;

    @BindString(R.string.password_change)
    String strPasswordChange;
    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.text_update_data_success)
    String strUpdateDataSuccess;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.msg_success)
    String strSuccess;
    @BindString(R.string.check_again)
    String strCheckAgain;

    private ProgressDialog progressDialog;
    private ChangePasswordPresenter mPresenter;

    public static ChangePasswordFragment newInstance() {
        Bundle args = new Bundle();
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_change_password;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new ChangePasswordPresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strPasswordChange);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @OnClick(R.id.btnSimpanChangePassword)
    public void saveChangePassword() {
        try {
            Helper.hideKeyboard(getActivity());
        } catch (Exception e) {
            }
        mPresenter.changePassword(getInput());
    }

    @Override
    public boolean validate() {
        edtOldPassword.setError(null);
        edtNewPassword.setError(null);
        edtConfirmNewPassword.setError(null);

        boolean cancel = false;
        View focus = null;

        if (!getInput().get("member_confirm_new_password").getAsString()
                .equals(getInput().get("member_new_password").getAsString())) {
            edtConfirmNewPassword.setError(strCheckAgain);
            focus = edtConfirmNewPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_new_password").getAsString())) {
            edtNewPassword.setError(strMsgEmpty);
            focus = edtNewPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_old_password").getAsString())) {
            edtOldPassword.setError(strMsgEmpty);
            focus = edtOldPassword;
            cancel = true;
        }

        if (cancel) {
            focus.requestFocus();
        }
        return cancel;
    }

    private JsonObject getInput(){
        JsonObject jsonInput = new JsonObject();
        try {
            String oldPassword          = edtOldPassword.getText().toString();
            String newPassword          = edtNewPassword.getText().toString();
            String confirmNewPassword   = edtConfirmNewPassword.getText().toString();

            jsonInput.addProperty("member_old_password", oldPassword);
            jsonInput.addProperty("member_new_password", newPassword);
            jsonInput.addProperty("member_confirm_new_password", confirmNewPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;
    }

    @Override
    public void success(JsonObject jsonRes) {
        Helper.createAlert(getActivity(), strSuccess, strUpdateDataSuccess, false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });
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
}
