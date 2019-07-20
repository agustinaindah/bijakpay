package com.bijakpay.member.features.profile.address;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.model.Address;
import com.bijakpay.member.model.City;
import com.bijakpay.member.model.Province;
import com.bijakpay.member.model.Subdistrict;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public class AddressFormFragment extends BaseFragment implements AddressFormPresenter.View {

    @BindView(R.id.edtAddressAs)
    EditText edtAddressAs;
    @BindView(R.id.edtRecipientName)
    EditText edtRecipientName;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.spinProvince)
    Spinner spinProvince;
    @BindView(R.id.spinCity)
    Spinner spinCity;
    @BindView(R.id.spinSubdistrict)
    Spinner spinSubdistrict;
    @BindView(R.id.layCity)
    LinearLayout layCity;
    @BindView(R.id.laySubdistrict)
    LinearLayout laySubdistrict;

    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.text_saved_data)
    String strSavedData;
    @BindString(R.string.address)
    String strAddress;
    @BindString(R.string.add)
    String strAdd;
    @BindString(R.string.edit)
    String strEdit;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.msg_success)
    String strSuccess;

    private int mType;
    private AddressFormPresenter mPresenter;
    private ProgressDialog progressDialog;

    private ArrayAdapter<Province> provinceArrayAdapter;
    private ArrayAdapter<City> cityArrayAdapter;
    private ArrayAdapter<Subdistrict> subdistrictArrayAdapter;

    private Address mAddress;
    private Province selectedProvince;
    private City selectedCity;
    private Subdistrict selectedSubdistrict;

    private int provinceId;
    private int cityId;
    private int subdistrictId;

    private String addressType;

    public static AddressFormFragment newInstance(int type, Address address, String addressType) {
        Bundle args = new Bundle();
        args.putInt(Consts.ARG_TYPE, type);
        args.putSerializable(Consts.ARG_DATA, address);
        args.putString(Consts.ARG_CODE, addressType);
        AddressFormFragment fragment = new AddressFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static AddressFormFragment newInstance(int type) {
        return newInstance(type, null, null);
    }

    @Override
    protected int setView() {
        return R.layout.fragment_address_from;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mType = getArguments().getInt(Consts.ARG_TYPE);
        mPresenter = new AddressFormPresenterImpl(this);
        if (mType == Consts.UPDATE) {
            mAddress = (Address) getArguments().getSerializable(Consts.ARG_DATA);
            addressType = getArguments().getString(Consts.ARG_CODE);
        }
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = ((mType == Consts.ADD) ? strAdd : strEdit) + " " + strAddress;
        getActivity().setTitle(title);
        setupSpinner();
        mPresenter.getProvinces();

        spinProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProvince = provinceArrayAdapter.getItem(position);
                mPresenter.getCities(selectedProvince.getProvinceId());
                provinceId = selectedProvince.getProvinceId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = cityArrayAdapter.getItem(position);
                mPresenter.getSubdistricts(selectedCity.getCityId());
                cityId = selectedCity.getCityId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinSubdistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubdistrict = subdistrictArrayAdapter.getItem(position);
                subdistrictId = selectedSubdistrict.getSubdistrictId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (mType == Consts.UPDATE) {
            setupEdit(mAddress);
        }
    }

    private void setupEdit(Address address) {
        edtAddressAs.setText(address.getMemberAddressAs());
        edtRecipientName.setText(address.getMemberAddressRecipientName());
        edtAddress.setText(address.getMemberAddressDetailAddress());
        edtPhone.setText("0" + address.getMemberAddressMobilePhoneNumber());
    }

    private void setupSpinner() {
        layCity.setVisibility(View.GONE);
        laySubdistrict.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSave)
    public void submitAddress(View v) {
        if (mType == Consts.ADD) {
            mPresenter.postAddress(getInput());
        } else if (mType == Consts.UPDATE) {
            mPresenter.updateAddress(getInput());
        }
    }

    @Override
    public boolean validate() {

        edtAddressAs.setError(null);
        edtRecipientName.setError(null);
        edtAddress.setError(null);
        edtPhone.setError(null);

        boolean cancel = false;
        View focus = null;

        if (TextUtils.isEmpty(getInput().get("member_address_mobile_phone_number").getAsString())) {
            edtPhone.setError(strMsgEmpty);
            focus = edtPhone;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_address_detail_address").getAsString())) {
            edtAddress.setError(strMsgEmpty);
            focus = edtAddress;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_address_recipient_name").getAsString())) {
            edtRecipientName.setError(strMsgEmpty);
            focus = edtRecipientName;
            cancel = true;
        }

        if (TextUtils.isEmpty(getInput().get("member_address_as").getAsString())) {
            edtAddressAs.setError(strMsgEmpty);
            focus = edtAddressAs;
            cancel = true;
        }

        if (cancel) {
            focus.requestFocus();
        }

        return cancel;
    }

    private JsonObject getInput() {
        JsonObject jsonInput = new JsonObject();
        try {
            String addressAs = edtAddressAs.getText().toString();
            String recipientName = edtRecipientName.getText().toString();
            String address = edtAddress.getText().toString();
            String phone = edtPhone.getText().toString();

            jsonInput.addProperty("member_address_as", addressAs);
            jsonInput.addProperty("member_address_recipient_name", recipientName);
            jsonInput.addProperty("member_address_detail_address", address);
            jsonInput.addProperty("member_address_province_id", provinceId);
            jsonInput.addProperty("member_address_city_id", cityId);
            jsonInput.addProperty("member_address_subdistrict_id", subdistrictId);
            jsonInput.addProperty("member_address_mobile_phone_number", phone);
            if (mType == Consts.UPDATE) {
                jsonInput.addProperty("member_address_id",mAddress.getMemberAddressId());
                jsonInput.addProperty("member_address_member_id", mAddress.getMemberAddressMemberId());
                jsonInput.addProperty("member_address_as", addressType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;

    }

    @Override
    public void showProvinces(List<Province> provinceList) {
        provinceArrayAdapter = new ArrayAdapter<Province>(getActivity(),
                android.R.layout.simple_list_item_1, provinceList
                .toArray(new Province[provinceList.size()]));
        spinProvince.setAdapter(provinceArrayAdapter);
        if (mType == Consts.UPDATE) {
            int idx = 0;
            for (int r = 0; r < provinceArrayAdapter.getCount(); r++) {
                if (mAddress.getMemberAddressProvinceId() == provinceArrayAdapter.getItem(r).getProvinceId()) {
                    idx = r;
                    break;
                }
            }
            spinProvince.setSelection(idx);
        }
    }

    @Override
    public void showCities(List<City> cityList) {
        layCity.setVisibility(View.VISIBLE);
        cityArrayAdapter = new ArrayAdapter<City>(getActivity(),
                android.R.layout.simple_list_item_1, cityList.toArray(new City[cityList.size()]));
        spinCity.setAdapter(cityArrayAdapter);
        if (mType == Consts.UPDATE) {
            int idx = 0;
            for (int r = 0; r < cityArrayAdapter.getCount(); r++) {
                if (mAddress.getMemberAddressCityId() == cityArrayAdapter.getItem(r).getCityId()) {
                    idx = r;
                    break;
                }
            }
            spinCity.setSelection(idx);
        }
    }

    @Override
    public void showSubdistricts(List<Subdistrict> subdistrictList) {
        laySubdistrict.setVisibility(View.VISIBLE);
        subdistrictArrayAdapter = new ArrayAdapter<Subdistrict>(getActivity(),
                android.R.layout.simple_list_item_1, subdistrictList
                .toArray(new Subdistrict[subdistrictList.size()]));
        spinSubdistrict.setAdapter(subdistrictArrayAdapter);
        if (mType == Consts.UPDATE) {
            int idx = 0;
            for (int r = 0; r < subdistrictArrayAdapter.getCount(); r++) {
                if (mAddress.getMemberAddressSubdistrictId() == subdistrictArrayAdapter.getItem(r).getSubdistrictId()) {
                    idx = r;
                    break;
                }
            }
            spinSubdistrict.setSelection(idx);
        }
    }

    @Override
    public void success() {
        Helper.createAlert(getActivity(), strSuccess, strSavedData, false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg, false, new CallbackInterface() {
            @Override
            public void callback() {
                getActivity().onBackPressed();
            }
        });
    }
}
