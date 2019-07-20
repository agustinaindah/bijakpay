package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public class Address implements Serializable {

    @SerializedName("member_address_id")
    @Expose
    private Integer memberAddressId;
    @SerializedName("member_address_member_id")
    @Expose
    private Integer memberAddressMemberId;
    @SerializedName("member_address_as")
    @Expose
    private String memberAddressAs;
    @SerializedName("member_address_recipient_name")
    @Expose
    private String memberAddressRecipientName;
    @SerializedName("member_address_province_id")
    @Expose
    private Integer memberAddressProvinceId;
    @SerializedName("member_address_city_id")
    @Expose
    private Integer memberAddressCityId;
    @SerializedName("member_address_subdistrict_id")
    @Expose
    private Integer memberAddressSubdistrictId;
    @SerializedName("member_address_detail_address")
    @Expose
    private String memberAddressDetailAddress;
    @SerializedName("member_address_mobile_phone_number")
    @Expose
    private String memberAddressMobilePhoneNumber;
    @SerializedName("member_address_province_name")
    @Expose
    private String memberAddressProvinceName;
    @SerializedName("member_address_city_name")
    @Expose
    private String memberAddressCityName;
    @SerializedName("member_address_subdistrict_name")
    @Expose
    private String memberAddressSubdistrictName;

    public Integer getMemberAddressId() {
        return memberAddressId;
    }

    public void setMemberAddressId(Integer memberAddressId) {
        this.memberAddressId = memberAddressId;
    }

    public Integer getMemberAddressMemberId() {
        return memberAddressMemberId;
    }

    public void setMemberAddressMemberId(Integer memberAddressMemberId) {
        this.memberAddressMemberId = memberAddressMemberId;
    }

    public String getMemberAddressAs() {
        return memberAddressAs;
    }

    public void setMemberAddressAs(String memberAddressAs) {
        this.memberAddressAs = memberAddressAs;
    }

    public String getMemberAddressRecipientName() {
        return memberAddressRecipientName;
    }

    public void setMemberAddressRecipientName(String memberAddressRecipientName) {
        this.memberAddressRecipientName = memberAddressRecipientName;
    }

    public Integer getMemberAddressProvinceId() {
        return memberAddressProvinceId;
    }

    public void setMemberAddressProvinceId(Integer memberAddressProvinceId) {
        this.memberAddressProvinceId = memberAddressProvinceId;
    }

    public Integer getMemberAddressCityId() {
        return memberAddressCityId;
    }

    public void setMemberAddressCityId(Integer memberAddressCityId) {
        this.memberAddressCityId = memberAddressCityId;
    }

    public Integer getMemberAddressSubdistrictId() {
        return memberAddressSubdistrictId;
    }

    public void setMemberAddressSubdistrictId(Integer memberAddressSubdistrictId) {
        this.memberAddressSubdistrictId = memberAddressSubdistrictId;
    }

    public String getMemberAddressDetailAddress() {
        return memberAddressDetailAddress;
    }

    public void setMemberAddressDetailAddress(String memberAddressDetailAddress) {
        this.memberAddressDetailAddress = memberAddressDetailAddress;
    }

    public String getMemberAddressMobilePhoneNumber() {
        return memberAddressMobilePhoneNumber;
    }

    public void setMemberAddressMobilePhoneNumber(String memberAddressMobilePhoneNumber) {
        this.memberAddressMobilePhoneNumber = memberAddressMobilePhoneNumber;
    }

    public String getMemberAddressProvinceName() {
        return memberAddressProvinceName;
    }

    public void setMemberAddressProvinceName(String memberAddressProvinceName) {
        this.memberAddressProvinceName = memberAddressProvinceName;
    }

    public String getMemberAddressCityName() {
        return memberAddressCityName;
    }

    public void setMemberAddressCityName(String memberAddressCityName) {
        this.memberAddressCityName = memberAddressCityName;
    }

    public String getMemberAddressSubdistrictName() {
        return memberAddressSubdistrictName;
    }

    public void setMemberAddressSubdistrictName(String memberAddressSubdistrictName) {
        this.memberAddressSubdistrictName = memberAddressSubdistrictName;
    }

    public Province getProvince() {
        Province province = new Province();
        province.setProvinceName(getMemberAddressProvinceName());
        province.setProvinceId(getMemberAddressProvinceId());
        return province;
    }

    public City getCity() {
        City city = new City();
        city.setCityName(getMemberAddressCityName());
        city.setCityId(getMemberAddressCityId());
        city.setCityProvinceId(getMemberAddressProvinceId());
        return city;
    }

    public Subdistrict getSubdistrict() {
        Subdistrict subdistrict = new Subdistrict();
        subdistrict.setSubdistrictCityId(getMemberAddressCityId());
        subdistrict.setSubdistrictName(getMemberAddressSubdistrictName());
        subdistrict.setSubdistrictId(getMemberAddressSubdistrictId());
        return subdistrict;
    }
}
