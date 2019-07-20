package com.bijakpay.member.features.personal_report;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.model.Simple;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public class PersonalReportAdapter extends RecyclerView.Adapter<PersonalReportAdapter.ViewHolder>{

    private Context context;
    private List<Simple> simples;

    public PersonalReportAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<Simple> simples){
        this.simples = simples;
        notifyDataSetChanged();
    }

    @Override
    public PersonalReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_list);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonalReportAdapter.ViewHolder holder, int position) {
        final Simple list = (Simple) simples.get(position);

        holder.txtItemList.setText(list.getValue());
        holder.txtItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =
                        ((BaseActivity) context).getSupportFragmentManager();
                Fragment fragment = null;
                switch (list.getKey()){
                    case Consts.TRANS_PPOB:
                        fragment = TransPpobReportFragment.newInstance();
                        break;
                    case Consts.CASHBACK:
                        fragment = TransCashbackReportFragment.newInstance();
                        break;
                    case Consts.COMMISSION:
                        fragment = TransCommissionReportFragment.newInstance();
                        break;
                    case Consts.CASHBACK_REFUND:
                        fragment = TransRefundCashbackReportFragment.newInstance();
                        break;
                }
                try {
                    if (fragment !=null){
                        ((BaseActivity) context)
                                .gotoFragment(fm, fragment, true);
                    }
                }catch (Exception e){
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

        @BindView(R.id.txtItemList)
        TextView txtItemList;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
