package com.bijakpay.member.features.personal_report;

import android.os.Bundle;
import android.os.Handler;
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
import com.bijakpay.member.model.ItemPpob;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public class TransPpobReportFragment extends BaseFragment
        implements TransPpobReportPresenter.View{

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

    private TransPpobReportAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lasCount = 0;
    private TransPpobReportPresenterImpl mPresenter;

    @BindString(R.string.report_trans_ppob)
    String strTransPpob;

    public static TransPpobReportFragment newInstance() {
        Bundle args = new Bundle();
        TransPpobReportFragment fragment = new TransPpobReportFragment();
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
        mPresenter = new TransPpobReportPresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strTransPpob);
        mPresenter.getTransPpob(Consts.FIRST_PAGE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMyReport.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lasCount == Consts.LIMIT){
                    mPresenter.getTransPpob(nextPage);
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg, true, new CallbackInterface() {
            @Override
            public void callback() {
                mAdapter.deleteAll();
                mPresenter.getTransPpob(Consts.FIRST_PAGE);
            }
        });
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getTransPpob(Consts.FIRST_PAGE);
    }

    @Override
    public void successlistTransPpob(List<ItemPpob> itemPpobs, int page) {
        lasCount = itemPpobs.size();

        if (page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemPpobs.size() == 0) ? View.VISIBLE : View.GONE);

            mAdapter = new TransPpobReportAdapter(itemPpobs, getActivity());

            rvMyReport.setHasFixedSize(true);
            rvMyReport.setLayoutManager(linearLayoutManager);
            rvMyReport.setAdapter(mAdapter);
            rvMyReport.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.up_from_bottom);
            rvMyReport.startAnimation(animation);
        } else {
            for (ItemPpob itemPpob : itemPpobs){
                mAdapter.add(itemPpob);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
