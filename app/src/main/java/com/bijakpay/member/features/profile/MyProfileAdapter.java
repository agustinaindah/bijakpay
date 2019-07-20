package com.bijakpay.member.features.profile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.member_type.MemberUpgradeFragment;
import com.bijakpay.member.features.profile.bank.BankAccountFragment;
import com.bijakpay.member.model.Simple;
import com.bijakpay.member.utils.Consts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public class MyProfileAdapter extends RecyclerView.Adapter<MyProfileAdapter.ViewHolder> {

    private Context context;
    private List<Simple> simples;

    public MyProfileAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<Simple> simples){
        this.simples = simples;
        notifyDataSetChanged();
    }

    @Override
    public MyProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_my_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyProfileAdapter.ViewHolder holder, int position) {
        final Simple list = (Simple) simples.get(position);

        holder.txtItemListProfile.setText(list.getValue());
        holder.txtItemListProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =
                        ((BaseActivity) context).getSupportFragmentManager();
                Fragment fragment = null;
                switch (list.getKey()){
                    case Consts.PROFILE:
                        fragment = MyProfileMemberFragment.newInstance();
                        break;
                    /*case Consts.ADDRESS:
                        fragment = AddressFragment.newInstance();
                        break;*/
                    case Consts.BANK_ACCOUNT:
                        fragment = BankAccountFragment.newInstance();
                        break;
                   /* case Consts.CHANGE_PASSWORD:
                        fragment = ChangePasswordFragment.newInstance();
                        break;*/
                    case Consts.UPGRADE_MEMBER:
                        fragment = MemberUpgradeFragment.newInstance();
                        break;
                }
                try {
                    if (fragment !=null){
                        ((BaseActivity) context)
                                .gotoFragment(fm, fragment, true);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return simples.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtItemListProfile)
        TextView txtItemListProfile;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
