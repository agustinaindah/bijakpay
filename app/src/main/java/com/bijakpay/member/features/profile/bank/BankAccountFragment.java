package com.bijakpay.member.features.profile.bank;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.features.profile.AccountSettingsActivity;
import com.bijakpay.member.model.Bank;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 21/05/2017.
 */

public class BankAccountFragment extends BaseFragment implements BankAccountPresenter.View,
        BankAccountAdapter.BankAccountAdapterListener {


    @BindView(R.id.btnAddAccountBank)
    Button btnAddAccountBank;
    /*@BindView(R.id.txtAccountTitle)
    TextView txtAccountTitle;
    @BindView(R.id.imgEditAccount)
    ImageView imgEditAccount;
    @BindView(R.id.imgDeleteAccount)
    ImageView imgDeleteAccount;
    @BindView(R.id.txtAccountName)
    TextView txtAccountName;
    @BindView(R.id.txtAccountNumber)
    TextView txtAccountNumber;
    @BindView(R.id.txtBankName)
    TextView txtBankName;
    @BindView(R.id.txtBranchBank)
    TextView txtBranchBank;*/
    @BindView(R.id.rvBank)
    RecyclerView rvBank;
    /* @BindView(R.id.fabAddAccountBank)
     FloatingActionButton fabAddAccountBank;*/
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    /*@BindView(R.id.layPrimaryBank)
    LinearLayout layPrimaryBank;*/

    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.success_remove)
    String strSuccessDelete;
    @BindString(R.string.label_account_bank)
    String strTitleAccountBank;

    private BankAccountPresenter mPresenter;
    private BankAccountAdapter mAdapter;
    private ProgressDialog progressDialog;
    private Bank mPrimaryBank;

    public static BankAccountFragment newInstance() {
        Bundle args = new Bundle();
        BankAccountFragment fragment = new BankAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_account_bank;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new BankAccountPresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strTitleAccountBank);
        prepareBank();
//        primaryBank(mPrimaryBank);

    }

    private void prepareBank() {
        mPresenter.getBankAccount();
       /* imgDeleteAccount.setVisibility(View.GONE);*/
    }

    /*@OnClick(R.id.imgEditAccount)
    public void editMainAccount(){
        Fragment fragment = BankAccountFormFragment.newInstance(Consts.UPDATE, mPrimaryBank, Consts.UTAMA);
        AccountSettingsActivity activity = ((AccountSettingsActivity) getActivity());
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }*/

    @OnClick(R.id.btnAddAccountBank)
    public void addAccount(){
        Fragment fragment = BankAccountFormFragment.newInstance(Consts.ADD);
        AccountSettingsActivity activity = ((AccountSettingsActivity) getActivity());
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
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

    /*public void primaryBank(Bank mPrimaryBank){
        txtNoData.setVisibility(View.GONE);
        layPrimaryBank.setVisibility(View.VISIBLE);
        txtAccountName.setText(mPrimaryBank.getMemberBankAccountAs());
        txtAccountNumber.setText(mPrimaryBank.getMemberBankAccountOwnerName());
        txtBankName.setText(mPrimaryBank.getMemberBankAccountBankName());
        txtBranchBank.setText(mPrimaryBank.getMemberBankAccountBranch());
        mgEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = AccountBankFormFragment
                        .newInstance(Consts.UPDATE, primaryBank, Consts.UTAMA);
                AccountSettingsActivity activity = ((AccountSettingsActivity) getActivity());
                activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
            }
        }); i
    }*/

    @Override
    public void successPrimary(List<Bank> banks) {
        if (banks.size() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
            btnAddAccountBank.setVisibility(View.GONE);
        } else if (banks.size() !=0){
            txtNoData.setVisibility(View.GONE);
            btnAddAccountBank.setVisibility(View.VISIBLE);
        }
        mAdapter = new BankAccountAdapter(getActivity(), this);
        mAdapter.updateItemBankAccount(banks);
        rvBank.setHasFixedSize(true);
        rvBank.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvBank.setNestedScrollingEnabled(false);
        rvBank.setAdapter(mAdapter);
    }

    @Override
    public void onClickItem(int position, Bank bank) {
//        mPresenter.deleteBank(bank.getMemberBankAccountId());
    }
}
