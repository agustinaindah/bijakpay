package com.bijakpay.member.features.community;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.features.bussines_report.ReportMembershipAdapter;
import com.bijakpay.member.features.network.my_channel.MyChannelPresenter;
import com.bijakpay.member.model.ItemCommMember;
import com.bijakpay.member.model.ItemGetCommunity;
import com.bijakpay.member.model.ItemMembership;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.SharedPref;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 29/08/2017.
 */

public class MyCommunityMemberFragment extends BaseFragment implements
        MyCommunityMemberPresenter.View {

    // TODO: 06/09/2017 benerin enabled button create

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.btnCreateComm)
    Button btnCreateComm;
    @BindView(R.id.rvMyCommMember)
    RecyclerView rvMyCommMember;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    @BindString(R.string.my_comm_member)
    String strTitleMyCommMember;

    private MyCommunityMemberAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastCount = 0;
    private MyCommunityMemberPresenterImpl mPresenter;

    public static MyCommunityMemberFragment newInstance() {
        Bundle args = new Bundle();
        MyCommunityMemberFragment fragment = new MyCommunityMemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_comm_member;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MyCommunityMemberPresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strTitleMyCommMember);

        mPresenter.getCommMember(getQueryRequest(Consts.FIRST_PAGE));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMyCommMember.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT) {
                    mPresenter.getCommMember(getQueryRequest(nextPage));
                }
            }
        });
    }

    private Map<String, String> getQueryRequest(int start) {
        HashMap<String, String> requestMap = new HashMap<>();
        ItemCommMember itemCommMember = new ItemCommMember();
        requestMap.put("community_id", String.valueOf(itemCommMember.getCommunityUserCommunityId()));
        requestMap.put("start", String.valueOf(start));
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

    @OnClick(R.id.btnCreateComm)
    public void btnCreateComm(View view) {
        DialogFragment dialogFragment = CreateCommunityDialog.newInstance();
        dialogFragment.show(getFragmentManager(), Consts.DIALOG);
    }

    @OnClick(R.id.btnError)
    public void reload() {
        layError.setVisibility(View.GONE);
        mPresenter.getCommMember(getQueryRequest(Consts.FIRST_PAGE));
    }

    @Override
    public void successListMember(List<ItemCommMember> itemCommMembers, Integer start) {
        lastCount = itemCommMembers.size();

        if (start == Consts.FIRST_PAGE) {
            txtNoData.setVisibility((itemCommMembers.size() == 0) ? View.VISIBLE : View.GONE);
            appbar.setVisibility((itemCommMembers.size() == 0) ? View.VISIBLE : View.GONE);

            mAdapter = new MyCommunityMemberAdapter(itemCommMembers, getActivity());

            rvMyCommMember.setHasFixedSize(true);
            rvMyCommMember.setLayoutManager(linearLayoutManager);
            rvMyCommMember.setAdapter(mAdapter);
            rvMyCommMember.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.up_from_bottom);
            rvMyCommMember.startAnimation(animation);
        } else {
            for (ItemCommMember itemCommMember : itemCommMembers) {
                mAdapter.add(itemCommMember);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
