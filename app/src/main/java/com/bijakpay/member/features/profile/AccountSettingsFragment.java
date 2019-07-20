package com.bijakpay.member.features.profile;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.custom.DividerItemDecoration;
import com.bijakpay.member.model.Simple;
import com.bijakpay.member.utils.Consts;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public class AccountSettingsFragment extends BaseFragment {

    @BindView(R.id.rvMyProfile)
    RecyclerView rvMyProfile;

    @BindString(R.string.label_account)
    String strTitleAccSettings;
    @BindString(R.string.label_profile)
    String strMyProfile;
    @BindString(R.string.label_account_bank)
    String strBankAccount;
    @BindString(R.string.label_member_type)
    String strUpgradeMember;

    public static AccountSettingsFragment newInstance() {
        Bundle args = new Bundle();
        AccountSettingsFragment fragment = new AccountSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_my_profile;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strTitleAccSettings);
        initView();
        setupAdapter();
    }

    private void initView() {
        rvMyProfile.setHasFixedSize(true);
        rvMyProfile.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvMyProfile.addItemDecoration(itemDecoration);
    }

    private void setupAdapter() {
        ArrayList<Simple> profile = new ArrayList<Simple>();
        profile.add(new Simple(Consts.PROFILE, strMyProfile));
        /*profile.add(new Simple(Consts.ADDRESS, "Address"));*/
        profile.add(new Simple(Consts.BANK_ACCOUNT, strBankAccount));
        /*profile.add(new Simple(Consts.CHANGE_PASSWORD, "Change Password"));*/
        profile.add(new Simple(Consts.UPGRADE_MEMBER, strUpgradeMember));
        MyProfileAdapter mAdapter = new MyProfileAdapter(getActivity());
        mAdapter.updateData(profile);
        rvMyProfile.setAdapter(mAdapter);
    }
}
