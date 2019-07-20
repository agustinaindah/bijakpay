package com.bijakpay.member.features.community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemCommMember;
import com.bijakpay.member.model.ItemGetCommunity;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public class MyCommunityMemberAdapter extends BaseRecyclerViewAdapter<ItemCommMember> {

    public MyCommunityMemberAdapter(List<ItemCommMember> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_member_community, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position),(ViewHolder) holder);
    }

    private void renderData(ItemCommMember itemCommMember, ViewHolder holder) {
        holder.txtCodeMemberMyMemberComm.setText(itemCommMember.getCommunityUserMemberId());
        holder.txtNameMyMemberComm.setText(itemCommMember.getMemberName());
        /*holder.txtNumbHpMyMemberComm.setText(itemCommMember.);*/
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtNameMyMemberComm)
        TextView txtNameMyMemberComm;
        /*@BindView(R.id.txtNumbHpMyMemberComm)
        TextView txtNumbHpMyMemberComm;*/
        @BindView(R.id.txtCodeMemberMyMemberComm)
        TextView txtCodeMemberMyMemberComm;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
