package com.bijakpay.member.features.personal_report;

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
 * Created by agustinaindah on 02/05/2017.
 */

public class PersonalReportFragment extends BaseFragment {

    @BindView(R.id.rvPersonalReport)
    RecyclerView rvPersonalReport;

    @BindString(R.string.personal_report)
    String strPersonalReport;
    @BindString(R.string.report_trans_ppob)
    String strTransPpob;
    @BindString(R.string.report_cashback)
    String strCashback;
    @BindString(R.string.report_commission)
    String strCommission;
    @BindString(R.string.report_cashback_refund)
    String strCashRefund;

    public static PersonalReportFragment newInstance() {
        Bundle args = new Bundle();
        PersonalReportFragment fragment = new PersonalReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_personal_report;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strPersonalReport);
        initView();
        setupAdapter();
    }

    private void initView() {
        rvPersonalReport.setHasFixedSize(true);
        rvPersonalReport.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvPersonalReport.addItemDecoration(itemDecoration);
    }

    private void setupAdapter() {
        ArrayList<Simple> personal = new ArrayList<Simple>();
        personal.add(new Simple(Consts.TRANS_PPOB, strTransPpob));
        personal.add(new Simple(Consts.CASHBACK, strCashback));
        personal.add(new Simple(Consts.COMMISSION, strCommission));
        personal.add(new Simple(Consts.CASHBACK_REFUND, strCashRefund));
        PersonalReportAdapter mAdapter = new PersonalReportAdapter(getActivity());
        mAdapter.updateData(personal);
        rvPersonalReport.setAdapter(mAdapter);
    }
}
