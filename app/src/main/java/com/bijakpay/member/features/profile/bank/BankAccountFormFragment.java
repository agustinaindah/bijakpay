package com.bijakpay.member.features.profile.bank;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.features.profile.bank.BankAccountFormPresenter;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.model.Bank;
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

public class BankAccountFormFragment extends BaseFragment implements BankAccountFormPresenter.View {

    @BindView(R.id.edtBankAccountAs)
    EditText edtBankAccountAs;
    @BindView(R.id.edtAccountName)
    EditText edtAccountName;
    @BindView(R.id.edtAccountNumber)
    EditText edtAccountNumber;
    @BindView(R.id.edtBankName)
    EditText edtBankName;
    @BindView(R.id.edtBranch)
    EditText edtBranch;
    @BindView(R.id.edtCodeOTP)
    EditText edtCodeOTP;
    @BindView(R.id.btnCodeOTP)
    Button btnCodeOTP;
    @BindView(R.id.btnSave)
    Button btnSave;

    @BindString(R.string.text_saved_data)
    String strSavedDataBankForm;
    @BindString(R.string.text_send_code)
    String strSendCode;
    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.account_bank)
    String strBankAccount;
    @BindString(R.string.add)
    String strAdd;
    @BindString(R.string.edit)
    String strEdit;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.msg_success)
    String strSuccess;

    private ProgressDialog progressDialog;
    private BankAccountFormPresenter mPresenter;
    private int mType;
    private Bank mBank;
    private String bankType;

    public static BankAccountFormFragment newInstance(int type, Bank bank, String bankType) {

        Bundle args = new Bundle();
        args.putInt(Consts.ARG_TYPE, type);
        args.putSerializable(Consts.ARG_DATA, bank);
        args.putString(Consts.ARG_CODE, bankType);
        BankAccountFormFragment fragment = new BankAccountFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static BankAccountFormFragment newInstance(int type) {
        return newInstance(type, null, null);
    }

    @Override
    protected int setView() {
        return R.layout.fragment_account_bank_form;
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg, false, new CallbackInterface() {
            @Override
            public void callback() {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public boolean validate() {
        edtBankAccountAs.setError(null);
        edtAccountName.setError(null);
        edtAccountNumber.setError(null);
        edtBankName.setError(null);
        edtBranch.setError(null);
        edtCodeOTP.setError(null);

        boolean cancel = false;
        View focus = null;

        if (TextUtils.isEmpty(getInput().get("member_bank_account_sms_code").getAsString())) {
            edtCodeOTP.setError(strMsgEmpty);
            focus = edtCodeOTP;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_bank_account_branch").getAsString())) {
            edtBranch.setError(strMsgEmpty);
            focus = edtBranch;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_bank_account_bank_name").getAsString())) {
            edtBankName.setError(strMsgEmpty);
            focus = edtBankName;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_bank_account_number").getAsString())) {
            edtAccountNumber.setError(strMsgEmpty);
            focus = edtAccountNumber;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_bank_account_owner_name").getAsString())) {
            edtAccountName.setError(strMsgEmpty);
            focus = edtAccountName;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_bank_account_as").getAsString())) {
            edtBankAccountAs.setError(strMsgEmpty);
            focus = edtBankAccountAs;
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
            String otpCode = edtCodeOTP.getText().toString();
            String branch = edtBranch.getText().toString();
            String bankName = edtBankName.getText().toString();
            String accountNumber = edtAccountNumber.getText().toString();
            String accountName = edtAccountName.getText().toString();
            String accountAs = edtBankAccountAs.getText().toString();

            jsonInput.addProperty("member_bank_account_sms_code", otpCode);
            jsonInput.addProperty("member_bank_account_branch", branch);
            jsonInput.addProperty("member_bank_account_bank_name", bankName);
            jsonInput.addProperty("member_bank_account_number", accountNumber);
            jsonInput.addProperty("member_bank_account_owner_name", accountName);
            jsonInput.addProperty("member_bank_account_as",accountAs);
            if (mType == Consts.UPDATE) {
                jsonInput.addProperty("member_bank_account_id", mBank.getMemberBankAccountId());
                jsonInput.addProperty("member_bank_account_as", bankType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;
    }

    @Override
    public void success() {
        Helper.createAlert(getActivity(), strSuccess, strSavedDataBankForm, false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });
    }

    @Override
    public void successSendCode() {
        Helper.createAlert(getActivity(), strSuccess, strSendCode);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mType = getArguments().getInt(Consts.ARG_TYPE);

        mPresenter = new BankAccountFormPresenterImpl(this);
        if (mType == Consts.UPDATE) {
            mBank = (Bank) getArguments().getSerializable(Consts.ARG_DATA);
            bankType = getArguments().getString(Consts.ARG_CODE);
        }
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = ((mType == Consts.ADD) ? strAdd : strEdit) + " " + strBankAccount;
        getActivity().setTitle(title);
        if (mType == Consts.UPDATE) {
            setupEdit(mBank);
        }
    }

    private void setupEdit(Bank mBank) {
        edtBankAccountAs.setText(mBank.getMemberBankAccountAs());
        edtAccountName.setText(mBank.getMemberBankAccountOwnerName());
        edtAccountNumber.setText(mBank.getMemberBankAccountNumber());
        edtBankName.setText(mBank.getMemberBankAccountBankName());
        edtBranch.setText(mBank.getMemberBankAccountBranch());
    }

    @OnClick(R.id.btnSave)
    public void saveBankAccount() {
        if (mType == Consts.ADD) {
            mPresenter.postAccountBank(getInput());
        } else if (mType == Consts.UPDATE) {
            mPresenter.updateAccountBank(getInput());
        }
    }

    @OnClick(R.id.btnCodeOTP)
    public void requestOTPCode() {
        try {
            mPresenter.requestCode(
                    mType, (mType == Consts.UPDATE) ? mBank.getMemberBankAccountId() : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
