package com.bijakpay.member.features.community;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.ItemGetCommunity;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public class ListGetCommunityAdapter extends BaseRecyclerViewAdapter<ItemGetCommunity> {

    public ListGetCommunityAdapter(List<ItemGetCommunity> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_get_community);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(final ItemGetCommunity itemGetCommunity, final ViewHolder holder) {
        holder.txtTitleGetCom.setText(itemGetCommunity.getCommunityTitle());
        holder.txtTypeMemberGetCom.setText(itemGetCommunity.getCommunityMemberType());
        holder.txtNominalCom.setText(Helper.numberCurrency(itemGetCommunity.getCommunityPrice()));
        holder.txtDescCom.setText(itemGetCommunity.getCommunityDesc());

        /*holder.btnJoinComm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = ((BaseActivity) mContext).getSupportFragmentManager();
                DialogFragment dialogFragment = JoinCommunityDialog.newInstance(itemGetCommunity);
                dialogFragment.show(fm, Consts.DIALOG);
            }
        });

        if (itemGetCommunity.getCommunityId().equals("Join")) {
            holder.btnJoinComm.setVisibility(View.GONE);
        } else {
            holder.btnJoinComm.setVisibility(View.VISIBLE);
        }
*/
        if (itemGetCommunity.getSysMembershipTypeQuota() == 0){
            Helper.createAlert(mContext, Consts.STR_INFO, "Kouta sudah habis");
        } else {
            holder.btnJoinComm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = ((BaseActivity) mContext).getSupportFragmentManager();
                    DialogFragment dialogFragment = JoinCommunityDialog.newInstance(itemGetCommunity);
                    dialogFragment.show(fm, Consts.DIALOG);
                }
            });
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTitleGetCom)
        TextView txtTitleGetCom;
        @BindView(R.id.txtTypeMemberGetCom)
        TextView txtTypeMemberGetCom;
        @BindView(R.id.txtNominalCom)
        TextView txtNominalCom;
        @BindView(R.id.txtDescCom)
        TextView txtDescCom;
        @BindView(R.id.btnJoinComm)
        Button btnJoinComm;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
