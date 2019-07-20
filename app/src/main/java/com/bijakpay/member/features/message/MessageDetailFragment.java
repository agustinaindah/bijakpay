package com.bijakpay.member.features.message;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.model.ItemMessage;
import com.bijakpay.member.model.ItemMessageDetail;
import com.bijakpay.member.model.ItemMessageHeader;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public class MessageDetailFragment extends BaseFragment implements MessageDetailPresenter.View {

    @BindView(R.id.rvMessageDetail)
    RecyclerView rvMessageDetail;
    @BindView(R.id.edtContent)
    EditText edtContent;
    @BindView(R.id.btnSend)
    Button btnSend;

    private ProgressDialog progressDialog;
    private ItemMessage mMessage;
    private MessageDetailPresenterImpl mPresenter;
    private MessageDetailAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static MessageDetailFragment newInstance(ItemMessage itemMessage) {
        Bundle args = new Bundle();
        args.putSerializable(Consts.ARG_DATA, itemMessage);
        MessageDetailFragment fragment = new MessageDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_message_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mMessage = (ItemMessage) getArguments().getSerializable(Consts.ARG_DATA);
        mPresenter = new MessageDetailPresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(mMessage.getMessageSubject());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMessageDetail(mMessage.getMessageId());
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    private JsonObject getInput(){
        JsonObject jsonInput = new JsonObject();
        try {
            String messageContent = edtContent.getText().toString();

            jsonInput.addProperty("message_id", mMessage.getMessageId());
            jsonInput.addProperty("target_email", mMessage.getMessageRecipient());
            jsonInput.addProperty("content", messageContent);
            jsonInput.addProperty("file", "null");

        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonInput;
    }

    @Override
    public boolean validate() {
        edtContent.setError(null);

        boolean cancel = false;
        View focus = null;

        if (TextUtils.isEmpty(getInput().get("content").getAsString())){
            edtContent.setError("harus diisi");
            focus = edtContent;
            cancel = true;
        }
        if (cancel){
            focus.requestFocus();
        }
        return cancel;
    }

    @OnClick(R.id.btnSend)
    public void sendMessage(View view){
        mPresenter.postReplyMessage(getInput());
    }

    @Override
    public void successReply() {
        Helper.createAlert(getActivity(), "Success", "Pesan berhasil terkirim", false, new CallbackInterface() {
            @Override
            public void callback() {
                edtContent.setText("");
                mPresenter.getMessageDetail(mMessage.getMessageId());
            }
        });
    }

    @Override
    public void succesMessage(List<ItemMessageDetail> itemMessageDetails) {
        itemMessageDetails.add(0, getMessageDetailByMessage());

        mAdapter = new MessageDetailAdapter(getActivity());
        mAdapter.updateData(itemMessageDetails);

        rvMessageDetail.setNestedScrollingEnabled(true);
        rvMessageDetail.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMessageDetail.setLayoutManager(linearLayoutManager);
        rvMessageDetail.setAdapter(mAdapter);
    }

    private ItemMessageDetail getMessageDetailByMessage(){
        ItemMessageDetail itemMessageDetail = new ItemMessageDetail();
        itemMessageDetail.setMessageReplySender(mMessage.getMessageSender());
        itemMessageDetail.setMessageReplyContent(mMessage.getMessageContent());
        return itemMessageDetail;
    }
}
