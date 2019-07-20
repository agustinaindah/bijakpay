package com.bijakpay.member.features;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 28/04/2017.
 */

public abstract class SimpleCardAdapter<T> extends BaseRecyclerViewAdapter<T> {

    public SimpleCardAdapter(List<T> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_simple_card);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    protected abstract void renderData(T item, ViewHolder holder);

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardItemSimpleCard)
        public CardView cardItemSimpleCard;
        @BindView(R.id.layItemSimpleCard)
        public RelativeLayout layItemSimpleCard;
        @BindView(R.id.imgItemSimpleCard)
        public ImageView imgItemSimpleCard;
        @BindView(R.id.txtItemSimpleCard)
        public TextView txtItemSimpleCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
