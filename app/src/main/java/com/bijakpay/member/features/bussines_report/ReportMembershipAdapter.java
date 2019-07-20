package com.bijakpay.member.features.bussines_report;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemMembership;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class ReportMembershipAdapter extends BaseRecyclerViewAdapter<ItemMembership> {

    public ReportMembershipAdapter(List<ItemMembership> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_report_membership);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(ItemMembership itemMembership, ViewHolder holder) {
        holder.txtItemTransNumb.setText(itemMembership.getTransMembershipNumber());
        holder.txtItemToken.setText(itemMembership.getTransMembershipToken());
        holder.txtTotal.setText(Helper.numberCurrency(itemMembership.getTransMembershipNominal()));
        holder.txtItemDate.setText(
                Helper.parseToDateString(itemMembership.getTransMembershipDatetime(), Consts.TYPE_DATE));
        holder.txtTypeMember.setText(itemMembership.getTransMembershipMemberType());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtItemTransNumb)
        TextView txtItemTransNumb;
        @BindView(R.id.txtItemToken)
        TextView txtItemToken;
        @BindView(R.id.txtTypeMember)
        TextView txtTypeMember;
        @BindView(R.id.txtItemDate)
        TextView txtItemDate;
        @BindView(R.id.txtTotal)
        TextView txtTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
