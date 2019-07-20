package com.bijakpay.member.features.bussines_report;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemRoyalty;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class ReportRoyaltyAdapter extends BaseRecyclerViewAdapter<ItemRoyalty>{

    public ReportRoyaltyAdapter(List<ItemRoyalty> mData, Context mContext) {
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

    private void renderData(ItemRoyalty itemRoyalty, ViewHolder holder) {
        holder.txtNumberReport.setText(itemRoyalty.getCashbackLogTransactionId());
        holder.txtDateReport.setText(
                Helper.parseToDateString(itemRoyalty.getCashbackLogDatetime(), Consts.TYPE_DATE));
        holder.txtNominalReport.setText(Helper.numberCurrency(itemRoyalty.getCashbackLogNominalRoyalty()));
        holder.txtDescReport.setText(itemRoyalty.getCashbackLogDesc());
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
