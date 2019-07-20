package com.bijakpay.member.features.personal_report;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemCommission;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public class TransCommissionReportAdapter extends BaseRecyclerViewAdapter<ItemCommission> {

    public TransCommissionReportAdapter(List<ItemCommission> mData, Context mContext) {
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

    private void renderData(ItemCommission itemCommission, ViewHolder holder) {
        holder.txtNumberReport.setText(itemCommission.getCashbackLogTransactionId());
        holder.txtDatePpob.setText(
                Helper.parseToDateString(itemCommission.getCashbackLogDatetime(), Consts.TYPE_DATE));
        holder.txtNominalPpob.setText(Helper.numberCurrency(itemCommission.getCashbackLogNominalCommission()));
        holder.txtDescPpob.setText(itemCommission.getCashbackLogDesc());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtNumberReport)
        TextView txtNumberReport;
        @BindView(R.id.txtDateReport)
        TextView txtDatePpob;
        @BindView(R.id.txtNominalReport)
        TextView txtNominalPpob;
        @BindView(R.id.txtDescReport)
        TextView txtDescPpob;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
