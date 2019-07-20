package com.bijakpay.member.features.profile.address;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bijakpay.member.R;
import com.bijakpay.member.features.profile.AccountSettingsActivity;
import com.bijakpay.member.model.Address;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.ViewHelper;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder>{

    private static final String TAG = "AddressAdapter";
    private List<Address> addressList = Collections.emptyList();
    private Context context;
    private AddressAdapterListener mCallback;
    public boolean isChooser = false;

    public AddressAdapter(Context context, AddressAdapterListener mCallback) {
        this.context = context;
        this.mCallback = mCallback;
    }

    public interface AddressAdapterListener {
        void onClickItem(int position, Address address);
    }

    public void updateItemAddress(List<Address> addressList){
        this.addressList = addressList;
        notifyDataSetChanged();
    }

    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_address);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AddressAdapter.ViewHolder holder, int position) {
        final Address address = (Address) addressList.get(position);

            holder.txtAddressAs.setText(address.getMemberAddressAs());
            holder.txtAddress.setText(address.getMemberAddressDetailAddress());
            holder.txtSubZip.setText(getTextRemains(address));
            holder.txtProvince.setText(address.getMemberAddressProvinceName());
            holder.txtPhone.setText("0" + address.getMemberAddressMobilePhoneNumber());
            holder.imgEditAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {gotoAddressForm(address);
                }
            });
            holder.imgDeleteAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.createPrompt(context, "Delete", "Hapus Data?",
                            new CallbackInterface() {
                                @Override
                                public void callback() {
                                    mCallback.onClickItem(
                                            holder.getAdapterPosition(),
                                            address);
                                }
                            });
                }
            });
        /*else {
            holder.txtAddressAs.setText("");
            holder.txtAddress.setText("");
            holder.txtSubZip.setText("");
            holder.txtProvince.setText("");
            holder.txtPhone.setText("");
        }*/

       /* holder.imgEditAddressUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAddressForm2(address);
            }
        });*/

        int visibility = (isChooser) ? View.GONE : View.VISIBLE;
        holder.imgDeleteAddress.setVisibility(visibility);
        holder.imgEditAddress.setVisibility(visibility);

        if (address.getMemberAddressAs().equals("Utama")){
            holder.imgEditAddress.setVisibility(View.VISIBLE);
            holder.imgDeleteAddress.setVisibility(View.GONE);
        } else {
            holder.imgEditAddress.setVisibility(View.VISIBLE);
            holder.imgDeleteAddress.setVisibility(View.VISIBLE);
        }

        if (isChooser) {
            holder.layItemAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onClickItem(holder.getAdapterPosition(), address);
                }
            });
        }
    }

    private void gotoAddressForm(Address address) {
        Fragment fragment = AddressFormFragment
                .newInstance(Consts.UPDATE, address, Consts.LAINNYA);
        AccountSettingsActivity activity = ((AccountSettingsActivity) context);
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }

    /*private void gotoAddressForm2(Address address) {
        Fragment fragment = AddressForm2Fragment
                .newInstance(Consts.UPDATE, address, Consts.LAINNYA);
        AccountSettingsActivity activity = ((AccountSettingsActivity) context);
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }*/

    @NonNull
    private String getTextRemains(Address address) {
        return address.getMemberAddressSubdistrictName() + ", "
                + address.getMemberAddressCityName() ;
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.layItemAddress)
        RelativeLayout layItemAddress;
        @BindView(R.id.txtAddressAs)
        TextView txtAddressAs;
        @BindView(R.id.imgEditAddress)
        ImageView imgEditAddress;
        @BindView(R.id.imgDeleteAddress)
        ImageView imgDeleteAddress;
//        @BindView(R.id.imgEditAddressUtama)
//        ImageView imgEditAddressUtama;
        @BindView(R.id.txtAddress)
        TextView txtAddress;
        @BindView(R.id.txtSubZip)
        TextView txtSubZip;
        @BindView(R.id.txtProvince)
        TextView txtProvince;
        @BindView(R.id.txtPhone)
        TextView txtPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
