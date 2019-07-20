package com.bijakpay.member.features.network.my_network;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemMyNetwork;
import com.bijakpay.member.utils.ViewHelper;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 22/08/2017.
 */

public class MyUnilevelAdapter extends BaseRecyclerViewAdapter<ItemMyNetwork> {

    public MyUnilevelAdapter(List<ItemMyNetwork> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_my_unilevel);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(ItemMyNetwork itemMyNetwork, ViewHolder holder) {
        holder.txtNameUnilevel.setText(itemMyNetwork.getMemberName());
        holder.txtCodeMemberUnilevel.setText(String.valueOf(itemMyNetwork.getJalur()));
        holder.txtNumberLevelUnilevel.setText(String.valueOf(itemMyNetwork.getLevel()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtNameUnilevel)
        TextView txtNameUnilevel;
        @BindView(R.id.txtNumberLevelUnilevel)
        TextView txtNumberLevelUnilevel;
        @BindView(R.id.txtCodeMemberUnilevel)
        TextView txtCodeMemberUnilevel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
