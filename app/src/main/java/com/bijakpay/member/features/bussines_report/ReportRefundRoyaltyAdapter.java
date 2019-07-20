package com.bijakpay.member.features.bussines_report;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemRefundRoyalty;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class ReportRefundRoyaltyAdapter extends BaseRecyclerViewAdapter<ItemRefundRoyalty> {

    public ReportRefundRoyaltyAdapter(List<ItemRefundRoyalty> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_report);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(ItemRefundRoyalty itemRefundRoyalty, ViewHolder holder) {
        holder.txtNumberReport.setText(itemRefundRoyalty.getCashbackLogTransactionId());
        holder.txtDateReport.setText(
                Helper.parseToDateString(itemRefundRoyalty.getCashbackLogDatetime(), Consts.TYPE_DATE));
        holder.txtNominalReport.setText(Helper.numberCurrency(itemRefundRoyalty.getCashbackLogNominalRoyalty()));
        holder.txtDescReport.setText(itemRefundRoyalty.getCashbackLogDesc());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtNumberReport)
        TextView txtNumberReport;
        @BindView(R.id.txtDateReport)
        TextView txtDateReport;
        @BindView(R.id.txtNominalReport)
        TextView txtNominalReport;
        @BindView(R.id.txtDescReport)
        TextView txtDescReport;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
