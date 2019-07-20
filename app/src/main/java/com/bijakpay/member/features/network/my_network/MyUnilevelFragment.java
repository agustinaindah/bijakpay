package com.bijakpay.member.features.network.my_network;

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
import com.bijakpay.member.model.ItemMyNetwork;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 22/08/2017.
 */

public class MyUnilevelFragment extends BaseFragment implements MyUnilevelPresenter.View {

    @BindView(R.id.rvMyUnilevel)
    RecyclerView rvMyUnilevel;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    @BindString(R.string.label_unilevel)
    String strTitleUnilevel;

    private MyUnilevelAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastCount = 0;
    private MyUnilevelPresenterImpl mPresenter;

    public static MyUnilevelFragment newInstance() {
        Bundle args = new Bundle();
        MyUnilevelFragment fragment = new MyUnilevelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MyUnilevelPresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strTitleUnilevel);
        mPresenter.getMyNetwork(Consts.FIRST_PAGE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMyUnilevel.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getMyNetwork(nextPage);
                }
            }
        });
    }

    @Override
    protected int setView() {
        return R.layout.fragment_my_unilevel;
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
        mPresenter.getMyNetwork(Consts.FIRST_PAGE);
    }

    @Override
    public void showListUnilevel(List<ItemMyNetwork> myNetworks, int page) {
        lastCount = myNetworks.size();

        if (page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((myNetworks.size()==0) ? View.VISIBLE : View.GONE);

            mAdapter = new MyUnilevelAdapter(myNetworks, getActivity());

            rvMyUnilevel.setHasFixedSize(true);
            rvMyUnilevel.setLayoutManager(linearLayoutManager);
            rvMyUnilevel.setAdapter(mAdapter);
            rvMyUnilevel.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.up_from_bottom);
            rvMyUnilevel.startAnimation(animation);
        } else {
            for (ItemMyNetwork myNetwork : myNetworks){
                mAdapter.add(myNetwork);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
