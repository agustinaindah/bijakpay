package com.bijakpay.member.features;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.custom.DividerItemDecoration;
import com.bijakpay.member.features.bussines_report.BussinesReportAdapter;
import com.bijakpay.member.features.bussines_report.BussinesReportFragment;
import com.bijakpay.member.model.Simple;
import com.bijakpay.member.utils.Consts;

import java.util.ArrayList;
import java.util.concurrent.CompletionService;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by agustinaindah on 21/08/2017.
 */

public class ReportFragment extends BaseFragment {

    @BindView(R.id.rvBussinessReport)
    RecyclerView rvBussinessReport;

    @BindString(R.string.bussines_report)
    String strBussinesReport;
    @BindString(R.string.personal_report)
    String strPersonalReport;
    @BindString(R.string.report)
    String strReport;

    public static ReportFragment newInstance() {
        Bundle args = new Bundle();
        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_bussines_report;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strReport);
        initView();
        setupAdapter();
    }

    private void initView() {
        rvBussinessReport.setHasFixedSize(true);
        rvBussinessReport.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvBussinessReport.addItemDecoration(itemDecoration);
    }

    private void setupAdapter() {
        ArrayList<Simple> report = new ArrayList<Simple>();
        report.add(new Simple(Consts.PERSONAL, strPersonalReport));
        report.add(new Simple(Consts.BUSSINES, strBussinesReport));
        ReportAdapter mAdapter  = new ReportAdapter(getActivity());
        mAdapter.updateDate(report);
        rvBussinessReport.setAdapter(mAdapter);
    }
}
