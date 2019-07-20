package com.bijakpay.member.features.message;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.model.ItemMessage;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public class MessageFormFragment extends BaseFragment implements MessageFormPresenter.View{

    @BindView(R.id.edtMessageRecipient)
    EditText edtMessageRecipient;
    @BindView(R.id.edtMessageSubject)
    EditText edtMessageSubject;
    @BindView(R.id.edtMessageContent)
    EditText edtMessageContent;
    @BindView(R.id.btnMessageSend)
    Button btnMessageSend;

    private ProgressDialog progressDialog;
    private MessageFormPresenter mPresenter;
    private ItemMessage mMessage;

    public static MessageFormFragment newInstance() {
        Bundle args = new Bundle();
        MessageFormFragment fragment = new MessageFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_message_form;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new MessageFormPresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Kirim Pesan Baru");
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

    @OnClick(R.id.btnMessageSend)
    public void submitMessage(View view){
        mPresenter.postFormMessage(getInput());
    }

    @Override
    public boolean validate() {
        edtMessageRecipient.setError(null);
        edtMessageSubject.setError(null);
        edtMessageContent.setError(null);

        boolean cancel = false;
        View focus = null;

        if (TextUtils.isEmpty(getInput().get("target_email").getAsString())) {
            edtMessageRecipient.setError("harus diisi");
            focus = edtMessageRecipient;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("subject").getAsString())) {
            edtMessageSubject.setError("harus diisi");
            focus = edtMessageSubject;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("content").getAsString())) {
            edtMessageContent.setError("harus diisi");
            focus = edtMessageContent;
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
            String messageRecipient = edtMessageRecipient.getText().toString();
            String messageSubject = edtMessageSubject.getText().toString();
            String messageContent = edtMessageContent.getText().toString();

            jsonInput.addProperty("target_email", messageRecipient);
            jsonInput.addProperty("subject", messageSubject);
            jsonInput.addProperty("content", messageContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;

    }

    @Override
    public void successMessageForm() {
        Helper.createAlert(getActivity(), "Success", "Pesan Berhasil Terkirim", false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });
    }
}
