package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public class Subdistrict implements Serializable {

    @SerializedName("subdistrict_id")
    @Expose
    private Integer subdistrictId;
    @SerializedName("subdistrict_city_id")
    @Expose
    private Integer subdistrictCityId;
    @SerializedName("subdistrict_name")
    @Expose
    private String subdistrictName;

    public Integer getSubdistrictId() {
        return subdistrictId;
    }

    public void setSubdistrictId(Integer subdistrictId) {
        this.subdistrictId = subdistrictId;
    }

    public Integer getSubdistrictCityId() {
        return subdistrictCityId;
    }

    public void setSubdistrictCityId(Integer subdistrictCityId) {
        this.subdistrictCityId = subdistrictCityId;
    }

    public String getSubdistrictName() {
        return subdistrictName;
    }

    public void setSubdistrictName(String subdistrictName) {
        this.subdistrictName = subdistrictName;
    }

    @Override
    public String toString() {
        return subdistrictName;

    }
}
