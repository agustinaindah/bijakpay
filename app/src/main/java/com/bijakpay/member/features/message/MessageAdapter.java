package com.bijakpay.member.features.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.model.ItemMessage;
import com.bijakpay.member.model.ItemMessageHeader;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private static final String TAG = "MessageAdapter";
    private Context context;
    private List<ItemMessage> itemMessages = Collections.emptyList();
    private MessageAdapterListener mCallback;

    public MessageAdapter(Context context, MessageAdapterListener mCallback) {
        this.context = context;
        this.mCallback = mCallback;
    }

    public void updateData(List<ItemMessage> itemMessages){
        this.itemMessages = itemMessages;
        notifyDataSetChanged();
    }

    public void addAll(List<ItemMessage> itemMessages){
        this.itemMessages.addAll(itemMessages);
        notifyDataSetChanged();
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_message);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MessageAdapter.ViewHolder holder, int position) {
        final ItemMessage itemMessage = getItem(position);
        try {
            Helper.displayImage(context, itemMessage.getMessageTargetPhoto(), holder.imgMessageSender, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.txtMessageSender.setText(itemMessage.getMessageSender());
        holder.txtMessageSubject.setText(itemMessage.getMessageSubject());
        holder.txtMessageTime.setText(Helper.parseToDateString(
                itemMessage.getMessageInputDatetime(),Consts.TYPE_DATETIME, Consts.TIME));
        /*holder.layItemMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onClickMessage(holder.getAdapterPosition(), itemMessage);
            }
        });*/
    }

    private ItemMessage getItem(int position){
        return itemMessages.get(position);
    }

    @Override
    public int getItemCount() {
        return itemMessages.size();
    }

    public void deleteAll(){
        itemMessages.removeAll(itemMessages);
        notifyDataSetChanged();
    }

    public interface MessageAdapterListener{
        void onClickMessage(int position, ItemMessage itemMessage);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgMessageSender)
        CircularImageView imgMessageSender;
        @BindView(R.id.txtMessageSender)
        TextView txtMessageSender;
        @BindView(R.id.txtMessageSubject)
        TextView txtMessageSubject;
        @BindView(R.id.txtMessageTime)
        TextView txtMessageTime;
       /* @BindView(R.id.layItemMessage)
        RelativeLayout layItemMessage;
*/
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
