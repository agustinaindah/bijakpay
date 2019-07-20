package com.bijakpay.member.features.community;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.model.ItemCommMember;
import com.bijakpay.member.model.ItemGetCommunity;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.EndlessScrollListener;
import com.bijakpay.member.utils.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 30/08/2017.
 */

public class MyCommunityActivity extends BaseActivity /*implements MyCommunityMemberPresenter.View*/{

    // TODO: 04/09/2017 tinggal iki tok suwer

    @BindView(R.id.rvMyMemberCommunity)
    RecyclerView rvMyMemberCommunity;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private MyCommunityMemberPresenter mPresenter;
    private LinearLayoutManager linearLayoutManager;
    private MyCommunityMemberAdapter mAdapter;
    private int lastCount = 0;
    private ItemGetCommunity mItem;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Community Member");

       /* mPresenter = new MyCommunityMemberPresenterImpl(this);
        mPresenter.getCommMember(getQueryRequest(1));
        initData();*/

        /*linearLayoutManager = new LinearLayoutManager(this);
        rvMyMemberCommunity.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getCommMember(getQueryRequest(nextPage));
                }
            }
        });*/
    }

   /* private void initData() {
        Intent intent = getIntent();
        mItem = (ItemGetCommunity) intent.getSerializableExtra(Consts.ARG_DATA);
    }

    private Map<String, String> getQueryRequest(int start){
        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("community_id",String.valueOf(mItem.getCommunityId()));
        requestMap.put("start",String.valueOf(start));
        return requestMap;
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_member, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_my_member:
                DialogFragment dialogFragment = CreateCommunityDialog.newInstance();
                dialogFragment.show(getSupportFragmentManager(),Consts.DIALOG);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    protected int setView() {
        return R.layout.activity_my_community_member;
    }

    /*@Override
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
        mPresenter.getCommMember(getQueryRequest(Consts.FIRST_PAGE));
    }

    @Override
    public void successListMember(List<ItemCommMember> itemCommMembers, Integer cInteger start) {
        lastCount = itemCommMembers.size();

        if (start == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemCommMembers.size() == 0) ? View.VISIBLE : View.GONE);

            mAdapter = new MyCommunityMemberAdapter(itemCommMembers, this);

            rvMyMemberCommunity.setHasFixedSize(true);
            rvMyMemberCommunity.setLayoutManager(linearLayoutManager);
            rvMyMemberCommunity.setAdapter(mAdapter);
            rvMyMemberCommunity.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.up_from_bottom);
            rvMyMemberCommunity.startAnimation(animation);
        } else {
            for (ItemCommMember itemCommMember : itemCommMembers){
                mAdapter.add(itemCommMember);
                mAdapter.notifyDataSetChanged();
            }
        }
    }*/
}
