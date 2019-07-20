package com.bijakpay.member.features.register_friends;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.features.member_type.SysMembershipAdapter;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 05/05/2017.
 */

public class RegisterFriendEwalletFragment extends BaseFragment
        implements RegisterFriendEwalletPresenter.View {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    /* @BindView(R.id.edtMemberType)
     EditText edtMemberType;
     @BindView(R.id.edtPrice)
     EditText edtPrice;*/
    @BindView(R.id.spinMembershipType)
    Spinner spinMembershipType;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
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
    private RegisterFriendEwalletPresenter mPresenter;
    private SysMembershipAdapter sysMembershipAdapter;

    public static RegisterFriendEwalletFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFriendEwalletFragment fragment = new RegisterFriendEwalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgress();
        mPresenter = new RegisterFriendEwalletPresenterImpl(this);

       /* mPresenter.getSysMember();

        spinMembershipType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spinMembershipType.setTag(sysMembershipAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.getSysMember();
        spinMembershipType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spinMembershipType.setTag(sysMembershipAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void initProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @OnClick(R.id.btnSubmit)
    public void submitEwallet(View view) {
        if (!validate()) {
            mPresenter.postRegEwallet(getInput());
        }
    }

    @Override
    protected int setView() {
        return R.layout.fragment_reg_via_ewallet;
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
//        edtMemberType.setError(null);
//        edtPrice.setError(null);

        boolean cancel = false;
        View focus = null;
      /*  if (TextUtils.isEmpty(getInput().get("price").getAsString())) {
            edtPrice.setError(strMsgEmpty);
            focus = edtPrice;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("type").getAsString())) {
            edtMemberType.setError(strMsgEmpty);
            focus = edtMemberType;
            cancel = true;
        }*/
        if (TextUtils.isEmpty(getInput().get("handphone").getAsString())) {
            edtPhone.setError(strMsgEmpty);
            focus = edtPhone;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("password").getAsString())) {
            edtPassword.setError(strMsgEmpty);
            focus = edtPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("email").getAsString())) {
            edtEmail.setError(strMsgEmpty);
            focus = edtEmail;
            cancel = true;
        } else if (!Helper.isEmail(getInput().get("email").getAsString())) {
            edtEmail.setError(getString(R.string.msg_not_valid));
            focus = edtEmail;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("nama").getAsString())) {
            edtName.setError(strMsgEmpty);
            focus = edtName;
            cancel = true;
        }
        if (cancel) {
            focus.requestFocus();
        }

        return cancel;
    }

    private JsonObject getInput() {
        SysMembership sysMembership = (SysMembership) spinMembershipType.getTag();
        JsonObject jsonInput = new JsonObject();
        try {
            jsonInput.addProperty("nama", edtName.getText().toString());
            jsonInput.addProperty("email", edtEmail.getText().toString());
            jsonInput.addProperty("password", edtPassword.getText().toString());
            jsonInput.addProperty("handphone", edtPhone.getText().toString());
            jsonInput.addProperty("type", String.valueOf(sysMembership.getSysMembershipTypeName()));
            jsonInput.addProperty("price", String.valueOf(sysMembership.getSysMembershipTypePrice()));
//            jsonInput.addProperty("type", edtMemberType.getText().toString());
//            jsonInput.addProperty("price", edtPrice.getText().toString());

        } catch (Exception e) {
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

    @Override
    public void showSysMember(List<SysMembership> sysMemberships) {
        sysMembershipAdapter = new SysMembershipAdapter(getActivity());
        sysMembershipAdapter.updateSysMembership(sysMemberships);
        spinMembershipType.setAdapter(sysMembershipAdapter);
    }
}
