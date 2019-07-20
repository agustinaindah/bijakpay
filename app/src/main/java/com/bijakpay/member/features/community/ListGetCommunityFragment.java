package com.bijakpay.member.features.community;

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
import com.bijakpay.member.model.ItemGetCommunity;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public class ListGetCommunityFragment extends BaseFragment implements ListGetCommunityPresenter.View{

    @BindView(R.id.rvGetCommunity)
    RecyclerView rvGetCommunity;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    @BindString(R.string.get_comm)
    String strTitleGetComm;

    private ListGetCommunityAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastCount = 0;
    private ListGetCommunityPresenterImpl mPresenter;

    public static ListGetCommunityFragment newInstance() {
        Bundle args = new Bundle();
        ListGetCommunityFragment fragment = new ListGetCommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_get_community;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strTitleGetComm);

        mPresenter = new ListGetCommunityPresenterImpl(this);
        mPresenter.getCommunity(getQueryRequest(1));

        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvGetCommunity.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getCommunity(getQueryRequest(nextPage));
                }
            }
        });
    }

    private Map<String, String> getQueryRequest(int page){
        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("type","netizen, lifestyler");
        requestMap.put("pagenum",String.valueOf(page));
        return requestMap;
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
        mPresenter.getCommunity(getQueryRequest(Consts.FIRST_PAGE));
    }

    @Override
    public void showGetCommunity(List<ItemGetCommunity> itemGetCommunityList, Integer pagenum) {
        lastCount = itemGetCommunityList.size();

        if (pagenum == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemGetCommunityList.size()==0) ? View.VISIBLE : View.GONE);

            mAdapter = new ListGetCommunityAdapter(itemGetCommunityList, getActivity());

            rvGetCommunity.setHasFixedSize(true);
            rvGetCommunity.setLayoutManager(linearLayoutManager);
            rvGetCommunity.setAdapter(mAdapter);
            rvGetCommunity.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.up_from_bottom);
            rvGetCommunity.startAnimation(animation);
        } else {
            for (ItemGetCommunity itemGetCommunity : itemGetCommunityList){
                mAdapter.add(itemGetCommunity);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
