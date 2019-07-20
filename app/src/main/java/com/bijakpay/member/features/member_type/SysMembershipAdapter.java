package com.bijakpay.member.features.member_type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.model.SysMembership;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 22/05/2017.
 */

public class SysMembershipAdapter extends BaseAdapter {

    @BindView(R.id.txtSysmembership)
    TextView txtSysmembership;

    private Context context;
    private List<SysMembership> sysMemberships = Collections.emptyList();

    public SysMembershipAdapter(Context context) {
        this.context = context;
    }

    public void updateSysMembership(List<SysMembership> sysMemberships){
        this.sysMemberships = sysMemberships;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sysMemberships.size();
    }

    @Override
    public Object getItem(int position) {
        return sysMemberships.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_sys_membership, parent, false);
        }
        ButterKnife.bind(this, convertView);
        displayData((SysMembership) getItem(position));
        return convertView;
    }

    private void displayData(SysMembership item) {
        txtSysmembership.setText(item.getSysMembershipTypeName() + " - "
                + item.getSysMembershipTypePrice());
    }
}
