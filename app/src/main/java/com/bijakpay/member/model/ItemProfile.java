package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 21/08/2017.
 */

public class ItemProfile implements Serializable {

    @SerializedName("member_id")
    @Expose
    private Integer memberId;
    @SerializedName("member_code")
    @Expose
    private String memberCode;
    @SerializedName("member_email")
    @Expose
    private String memberEmail;
    @SerializedName("member_status")
    @Expose
    private String memberStatus;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("member_photo")
    @Expose
    private String memberPhoto;
    @SerializedName("member_tag_line")
    @Expose
    private String memberTagLine;
    @SerializedName("member_mobile_phone_number")
    @Expose
    private Integer memberMobilePhoneNumber;
    @SerializedName("member_job")
    @Expose
    private String memberJob;
    @SerializedName("member_hobby")
    @Expose
    private String memberHobby;
    @SerializedName("member_gender")
    @Expose
    private String memberGender;
    @SerializedName("member_birthdate")
    @Expose
    private String memberBirthdate;
    @SerializedName("member_type")
    @Expose
    private String memberType;
    @SerializedName("saldo_point")
    @Expose
    private Integer saldoPoint;
    @SerializedName("saldo_ewallet")
    @Expose
    private Integer saldoEwallet;
    @SerializedName("saldo_cashback")
    @Expose
    private Integer saldoCashback;
    @SerializedName("saldo_commision")
    @Expose
    private Integer saldoCommision;
    @SerializedName("saldo_royalty")
    @Expose
    private Integer saldoRoyalty;
    @SerializedName("saldo_royalty_distributor")
    @Expose
    private Integer saldoRoyaltyDistributor;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }

    public String getMemberTagLine() {
        return memberTagLine;
    }

    public void setMemberTagLine(String memberTagLine) {
        this.memberTagLine = memberTagLine;
    }

    public Integer getMemberMobilePhoneNumber() {
        return memberMobilePhoneNumber;
    }

    public void setMemberMobilePhoneNumber(Integer memberMobilePhoneNumber) {
        this.memberMobilePhoneNumber = memberMobilePhoneNumber;
    }

    public String getMemberJob() {
        return memberJob;
    }

    public void setMemberJob(String memberJob) {
        this.memberJob = memberJob;
    }

    public String getMemberHobby() {
        return memberHobby;
    }

    public void setMemberHobby(String memberHobby) {
        this.memberHobby = memberHobby;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public String getMemberBirthdate() {
        return memberBirthdate;
    }

    public void setMemberBirthdate(String memberBirthdate) {
        this.memberBirthdate = memberBirthdate;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Integer getSaldoPoint() {
        return saldoPoint;
    }

    public void setSaldoPoint(Integer saldoPoint) {
        this.saldoPoint = saldoPoint;
    }

    public Integer getSaldoEwallet() {
        return saldoEwallet;
    }

    public void setSaldoEwallet(Integer saldoEwallet) {
        this.saldoEwallet = saldoEwallet;
    }

    public Integer getSaldoCashback() {
        return saldoCashback;
    }

    public void setSaldoCashback(Integer saldoCashback) {
        this.saldoCashback = saldoCashback;
    }

    public Integer getSaldoCommision() {
        return saldoCommision;
    }

    public void setSaldoCommision(Integer saldoCommision) {
        this.saldoCommision = saldoCommision;
    }

    public Integer getSaldoRoyalty() {
        return saldoRoyalty;
    }

    public void setSaldoRoyalty(Integer saldoRoyalty) {
        this.saldoRoyalty = saldoRoyalty;
    }

    public Integer getSaldoRoyaltyDistributor() {
        return saldoRoyaltyDistributor;
    }

    public void setSaldoRoyaltyDistributor(Integer saldoRoyaltyDistributor) {
        this.saldoRoyaltyDistributor = saldoRoyaltyDistributor;
    }
}
