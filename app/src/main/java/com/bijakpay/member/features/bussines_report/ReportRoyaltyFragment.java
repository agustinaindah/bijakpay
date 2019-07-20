package com.bijakpay.member.features.bussines_report;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.model.ItemRoyalty;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class ReportRoyaltyFragment extends BaseFragment implements ReportRoyaltyPresenter.View{

    @BindView(R.id.rvMyReport)
    RecyclerView rvMyReport;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private ReportRoyaltyAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastCount = 0;
    private ReportRoyaltyPresenterImpl mPresenter;

    @BindString(R.string.report_royalty)
    String strReportRoyalty;

    public static ReportRoyaltyFragment newInstance() {
        Bundle args = new Bundle();
        ReportRoyaltyFragment fragment = new ReportRoyaltyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_my_report;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ReportRoyaltyPresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strReportRoyalty);
        mPresenter.getReportRoyalty(Consts.FIRST_PAGE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMyReport.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getReportRoyalty(nextPage);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressBar != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        }, 2000);
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getReportRoyalty(Consts.FIRST_PAGE);
    }

    @Override
    public void successListRoyalty(List<ItemRoyalty> itemRoyalties, int page) {
        lastCount = itemRoyalties.size();

        if (page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemRoyalties.size() == 0) ? View.VISIBLE : View.GONE);

            mAdapter = new ReportRoyaltyAdapter(itemRoyalties, getActivity());

            rvMyReport.setHasFixedSize(true);
            rvMyReport.setLayoutManager(linearLayoutManager);
            rvMyReport.setAdapter(mAdapter);
            rvMyReport.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.up_from_bottom);
            rvMyReport.startAnimation(animation);

        } else {
            for (ItemRoyalty itemRoyalty : itemRoyalties){
                mAdapter.add(itemRoyalty);
                mAdapter.notifyDataSetChanged();
            }

        }
    }
}
