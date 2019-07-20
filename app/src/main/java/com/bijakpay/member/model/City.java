package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public class City implements Serializable {

    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("city_province_id")
    @Expose
    private Integer cityProvinceId;
    @SerializedName("city_name")
    @Expose
    private String cityName;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityProvinceId() {
        return cityProvinceId;
    }

    public void setCityProvinceId(Integer cityProvinceId) {
        this.cityProvinceId = cityProvinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
