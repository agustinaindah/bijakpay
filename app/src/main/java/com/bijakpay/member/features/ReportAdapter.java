package com.bijakpay.member.features;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.bussines_report.BussinesReportFragment;
import com.bijakpay.member.features.personal_report.PersonalReportFragment;
import com.bijakpay.member.model.Simple;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.ViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 22/08/2017.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder>{

    private Context context;
    private List<Simple> simples;

    public ReportAdapter(Context context) {
        this.context = context;
    }

    public void updateDate(List<Simple> simples){
        this.simples = simples;
        notifyDataSetChanged();
    }


    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_list);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportAdapter.ViewHolder holder, int position) {
        final Simple list = (Simple) simples.get(position);

        holder.txtItemList.setText(list.getValue());
        holder.txtItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =
                        ((BaseActivity) context).getSupportFragmentManager();
                Fragment fragment = null;
                switch (list.getKey()){
                    case Consts.BUSSINES:
                        fragment = BussinesReportFragment.newInstance();
                        break;
                    case Consts.PERSONAL:
                        fragment = PersonalReportFragment.newInstance();
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtItemList)
        TextView txtItemList;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
