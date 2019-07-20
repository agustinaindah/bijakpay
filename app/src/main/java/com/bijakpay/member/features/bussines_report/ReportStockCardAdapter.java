package com.bijakpay.member.features.bussines_report;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemStockCard;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class ReportStockCardAdapter extends BaseRecyclerViewAdapter<ItemStockCard> {

    public ReportStockCardAdapter(List<ItemStockCard> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_report_stockcard);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(ItemStockCard itemStockCard, ViewHolder holder) {
        holder.txtItemTokenNumb.setText(itemStockCard.getStockCardToken());
        holder.txtItemDate.setText(
                Helper.parseToDateString(itemStockCard.getStockCardCreateDatetime(), Consts.TYPE_DATE));
        holder.txtItemStatus.setText(itemStockCard.getStockCardStatus());
        holder.txtItemMemberType.setText(itemStockCard.getStockCardMemberType());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtItemTokenNumb)
        TextView txtItemTokenNumb;
        @BindView(R.id.txtItemStatus)
        TextView txtItemStatus;
        @BindView(R.id.txtItemDate)
        TextView txtItemDate;
        @BindView(R.id.txtItemMemberType)
        TextView txtItemMemberType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
