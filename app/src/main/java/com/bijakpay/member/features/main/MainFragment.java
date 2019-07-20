package com.bijakpay.member.features.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.utils.Consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by agustinaindah on 27/04/2017.
 */

public class MainFragment extends BaseFragment{

    @BindView(R.id.rvMain)
    RecyclerView rvMain;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dashboard");
        initRecyclerView(initMenus());
    }

    private void initRecyclerView(List<HashMap<String, Integer>> menus) {
        MainAdapter adapter = new MainAdapter(menus, getActivity());

        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rvMain.setAdapter(adapter);
        rvMain.setNestedScrollingEnabled(false);
    }

    @NonNull
    private List<HashMap<String, Integer>> initMenus() {
        List<HashMap<String, Integer>> menus = new ArrayList<>();
        /*menus.add(addMenu(R.string.label_member_type, R.mipmap.ic_membership));*/
       /* menus.add(addMenu(R.string.label_income, R.mipmap.ic_income));*/
        menus.add(addMenu(R.string.label_dashboard, R.drawable.dashboard));
        menus.add(addMenu(R.string.label_register_friend, R.drawable.register));
        menus.add(addMenu(R.string.label_point_reward, R.drawable.pointreward));
        menus.add(addMenu(R.string.label_my_network, R.drawable.network));
        menus.add(addMenu(R.string.label_join_community, R.drawable.community));
        menus.add(addMenu(R.string.label_bonus_redeem, R.drawable.redeem));
        menus.add(addMenu(R.string.label_report, R.drawable.report));
        menus.add(addMenu(R.string.label_download, R.drawable.download));
        return menus;
    }

    private HashMap<String, Integer> addMenu(int resIdTitle, int resIdIcon) {
        HashMap<String, Integer> item = new HashMap<>();
        item.put(Consts.TITLE, resIdTitle);
        item.put(Consts.ICON, resIdIcon);
        return item;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_main;
    }
}
