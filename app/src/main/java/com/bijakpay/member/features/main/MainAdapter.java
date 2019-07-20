package com.bijakpay.member.features.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.features.ReportActivity;
import com.bijakpay.member.features.dashboard.DashboardActivity;
import com.bijakpay.member.features.community.CommunityActivity;
import com.bijakpay.member.features.download.DownloadFileActivity;
import com.bijakpay.member.features.download.DownloadMateriActivity;
import com.bijakpay.member.features.network.MyNetworkActivity;
import com.bijakpay.member.features.point_reward.BonusRedeemActivity;
import com.bijakpay.member.features.point_reward.history.HistoryMyPointActivity;
import com.bijakpay.member.features.SimpleCardAdapter;
import com.bijakpay.member.features.register_friends.RegisterFriendActivity2;
import com.bijakpay.member.utils.Consts;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by agustinaindah on 27/04/2017.
 */

public class MainAdapter extends SimpleCardAdapter<HashMap<String, Integer>> {

    public MainAdapter(List<HashMap<String, Integer>> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    protected void renderData(final HashMap<String, Integer> item, ViewHolder holder) {
        holder.imgItemSimpleCard.setImageResource(item.get(Consts.ICON));
        holder.imgItemSimpleCard.setPadding(10, 10, 10, 10);
        holder.cardItemSimpleCard.getLayoutParams().width = MATCH_PARENT;
        holder.cardItemSimpleCard.setCardElevation(0);
        holder.txtItemSimpleCard.setText(item.get(Consts.TITLE));
        holder.layItemSimpleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(
                        new Intent(mContext, getActivityClass(item.get(Consts.TITLE))));
            }
        });
    }

    private Class<? extends BaseActivity> getActivityClass(Integer title) {
        Hashtable ht = new Hashtable();
        ht.put(R.string.label_dashboard, DashboardActivity.class);
        ht.put(R.string.label_register_friend, RegisterFriendActivity2.class);
        ht.put(R.string.label_point_reward, HistoryMyPointActivity.class);
        ht.put(R.string.label_my_network, MyNetworkActivity.class);
        ht.put(R.string.label_join_community, CommunityActivity.class);
        ht.put(R.string.label_bonus_redeem, BonusRedeemActivity.class);
        ht.put(R.string.label_report, ReportActivity.class);
        ht.put(R.string.label_download, DownloadFileActivity.class);
        return (Class<? extends BaseActivity>) ht.get(title);
    }
}
