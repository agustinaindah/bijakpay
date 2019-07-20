package com.bijakpay.member.features.point_reward.history;

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
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.profile.ProfilePresenter;
import com.bijakpay.member.model.ItemMyPoint;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 22/06/2017.
 */

public class HistoryMyPointActivity extends BaseActivity implements HistoryMyPointPresenter.View {

    @BindView(R.id.txtSaldoEwallet)
    TextView txtSaldoEwallet;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.rvMyHistoryPoint)
    RecyclerView rvMyHistoryPoint;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private HistoryMyPointAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastCount = 0;
    private HistoryMyPointPresenter mPresenter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new HistoryMyPointPresenterImpl(this);
        mPresenter.getMyPoint(Consts.FIRST_PAGE);
        mPresenter.getPointSaldo();

        linearLayoutManager = new LinearLayoutManager(this);
        rvMyHistoryPoint.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getMyPoint(nextPage);
                }
            }
        });
    }

    @Override
    protected int setView() {
        return R.layout.activity_history_my_point;
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
        Helper.createAlert(this, Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getMyPoint(Consts.FIRST_PAGE);
    }

    @Override
    public void successListMyPoint(List<ItemMyPoint> itemMyPoints, int page) {
        lastCount = itemMyPoints.size();

        if (page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemMyPoints.size() == 0) ? View.VISIBLE : View.GONE);
            mAdapter = new HistoryMyPointAdapter(itemMyPoints, this);

            rvMyHistoryPoint.setHasFixedSize(true);
            rvMyHistoryPoint.setLayoutManager(linearLayoutManager);
            rvMyHistoryPoint.setAdapter(mAdapter);
            rvMyHistoryPoint.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.up_from_bottom);
            rvMyHistoryPoint.startAnimation(animation);

        } else {
            for (ItemMyPoint itemMyPoint : itemMyPoints){
                mAdapter.add(itemMyPoint);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void showSaldo(JsonObject results) {
        try {
            txtSaldoEwallet.setText(results.get("results").getAsLong() + " pt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
