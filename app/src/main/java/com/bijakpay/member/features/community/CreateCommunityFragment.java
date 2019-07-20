package com.bijakpay.member.features.community;

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
 * Created by agustinaindah on 25/08/2017.
 */

public class CreateCommunityFragment extends BaseFragment implements CreateCommunityPresenter.View{

    // TODO: 29/08/2017 besok diubah sdkt di tampilannya

    @BindView(R.id.edtTitleCom)
    EditText edtTitleCom;
    @BindView(R.id.edtTypeCom)
    EditText edtTypeCom;
    @BindView(R.id.edtDescCom)
    EditText edtDescCom;
    @BindView(R.id.btnSave)
    Button btnSave;

    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.msg_succes_comm)
    String strSuccess;

    private ProgressDialog progressDialog;
    private CreateCommunityPresenter mPresenter;

    public static CreateCommunityFragment newInstance() {
        Bundle args = new Bundle();
        CreateCommunityFragment fragment = new CreateCommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgress();
        mPresenter = new CreateCommunityPresenterImpl(this);
    }

    private void initProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @OnClick(R.id.btnSave)
    public void saveComm(View view){
        if (!valiate()){
            mPresenter.postCreateCom(getInput());
        }
    }

    @Override
    protected int setView() {
        return R.layout.fragment_create_community;
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, strSuccess, false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });
    }
}
