package com.bijakpay.member.features.member_type;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.features.main.MainActivity;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.features.profile.ProfilePresenterImpl;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 06/05/2017.
 */

public class UpdateMemberEwalletDialog extends DialogFragment implements ProfilePresenter.View {

    @BindView(R.id.edtMemberType)
    EditText edtMemberType;
    @BindView(R.id.edtPrice)
    EditText edtPrice;
    @BindView(R.id.edtSaldo)
    EditText edtSaldo;

    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.verify_upgrade)
    String strVerifyUpgrade;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;

    private ProgressDialog progressDialog;
    private ProfilePresenter mPresenter;
    private SysMembership mSys;


    public static UpdateMemberEwalletDialog newInstance(SysMembership sysMembership) {
        Bundle args = new Bundle();
        args.putSerializable(Consts.ARG_DATA, sysMembership);
        UpdateMemberEwalletDialog fragment = new UpdateMemberEwalletDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSys = (SysMembership) getArguments().getSerializable(Consts.ARG_DATA);
        initLoading();
        mPresenter = new ProfilePresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_update_member_ewallet, null);
        ButterKnife.bind(this,view);

        mPresenter.getProfile();

        builder.setView(view)
                .setTitle(strVerifyUpgrade)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UpdateMemberEwalletDialog.this.getDialog().cancel();
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
                        mPresenter.updateMemberEwallet(getInput());
                    }
                });
            }
        });
        return alertDialog;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getDialog() == null)
            return;

        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    private JsonObject getInput(){
        JsonObject jsonInput = new JsonObject();
        try {
            String typeMember     = edtMemberType.getText().toString();
            String price          = edtPrice.getText().toString();

            jsonInput.addProperty("member_type", typeMember);
            jsonInput.addProperty("price", price);
            jsonInput.addProperty("quota", mSys.getSysMembershipTypeQuota());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;
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
        edtSaldo.setText(Helper.numberCurrency(results.get("saldo_ewallet").getAsLong()));
    }

    @Override
    public void successListSys(List<SysMembership> sysMemberships) {

    }

    @Override
    public void successUpdateProfile() {

    }

    @Override
    public void successUpdateEwallet() {
        getDialog().dismiss();
        Helper.createAlert(getActivity(), "Success", "Berhasil update member");
    }

    @Override
    public void successUpdateToken() {

    }
}
