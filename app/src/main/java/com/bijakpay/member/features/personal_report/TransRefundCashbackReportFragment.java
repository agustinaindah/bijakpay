package com.bijakpay.member.features.personal_report;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.model.ItemRefundCashback;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public class TransRefundCashbackReportFragment extends BaseFragment
        implements TransRefundCashbackReportPresenter.View{

    @BindView(R.id.rvMyReport)
    RecyclerView rvMyReport;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    /*@BindView(R.id.layError)
    LinearLayout layError;*/

    private TransRefundCashbackReportAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastCount = 0;
    private TransRefundCashbackReportPresenterImpl mPresenter;

    @BindString(R.string.report_cashback_refund)
    String strCashRefund;

    public static TransRefundCashbackReportFragment newInstance() {
        Bundle args = new Bundle();
        TransRefundCashbackReportFragment fragment = new TransRefundCashbackReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TransRefundCashbackReportPresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strCashRefund);
        mPresenter.getTransRefundCashback(Consts.FIRST_PAGE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMyReport.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getTransRefundCashback(nextPage);
                }
            }
        });
    }

    @Override
    protected int setView() {
        return R.layout.fragment_my_report;
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    @Override
    public void successListTransRefundCashback(List<ItemRefundCashback> itemRefundCashbacks, int page) {
        lastCount = itemRefundCashbacks.size();

        if (page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemRefundCashbacks.size() == 0) ? View.VISIBLE : View.GONE);

            mAdapter = new TransRefundCashbackReportAdapter(itemRefundCashbacks, getActivity());

            rvMyReport.setHasFixedSize(true);
            rvMyReport.setLayoutManager(linearLayoutManager);
            rvMyReport.setAdapter(mAdapter);
            rvMyReport.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.up_from_bottom);
            rvMyReport.startAnimation(animation);
        } else {
            for (ItemRefundCashback itemRefundCashback : itemRefundCashbacks){
                mAdapter.add(itemRefundCashback);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
