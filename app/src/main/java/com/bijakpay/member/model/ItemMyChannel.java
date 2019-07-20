package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 23/08/2017.
 */

public class ItemMyChannel implements Serializable {

    @SerializedName("member_id")
    @Expose
    private Integer memberId;
    @SerializedName("member_parent_id")
    @Expose
    private Integer memberParentId;
    @SerializedName("member_email")
    @Expose
    private String memberEmail;
    @SerializedName("member_code")
    @Expose
    private String memberCode;
    @SerializedName("member_password")
    @Expose
    private String memberPassword;
    @SerializedName("member_password_temp")
    @Expose
    private Integer memberPasswordTemp;
    @SerializedName("member_hash")
    @Expose
    private String memberHash;
    @SerializedName("member_sms_code")
    @Expose
    private Integer memberSmsCode;
    @SerializedName("member_sms_expired")
    @Expose
    private String memberSmsExpired;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("member_photo")
    @Expose
    private String memberPhoto;
    @SerializedName("member_tag_line")
    @Expose
    private Object memberTagLine;
    @SerializedName("member_mobile_phone_number")
    @Expose
    private Integer memberMobilePhoneNumber;
    @SerializedName("member_job")
    @Expose
    private Object memberJob;
    @SerializedName("member_hobby")
    @Expose
    private Object memberHobby;
    @SerializedName("member_status")
    @Expose
    private String memberStatus;
    @SerializedName("member_gender")
    @Expose
    private String memberGender;
    @SerializedName("member_birthdate")
    @Expose
    private Object memberBirthdate;
    @SerializedName("member_registration_date")
    @Expose
    private String memberRegistrationDate;
    @SerializedName("member_last_login_date")
    @Expose
    private Object memberLastLoginDate;
    @SerializedName("member_type")
    @Expose
    private String memberType;
    @SerializedName("member_recovery_hash")
    @Expose
    private String memberRecoveryHash;
    @SerializedName("member_recovery_hash_expired")
    @Expose
    private Object memberRecoveryHashExpired;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberParentId() {
        return memberParentId;
    }

    public void setMemberParentId(Integer memberParentId) {
        this.memberParentId = memberParentId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public Integer getMemberPasswordTemp() {
        return memberPasswordTemp;
    }

    public void setMemberPasswordTemp(Integer memberPasswordTemp) {
        this.memberPasswordTemp = memberPasswordTemp;
    }

    public String getMemberHash() {
        return memberHash;
    }

    public void setMemberHash(String memberHash) {
        this.memberHash = memberHash;
    }

    public Integer getMemberSmsCode() {
        return memberSmsCode;
    }

    public void setMemberSmsCode(Integer memberSmsCode) {
        this.memberSmsCode = memberSmsCode;
    }

    public String getMemberSmsExpired() {
        return memberSmsExpired;
    }

    public void setMemberSmsExpired(String memberSmsExpired) {
        this.memberSmsExpired = memberSmsExpired;
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

    public Object getMemberTagLine() {
        return memberTagLine;
    }

    public void setMemberTagLine(Object memberTagLine) {
        this.memberTagLine = memberTagLine;
    }

    public Integer getMemberMobilePhoneNumber() {
        return memberMobilePhoneNumber;
    }

    public void setMemberMobilePhoneNumber(Integer memberMobilePhoneNumber) {
        this.memberMobilePhoneNumber = memberMobilePhoneNumber;
    }

    public Object getMemberJob() {
        return memberJob;
    }

    public void setMemberJob(Object memberJob) {
        this.memberJob = memberJob;
    }

    public Object getMemberHobby() {
        return memberHobby;
    }

    public void setMemberHobby(Object memberHobby) {
        this.memberHobby = memberHobby;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(String memberGender) {
        this.memberGender = memberGender;
    }

    public Object getMemberBirthdate() {
        return memberBirthdate;
    }

    public void setMemberBirthdate(Object memberBirthdate) {
        this.memberBirthdate = memberBirthdate;
    }

    public String getMemberRegistrationDate() {
        return memberRegistrationDate;
    }

    public void setMemberRegistrationDate(String memberRegistrationDate) {
        this.memberRegistrationDate = memberRegistrationDate;
    }

    public Object getMemberLastLoginDate() {
        return memberLastLoginDate;
    }

    public void setMemberLastLoginDate(Object memberLastLoginDate) {
        this.memberLastLoginDate = memberLastLoginDate;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberRecoveryHash() {
        return memberRecoveryHash;
    }

    public void setMemberRecoveryHash(String memberRecoveryHash) {
        this.memberRecoveryHash = memberRecoveryHash;
    }

    public Object getMemberRecoveryHashExpired() {
        return memberRecoveryHashExpired;
    }

    public void setMemberRecoveryHashExpired(Object memberRecoveryHashExpired) {
        this.memberRecoveryHashExpired = memberRecoveryHashExpired;
    }
}
