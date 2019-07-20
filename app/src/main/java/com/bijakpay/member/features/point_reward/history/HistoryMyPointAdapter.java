package com.bijakpay.member.features.point_reward.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemMyPoint;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;

/**
 * Created by agustinaindah on 22/06/2017.
 */

public class HistoryMyPointAdapter extends BaseRecyclerViewAdapter<ItemMyPoint> {

    public HistoryMyPointAdapter(List<ItemMyPoint> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_my_history_point);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(final ItemMyPoint itemMyPoint, ViewHolder holder) {
        holder.txtInPoint.setText(itemMyPoint.getMemberPointRewardDetailIn());
        holder.txtOutPoint.setText(itemMyPoint.getMemberPointRewardDetailOut());
        holder.txtDateMyHistory.setText(
                Helper.parseToDateString(itemMyPoint.getMemberPointRewardDetailInputDatetime(), Consts.TYPE_DATE));
        holder.txtDescMyHistory.setText(itemMyPoint.getMemberPointRewardDetailDesc());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtInPoint)
        TextView txtInPoint;
        @BindView(R.id.txtOutPoint)
        TextView txtOutPoint;
        @BindView(R.id.txtDateMyHistory)
        TextView txtDateMyHistory;
        @BindView(R.id.txtDescMyHistory)
        TextView txtDescMyHistory;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
