package com.bijakpay.member.features.message;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.R;
import com.bijakpay.member.model.ItemMessage;
import com.bijakpay.member.model.ItemMessageDetail;
import com.bijakpay.member.model.ItemMessageHeader;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public class MessageDetailAdapter extends RecyclerView.Adapter<MessageDetailAdapter.ViewHolder> {

    private Context context;
    private List<ItemMessageDetail> mData;

    public MessageDetailAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<ItemMessageDetail> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    public void addAll(List<ItemMessageDetail> data){
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return isMine(position) ? Consts.TYPE_MESSAGE_MINE : Consts.TYPE_MESSAGE_OTHER;
    }

    private boolean isMine(int position){
        return getItem(position).getMessageReplySender().equals(BijakApps.getInstance().getEmail());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, getItemViewRes(viewType));
        return new ViewHolder(view);
    }

    private int getItemViewRes(int viewType){
        return viewType == Consts.TYPE_MESSAGE_MINE ?
                R.layout.item_message_detail_mine :
                R.layout.item_message_detail_other;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemMessageDetail itemMessageDetail = getItem(position);

        if (isMine(position)){
            holder.txtMessageDetailMine.setText(itemMessageDetail.getMessageReplyContent());
        } else {
            holder.txtMessageDetailOther.setText(itemMessageDetail.getMessageReplyContent());
        }
    }

    private ItemMessageDetail getItem(int position){
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Nullable
        @BindView(R.id.txtMessageDetailMine)
        TextView txtMessageDetailMine;
        @Nullable
        @BindView(R.id.txtMessageDetailOther)
        TextView txtMessageDetailOther;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
