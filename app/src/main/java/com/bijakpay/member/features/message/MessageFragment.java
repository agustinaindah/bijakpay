package com.bijakpay.member.features.message;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.model.ItemMessage;
import com.bijakpay.member.model.ItemMessageHeader;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public class MessageFragment extends BaseFragment implements MessagePresenter.View,
        MessageAdapter.MessageAdapterListener {

    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.rvMessage)
    RecyclerView rvMessage;

    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;

    private ProgressDialog progressDialog;
    private MessagePresenter mPresenter;
    private MessageAdapter mAdapter;
    private int lastCount = 0;
    private LinearLayoutManager linearLayoutManager;

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_message;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new MessagePresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Message");
        mPresenter.getMessage(Consts.FIRST_PAGE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMessage.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getMessage(nextPage);
                }
            }
        });
    }

    /*@OnClick(R.id.fabAddMessage)
    public void addMessage() {
        Fragment fragment = MessageFormFragment.newInstance();
        MessagesActivity activity = ((MessagesActivity) getActivity());
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }*/

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
        Helper.createAlert(getActivity(), Consts.STR_INFO, strInfoNotConnect, false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });
    }

    @Override
    public void successMessage(List<ItemMessage> itemMessages, int page) {
        lastCount = itemMessages.size();
        if (page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemMessages.size() == 0) ? View.VISIBLE : View.GONE);
            mAdapter = new MessageAdapter(getActivity(), this);
            mAdapter.updateData(itemMessages);
            rvMessage.setHasFixedSize(true);
            rvMessage.setLayoutManager(linearLayoutManager);
            rvMessage.setAdapter(mAdapter);
            rvMessage.setNestedScrollingEnabled(false);
        } else {
            mAdapter.addAll(itemMessages);
        }
    }

    @Override
    public void onClickMessage(int position, ItemMessage itemMessage) {
        /*((BaseActivity) getActivity())
                .gotoFragment(getFragmentManager(),
                        MessageDetailFragment.newInstance(itemMessage), true);*/
    }

}
