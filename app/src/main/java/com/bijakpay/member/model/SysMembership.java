package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 05/05/2017.
 */

public class SysMembership implements Serializable {
    @SerializedName("sys_membership_type_id")
    @Expose
    private Integer sysMembershipTypeId;
    @SerializedName("sys_membership_type_name")
    @Expose
    private String sysMembershipTypeName;
    @SerializedName("sys_membership_type_title")
    @Expose
    private String sysMembershipTypeTitle;
    @SerializedName("sys_membership_type_cover")
    @Expose
    private String sysMembershipTypeCover;
    @SerializedName("sys_membership_type_desc")
    @Expose
    private String sysMembershipTypeDesc;
    @SerializedName("sys_membership_type_price")
    @Expose
    private Integer sysMembershipTypePrice;
    @SerializedName("sys_membership_type_quota")
    @Expose
    private Integer sysMembershipTypeQuota;
    @SerializedName("sys_membership_type_cashback_percent")
    @Expose
    private Integer sysMembershipTypeCashbackPercent;
    @SerializedName("sys_membership_type_royalty_percent")
    @Expose
    private Integer sysMembershipTypeRoyaltyPercent;
    @SerializedName("sys_membership_type_commision_percent")
    @Expose
    private Integer sysMembershipTypeCommisionPercent;
    @SerializedName("sys_membership_type_savenett_percent")
    @Expose
    private Integer sysMembershipTypeSavenettPercent;

    public Integer getSysMembershipTypeId() {
        return sysMembershipTypeId;
    }

    public void setSysMembershipTypeId(Integer sysMembershipTypeId) {
        this.sysMembershipTypeId = sysMembershipTypeId;
    }

    public String getSysMembershipTypeName() {
        return sysMembershipTypeName;
    }

    public void setSysMembershipTypeName(String sysMembershipTypeName) {
        this.sysMembershipTypeName = sysMembershipTypeName;
    }

    public String getSysMembershipTypeTitle() {
        return sysMembershipTypeTitle;
    }

    public void setSysMembershipTypeTitle(String sysMembershipTypeTitle) {
        this.sysMembershipTypeTitle = sysMembershipTypeTitle;
    }

    public String getSysMembershipTypeCover() {
        return sysMembershipTypeCover;
    }

    public void setSysMembershipTypeCover(String sysMembershipTypeCover) {
        this.sysMembershipTypeCover = sysMembershipTypeCover;
    }

    public String getSysMembershipTypeDesc() {
        return sysMembershipTypeDesc;
    }

    public void setSysMembershipTypeDesc(String sysMembershipTypeDesc) {
        this.sysMembershipTypeDesc = sysMembershipTypeDesc;
    }

    public Integer getSysMembershipTypePrice() {
        return sysMembershipTypePrice;
    }

    public void setSysMembershipTypePrice(Integer sysMembershipTypePrice) {
        this.sysMembershipTypePrice = sysMembershipTypePrice;
    }

    public Integer getSysMembershipTypeQuota() {
        return sysMembershipTypeQuota;
    }

    public void setSysMembershipTypeQuota(Integer sysMembershipTypeQuota) {
        this.sysMembershipTypeQuota = sysMembershipTypeQuota;
    }

    public Integer getSysMembershipTypeCashbackPercent() {
        return sysMembershipTypeCashbackPercent;
    }

    public void setSysMembershipTypeCashbackPercent(Integer sysMembershipTypeCashbackPercent) {
        this.sysMembershipTypeCashbackPercent = sysMembershipTypeCashbackPercent;
    }

    public Integer getSysMembershipTypeRoyaltyPercent() {
        return sysMembershipTypeRoyaltyPercent;
    }

    public void setSysMembershipTypeRoyaltyPercent(Integer sysMembershipTypeRoyaltyPercent) {
        this.sysMembershipTypeRoyaltyPercent = sysMembershipTypeRoyaltyPercent;
    }

    public Integer getSysMembershipTypeCommisionPercent() {
        return sysMembershipTypeCommisionPercent;
    }

    public void setSysMembershipTypeCommisionPercent(Integer sysMembershipTypeCommisionPercent) {
        this.sysMembershipTypeCommisionPercent = sysMembershipTypeCommisionPercent;
    }

    public Integer getSysMembershipTypeSavenettPercent() {
        return sysMembershipTypeSavenettPercent;
    }

    public void setSysMembershipTypeSavenettPercent(Integer sysMembershipTypeSavenettPercent) {
        this.sysMembershipTypeSavenettPercent = sysMembershipTypeSavenettPercent;
    }
}
