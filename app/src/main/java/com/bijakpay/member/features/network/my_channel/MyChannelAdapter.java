package com.bijakpay.member.features.network.my_channel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemMyChannel;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 23/08/2017.
 */

public class MyChannelAdapter extends BaseRecyclerViewAdapter<ItemMyChannel> {

    public MyChannelAdapter(List<ItemMyChannel> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_my_channel);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(ItemMyChannel itemMyChannel, ViewHolder holder) {
        Helper.displayImage(mContext, itemMyChannel.getMemberPhoto(), holder.imgMyChannel);
        holder.txtMyChannelName.setText(itemMyChannel.getMemberName());
        holder.txtMyChannelNumbCode.setText(itemMyChannel.getMemberCode());
        holder.txtMyChannelTypeMember.setText(itemMyChannel.getMemberType());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgMyChannel)
        CircularImageView imgMyChannel;
        @BindView(R.id.txtMyChannelName)
        TextView txtMyChannelName;
        @BindView(R.id.txtMyChannelNumbCode)
        TextView txtMyChannelNumbCode;
        @BindView(R.id.txtMyChannelTypeMember)
        TextView txtMyChannelTypeMember;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
