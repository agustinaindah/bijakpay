package com.bijakpay.member.features.bussines_report;

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

public class BussinesReportFragment extends BaseFragment {

    @BindView(R.id.rvBussinessReport)
    RecyclerView rvBussinessReport;

    @BindString(R.string.bussines_report)
    String strBussinesReport;
    @BindString(R.string.report_membership)
    String strMembership;
    @BindString(R.string.report_royalty)
    String strRoyalty;
    @BindString(R.string.report_royalty_refund)
    String strRoyaltyRefund;
    @BindString(R.string.report_stock_card)
    String strStockCard;

    public static BussinesReportFragment newInstance() {
        Bundle args = new Bundle();
        BussinesReportFragment fragment = new BussinesReportFragment();
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
        getActivity().setTitle(strBussinesReport);
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
        ArrayList<Simple> bussines = new ArrayList<Simple>();
        bussines.add(new Simple(Consts.MEMBERSHIP, strMembership));
        bussines.add(new Simple(Consts.ROYALTY, strRoyalty));
        bussines.add(new Simple(Consts.ROYALTY_REFUND, strRoyaltyRefund));
        bussines.add(new Simple(Consts.CARD_STOCK, strStockCard));
        BussinesReportAdapter mAdapter  = new BussinesReportAdapter(getActivity());
        mAdapter.updateDate(bussines);
        rvBussinessReport.setAdapter(mAdapter);
    }
}
