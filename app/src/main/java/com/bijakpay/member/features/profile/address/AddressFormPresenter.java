package com.bijakpay.member.features.profile.address;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.City;
import com.bijakpay.member.model.Province;
import com.bijakpay.member.model.Subdistrict;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public interface AddressFormPresenter {

interface View extends BaseView {

        boolean validate();

        void showProvinces(List<Province> provinceList);

        void showCities(List<City> cityList);

        void showSubdistricts(List<Subdistrict> subdistrictList);

        void success();
    }

    void getProvinces();

    void getCities(int provinceId);

    void getSubdistricts(int CityId);

    void postAddress(JsonObject jsonRequest);

    void updateAddress(JsonObject jsonRequest);
}
