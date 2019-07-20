package com.bijakpay.member.features.personal_report;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemCashback;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public class TransCashbackReportAdapter extends BaseRecyclerViewAdapter<ItemCashback> {

    public TransCashbackReportAdapter(List<ItemCashback> mData, Context mContext) {
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

    private void renderData(ItemCashback itemCashback, ViewHolder holder) {
        holder.txtNumberReport.setText(itemCashback.getCashbackLogTransactionId());
        holder.txtDatePpob.setText(
                Helper.parseToDateString(itemCashback.getCashbackLogDatetime(), Consts.TYPE_DATE));
        holder.txtNominalPpob.setText(Helper.numberCurrency(itemCashback.getCashbackLogNominalPersonal()));
        holder.txtDescPpob.setText(itemCashback.getCashbackLogDesc());
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
