package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class ItemMembership implements Serializable {

    @SerializedName("trans_membership_id")
    @Expose
    private Integer transMembershipId;
    @SerializedName("trans_membership_member_id")
    @Expose
    private Integer transMembershipMemberId;
    @SerializedName("trans_membership_member_parent_id")
    @Expose
    private Integer transMembershipMemberParentId;
    @SerializedName("trans_membership_number")
    @Expose
    private String transMembershipNumber;
    @SerializedName("trans_membership_member_type")
    @Expose
    private String transMembershipMemberType;
    @SerializedName("trans_membership_nominal")
    @Expose
    private Integer transMembershipNominal;
    @SerializedName("trans_membership_token")
    @Expose
    private String transMembershipToken;
    @SerializedName("trans_membership_datetime")
    @Expose
    private String transMembershipDatetime;

    public Integer getTransMembershipId() {
        return transMembershipId;
    }

    public void setTransMembershipId(Integer transMembershipId) {
        this.transMembershipId = transMembershipId;
    }

    public Integer getTransMembershipMemberId() {
        return transMembershipMemberId;
    }

    public void setTransMembershipMemberId(Integer transMembershipMemberId) {
        this.transMembershipMemberId = transMembershipMemberId;
    }

    public Integer getTransMembershipMemberParentId() {
        return transMembershipMemberParentId;
    }

    public void setTransMembershipMemberParentId(Integer transMembershipMemberParentId) {
        this.transMembershipMemberParentId = transMembershipMemberParentId;
    }

    public String getTransMembershipNumber() {
        return transMembershipNumber;
    }

    public void setTransMembershipNumber(String transMembershipNumber) {
        this.transMembershipNumber = transMembershipNumber;
    }

    public String getTransMembershipMemberType() {
        return transMembershipMemberType;
    }

    public void setTransMembershipMemberType(String transMembershipMemberType) {
        this.transMembershipMemberType = transMembershipMemberType;
    }

    public Integer getTransMembershipNominal() {
        return transMembershipNominal;
    }

    public void setTransMembershipNominal(Integer transMembershipNominal) {
        this.transMembershipNominal = transMembershipNominal;
    }

    public String getTransMembershipToken() {
        return transMembershipToken;
    }

    public void setTransMembershipToken(String transMembershipToken) {
        this.transMembershipToken = transMembershipToken;
    }

    public String getTransMembershipDatetime() {
        return transMembershipDatetime;
    }

    public void setTransMembershipDatetime(String transMembershipDatetime) {
        this.transMembershipDatetime = transMembershipDatetime;
    }
}
