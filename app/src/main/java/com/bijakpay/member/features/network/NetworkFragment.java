package com.bijakpay.member.features.network;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.custom.DividerItemDecoration;
import com.bijakpay.member.features.ReportAdapter;
import com.bijakpay.member.features.ReportFragment;
import com.bijakpay.member.model.Simple;
import com.bijakpay.member.utils.Consts;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by agustinaindah on 22/08/2017.
 */

public class NetworkFragment extends BaseFragment{

    @BindView(R.id.rvNetwork)
    RecyclerView rvNetwork;

    @BindString(R.string.label_my_network)
    String strTitleMyNetwork;
    @BindString(R.string.label_unilevel)
    String strTitleUnilevel;
    @BindString(R.string.label_my_channel)
    String strTitleMyChannel;

    public static NetworkFragment newInstance() {
        Bundle args = new Bundle();
        NetworkFragment fragment = new NetworkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_network;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strTitleMyNetwork);
        initView();
        setupAdapter();
    }

    private void initView() {
        rvNetwork.setHasFixedSize(true);
        rvNetwork.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvNetwork.addItemDecoration(itemDecoration);
    }

    private void setupAdapter() {
        ArrayList<Simple> report = new ArrayList<Simple>();
        report.add(new Simple(Consts.UNILEVEL, strTitleUnilevel));
        report.add(new Simple(Consts.CHANNEL, strTitleMyChannel));
        NetworkAdapter mAdapter  = new NetworkAdapter(getActivity());
        mAdapter.updateDate(report);
        rvNetwork.setAdapter(mAdapter);
    }
}
