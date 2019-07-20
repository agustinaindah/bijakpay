package com.bijakpay.member.features.profile.address;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.features.community.CreateCommunityDialog;
import com.bijakpay.member.features.profile.AccountSettingsActivity;
import com.bijakpay.member.model.Address;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class AddressFragment extends BaseFragment implements AddressPresenter.View,
        AddressAdapter.AddressAdapterListener{

    /*@BindView(R.id.fabAddAddress)
    FloatingActionButton fabAddAddress;*/
    @BindView(R.id.btnAddAddress)
    Button btnAddAddress;
    @BindView(R.id.rvAddress)
    RecyclerView rvAddress;
   /* @BindView(R.id.txtAddressAs)
    TextView txtAddressAs;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtSubZip)
    TextView txtSubZip;
    @BindView(R.id.txtProvince)
    TextView txtProvince;
    @BindView(R.id.txtPhone)
    TextView txtPhone;*/
  /*  @BindView(R.id.imgEditAddressUtama)
    ImageView imgEditAddressUtama;*/
    /*@BindView(R.id.imgDeleteAddress)
    ImageView imgDeleteAddress;
    @BindView(R.id.imgEditAddress)
    ImageView imgEditAddress;*/
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    /*@BindView(R.id.layPrimaryAddress)
    LinearLayout layPrimaryAddress;*/

    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.address)
    String strAddress;
    @BindString(R.string.success_remove)
    String strSuccessDelete;
    @BindString(R.string.msg_success)
    String strSuccess;

    private AddressPresenter mPresenter;
    private ProgressDialog progressDialog;
    private AddressAdapter mAdapter;
    private Address mPrimaryAddress;

    public static AddressFragment newInstance() {
        Bundle args = new Bundle();
        AddressFragment fragment = new AddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_address;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new AddressPresenterImpl(this);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strAddress);
        preparePrimaryAddress();
    }

    private void preparePrimaryAddress() {
        mPresenter.getAddress();
//        imgDeleteAddress.setVisibility(View.GONE);
    }

    private void prepareOtherAddress(){
        mPresenter.getAddress();
    }

   /* @OnClick(R.id.imgEditAddress)
    public void editMainAddress() {
        Fragment fragment = AddressFormFragment.newInstance(Consts.UPDATE, mPrimaryAddress, Consts.UTAMA);
        AccountSettingsActivity activity = ((AccountSettingsActivity) getActivity());
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }*/

    @OnClick(R.id.btnAddAddress)
    public void addAddress(){
        Fragment fragment = AddressFormFragment.newInstance(Consts.ADD);
        AccountSettingsActivity activity = ((AccountSettingsActivity) getActivity());
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, strInfoNotConnect, false, new CallbackInterface() {
            @Override
            public void callback() {
                getActivity().onBackPressed();
            }
        });
    }

   /* @Override
    public void successPrimary(Address primaryAddress) {
        txtNoData.setVisibility(View.GONE);
        layPrimaryAddress.setVisibility(View.VISIBLE);
        mPrimaryAddress = primaryAddress;
        txtAddressAs.setText("Alamat" + Consts.UTAMA);
        txtAddress.setText(primaryAddress.getMemberAddressDetailAddress());
        txtSubZip.setText(
                primaryAddress.getMemberAddressSubdistrictName() + ", "
                        + primaryAddress.getMemberAddressCityName()
        );
        txtProvince.setText(primaryAddress.getMemberAddressProvinceName());
        txtPhone.setText("0" + primaryAddress.getMemberAddressMobilePhoneNumber());
        prepareOtherAddress();
    }*/

    @Override
    public void successPrimary(List<Address> addresses) {
        if (addresses.size() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
            btnAddAddress.setVisibility(View.GONE);
        } else if (addresses.size() !=0){
            txtNoData.setVisibility(View.GONE);
            btnAddAddress.setVisibility(View.VISIBLE);
        }
        mAdapter = new AddressAdapter(getActivity(), this);
        mAdapter.updateItemAddress(addresses);
        rvAddress.setHasFixedSize(true);
        rvAddress.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAddress.setNestedScrollingEnabled(false);
        rvAddress.setAdapter(mAdapter);
    }

    @Override
    public void showSuccess() {
        Helper.createAlert(getActivity(), strSuccess, strSuccessDelete, false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        preparePrimaryAddress();
                        prepareOtherAddress();
                    }
                });
    }

    @Override
    public void onClickItem(int position, Address address) {
        mPresenter.deleteAddress(address.getMemberAddressId());
    }
}
