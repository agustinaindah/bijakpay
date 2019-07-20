package com.bijakpay.member.features.profile.bank;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.features.profile.AccountSettingsActivity;
import com.bijakpay.member.model.Bank;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 21/05/2017.
 */

public class BankAccountAdapter extends RecyclerView.Adapter<BankAccountAdapter.ViewHolder>{

    private static final String TAG = "BankAccountAdapter";
    private List<Bank> bankList = Collections.emptyList();
    private Context context;
    private BankAccountAdapterListener mCallback;
    private boolean isChooser = false;

    public BankAccountAdapter(Context context, BankAccountAdapterListener mCallback) {
        this.context = context;
        this.mCallback = mCallback;
    }

    public interface BankAccountAdapterListener{
        void onClickItem(int position, Bank bank);
    }

    public void updateItemBankAccount(List<Bank> bankList){
        this.bankList = bankList;
        notifyDataSetChanged();
    }

    @Override
    public BankAccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_account_bank);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BankAccountAdapter.ViewHolder holder, int position) {
        final Bank bank = (Bank) bankList.get(position);

        holder.txtAccountTitle.setText(bank.getMemberBankAccountAs());
        holder.txtAccountName.setText(bank.getMemberBankAccountOwnerName());
        holder.txtAccountNumber.setText(bank.getMemberBankAccountNumber());
        holder.txtBankName.setText(bank.getMemberBankAccountBankName());
        holder.txtBranchBank.setText(bank.getMemberBankAccountBranch());
        holder.imgEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBankAccount(bank);
            }
        });
        /*holder.imgDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.createPrompt(context, "Delete", "Hapus Data?", new CallbackInterface() {
                    @Override
                    public void callback() {
                        mCallback.onClickItem(holder.getAdapterPosition(), bank);
                    }
                });
            }
        });*/

        int visilibity = (isChooser) ? View.GONE : View.VISIBLE;
//        holder.imgDeleteAccount.setVisibility(visilibity);
        holder.imgEditAccount.setVisibility(visilibity);

        if (bank.getMemberBankAccountAs().equals("Utama")){
            holder.imgEditAccount.setVisibility(View.VISIBLE);
//            holder.imgDeleteAccount.setVisibility(View.GONE);
        } else {
            holder.imgEditAccount.setVisibility(View.VISIBLE);
//            holder.imgDeleteAccount.setVisibility(View.VISIBLE);
        }

        if (isChooser){
            holder.layBankAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onClickItem(holder.getAdapterPosition(), bank);
                }
            });
        }
    }

    private void gotoBankAccount(Bank bank) {
        Fragment fragment = BankAccountFormFragment
                .newInstance(Consts.UPDATE, bank, Consts.LAINNYA);
        AccountSettingsActivity activity = ((AccountSettingsActivity) context);
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.layBankAccount)
        RelativeLayout layBankAccount;
        @BindView(R.id.txtAccountTitle)
        TextView txtAccountTitle;
        @BindView(R.id.imgEditAccount)
        ImageView imgEditAccount;
        /*@BindView(R.id.imgDeleteAccount)
        ImageView imgDeleteAccount;*/
        @BindView(R.id.txtAccountName)
        TextView txtAccountName;
        @BindView(R.id.txtAccountNumber)
        TextView txtAccountNumber;
        @BindView(R.id.txtBankName)
        TextView txtBankName;
        @BindView(R.id.txtBranchBank)
        TextView txtBranchBank;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
