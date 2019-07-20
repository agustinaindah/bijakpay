package com.bijakpay.member.features.community;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 30/08/2017.
 */

public class CreateCommunityDialog extends DialogFragment implements
        CreateCommunityPresenter.View {

    @BindView(R.id.edtTitleCom)
    EditText edtTitleCom;
    @BindView(R.id.edtTypeCom)
    EditText edtTypeCom;
    @BindView(R.id.edtDescCom)
    EditText edtDescCom;
    @BindView(R.id.btnSave)
    Button btnSave;

    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.create_comm)
    String strTitleCreateComm;
    @BindString(R.string.msg_create_comm)
    String strMsgSuccessComm;

    private CreateCommunityPresenter mPresenter;
    private ProgressDialog progressDialog;

    public static CreateCommunityDialog newInstance() {
        Bundle args = new Bundle();
        CreateCommunityDialog fragment = new CreateCommunityDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new CreateCommunityPresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_create_community, null);
        ButterKnife.bind(this, view);

        builder.setView(view)
                .setTitle(strTitleCreateComm);
        final AlertDialog alertDialog = builder.create();

        return alertDialog;
    }

    @OnClick(R.id.btnSave)
    public void saveComm(View view){
        if (!valiate()){
            mPresenter.postCreateCom(getInput());
        }
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
    public boolean valiate() {
        edtTitleCom.setError(null);
        edtTypeCom.setError(null);
        edtDescCom.setError(null);

        boolean cancel = false;
        View focus = null;
        if (TextUtils.isEmpty(getInput().get("title").getAsString())){
            edtTitleCom.setError(strMsgEmpty);
            focus = edtTitleCom;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("member_type").getAsString())){
            edtTypeCom.setError(strMsgEmpty);
            focus = edtTypeCom;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("desc").getAsString())){
            edtDescCom.setError(strMsgEmpty);
            focus = edtDescCom;
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
            jsonInput.addProperty("title", edtTitleCom.getText().toString());
            jsonInput.addProperty("member_type", edtTypeCom.getText().toString());
            jsonInput.addProperty("desc", edtDescCom.getText().toString());

        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonInput;
    }


    @Override
    public void success(JsonObject jsonRes) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, strMsgSuccessComm, false, new CallbackInterface() {
            @Override
            public void callback() {
                CreateCommunityDialog.this.getDialog().cancel();
            }
        });
    }
}
