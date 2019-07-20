package com.bijakpay.member.features.member_type;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.base.BaseRecyclerViewAdapter;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 05/05/2017.
 */

public class MemberTypeAdapter extends BaseRecyclerViewAdapter<SysMembership> {

    private String currentType;

    public MemberTypeAdapter(List<SysMembership> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_member_type);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(final SysMembership sysMembership, final ViewHolder holder) {
        holder.txtMemberType.setText(sysMembership.getSysMembershipTypeName());
        holder.txtPriceMember.setText(
        Helper.numberCurrency(sysMembership.getSysMembershipTypePrice()));

        int profileType = getMemberType(currentType);
//        if(profileType == 1)
//        {
//            Log.d("life", String.valueOf(getMemberType(currentType)));
//            if(sysMembership.getSysMembershipTypeId() == 6){
//                holder.btnUpdateLife.setVisibility(View.GONE);
//                holder.btnUpdateAgen.setVisibility(View.VISIBLE);
//                holder.btnUpdateDealer.setVisibility(View.VISIBLE);
//            }else if(sysMembership.getSysMembershipTypeId() == 7){
//
//            }else{
//
//            }
//        }
//        if(profileType == 2){
//            Log.d("agen", String.valueOf(getMemberType(currentType)));
//
//            if(sysMembership.getSysMembershipTypeId() == 6){
//
//            }else if(sysMembership.getSysMembershipTypeId() == 7){
//                holder.btnUpdateLife.setVisibility(View.GONE);
//                holder.btnUpdateAgen.setVisibility(View.GONE);
//                holder.btnUpdateDealer.setVisibility(View.VISIBLE);
//            }else{
//
//            }
//        }
//        if(profileType == 3){
//            Log.d("dealer", String.valueOf(getMemberType(currentType)));
//
//            if(sysMembership.getSysMembershipTypeId() == 6){
//
//            }else if(sysMembership.getSysMembershipTypeId() == 7){
//
//            }else{
//                holder.btnUpdateLife.setVisibility(View.GONE);
//                holder.btnUpdateAgen.setVisibility(View.GONE);
//                holder.btnUpdateDealer.setVisibility(View.GONE);
//            }
//        }

        switch (profileType) {
            case 0:
                if (sysMembership.getSysMembershipTypeId() == 5) {
                    holder.btnUpdateLife.setVisibility(View.VISIBLE);
                } else if (sysMembership.getSysMembershipTypeId() == 6) {
                    holder.btnUpdateAgen.setVisibility(View.VISIBLE);
                } else {
                    holder.btnUpdateDealer.setVisibility(View.VISIBLE);
                }
                break;
            case 1:
                if (sysMembership.getSysMembershipTypeId() == 5) {
                } else if (sysMembership.getSysMembershipTypeId() == 6) {
                    holder.btnUpdateAgen.setVisibility(View.VISIBLE);
                } else {
                    holder.btnUpdateDealer.setVisibility(View.VISIBLE);
                }
            break;
            case 2:
                Log.d("agen", String.valueOf(getMemberType(currentType)));

                if (sysMembership.getSysMembershipTypeId() == 5) {

                } else if (sysMembership.getSysMembershipTypeId() == 6) {
                } else {
                    holder.btnUpdateDealer.setVisibility(View.VISIBLE);
                }

            break;
            case 3:

            break;

            default:

                break;
        }

        holder.btnUpdateLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = ((BaseActivity) mContext).getSupportFragmentManager();
                DialogFragment dialogFragment = UpdateMemberEwalletDialog.newInstance(sysMembership);
                dialogFragment.show(fm, Consts.DIALOG);
            }
        });

        holder.btnUpdateAgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = ((BaseActivity) mContext).getSupportFragmentManager();
                DialogFragment dialogFragment = UpdateMemberEwalletDialog.newInstance(sysMembership);
                dialogFragment.show(fm, Consts.DIALOG);
            }
        });

        holder.btnUpdateDealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = ((BaseActivity) mContext).getSupportFragmentManager();
                DialogFragment dialogFragment = UpdateMemberEwalletDialog.newInstance(sysMembership);
                dialogFragment.show(fm, Consts.DIALOG);
            }
        });

        int itemType = getMemberType(sysMembership.getSysMembershipTypeName());
//        int profileType = getMemberType(currentType);

        /*if (profileType == 0 && itemType == 0){

        }
        else if (profileType == 1 || itemType == 1 )
        {
            deleteItem(holder.getAdapterPosition());
        }
        else if (profileType == 2 || itemType == 2)
        {
            deleteItem(holder.getAdapterPosition());
        }
        else if (profileType == 3 || itemType == 3)
        {
            deleteItem(holder.getAdapterPosition());
        }*/
    }

    public void setCurrentType(String currentType){
        this.currentType = currentType;
    }

    private int getMemberType(String type){
        type = currentType;
        int resId = 0;
        switch (type){
            case Consts.LIFESTYLER:
                resId = 1;
                break;
            case Consts.AGEN:
                resId = 2;
                break;
            case Consts.DEALER:
                resId = 3;
                break;
        }
        return resId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardMemberType)
        CardView cardMemberType;
        @BindView(R.id.txtMemberType)
        TextView txtMemberType;
        @BindView(R.id.txtPriceMember)
        TextView txtPriceMember;
        /*@BindView(R.id.btnMemberOptions)
        ImageView btnMemberOptions;*/
        @BindView(R.id.btnUpdateLife)
        Button btnUpdateLife;
        @BindView(R.id.btnUpdateAgen)
        Button btnUpdateAgen;
        @BindView(R.id.btnUpdateDealer)
        Button btnUpdateDealer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
