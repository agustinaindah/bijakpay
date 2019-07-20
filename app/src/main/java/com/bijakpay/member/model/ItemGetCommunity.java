package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 25/08/2017.
 */

public class ItemGetCommunity implements Serializable {

    @SerializedName("community_id")
    @Expose
    private Integer communityId;
    @SerializedName("community_member_id")
    @Expose
    private Integer communityMemberId;
    @SerializedName("community_member_type")
    @Expose
    private String communityMemberType;
    @SerializedName("community_price")
    @Expose
    private Integer communityPrice;
    @SerializedName("community_discount")
    @Expose
    private Integer communityDiscount;
    @SerializedName("community_title")
    @Expose
    private String communityTitle;
    @SerializedName("community_desc")
    @Expose
    private String communityDesc;
    @SerializedName("community_image")
    @Expose
    private Object communityImage;
    @SerializedName("community_create_datetime")
    @Expose
    private String communityCreateDatetime;
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
    private Object sysMembershipTypeCover;
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
    @SerializedName("sys_membership_type_royalti_distributor_percent")
    @Expose
    private Integer sysMembershipTypeRoyaltiDistributorPercent;
    @SerializedName("sys_membership_type_savenett_percent")
    @Expose
    private Integer sysMembershipTypeSavenettPercent;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getCommunityMemberId() {
        return communityMemberId;
    }

    public void setCommunityMemberId(Integer communityMemberId) {
        this.communityMemberId = communityMemberId;
    }

    public String getCommunityMemberType() {
        return communityMemberType;
    }

    public void setCommunityMemberType(String communityMemberType) {
        this.communityMemberType = communityMemberType;
    }

    public Integer getCommunityPrice() {
        return communityPrice;
    }

    public void setCommunityPrice(Integer communityPrice) {
        this.communityPrice = communityPrice;
    }

    public Integer getCommunityDiscount() {
        return communityDiscount;
    }

    public void setCommunityDiscount(Integer communityDiscount) {
        this.communityDiscount = communityDiscount;
    }

    public String getCommunityTitle() {
        return communityTitle;
    }

    public void setCommunityTitle(String communityTitle) {
        this.communityTitle = communityTitle;
    }

    public String getCommunityDesc() {
        return communityDesc;
    }

    public void setCommunityDesc(String communityDesc) {
        this.communityDesc = communityDesc;
    }

    public Object getCommunityImage() {
        return communityImage;
    }

    public void setCommunityImage(Object communityImage) {
        this.communityImage = communityImage;
    }

    public String getCommunityCreateDatetime() {
        return communityCreateDatetime;
    }

    public void setCommunityCreateDatetime(String communityCreateDatetime) {
        this.communityCreateDatetime = communityCreateDatetime;
    }

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

    public Object getSysMembershipTypeCover() {
        return sysMembershipTypeCover;
    }

    public void setSysMembershipTypeCover(Object sysMembershipTypeCover) {
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

    public Integer getSysMembershipTypeRoyaltiDistributorPercent() {
        return sysMembershipTypeRoyaltiDistributorPercent;
    }

    public void setSysMembershipTypeRoyaltiDistributorPercent(Integer sysMembershipTypeRoyaltiDistributorPercent) {
        this.sysMembershipTypeRoyaltiDistributorPercent = sysMembershipTypeRoyaltiDistributorPercent;
    }

    public Integer getSysMembershipTypeSavenettPercent() {
        return sysMembershipTypeSavenettPercent;
    }

    public void setSysMembershipTypeSavenettPercent(Integer sysMembershipTypeSavenettPercent) {
        this.sysMembershipTypeSavenettPercent = sysMembershipTypeSavenettPercent;
    }
}
