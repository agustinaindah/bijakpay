package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 12/05/2017.
 */

public class Bank implements Serializable {

    @SerializedName("member_bank_account_id")
    @Expose
    private Integer memberBankAccountId;
    @SerializedName("member_bank_account_member_id")
    @Expose
    private Integer memberBankAccountMemberId;
    @SerializedName("member_bank_account_as")
    @Expose
    private String memberBankAccountAs;
    @SerializedName("member_bank_account_owner_name")
    @Expose
    private String memberBankAccountOwnerName;
    @SerializedName("member_bank_account_number")
    @Expose
    private String memberBankAccountNumber;
    @SerializedName("member_bank_account_bank_name")
    @Expose
    private String memberBankAccountBankName;
    @SerializedName("member_bank_account_branch")
    @Expose
    private String memberBankAccountBranch;
    @SerializedName("member_bank_account_sms_code")
    @Expose
    private Integer memberBankAccountSmsCode;
    @SerializedName("member_bank_account_sms_expired")
    @Expose
    private String memberBankAccountSmsExpired;
    @SerializedName("member_bank_account_is_primary")
    @Expose
    private String memberBankAccountIsPrimary;

    public Integer getMemberBankAccountId() {
        return memberBankAccountId;
    }

    public void setMemberBankAccountId(Integer memberBankAccountId) {
        this.memberBankAccountId = memberBankAccountId;
    }

    public Integer getMemberBankAccountMemberId() {
        return memberBankAccountMemberId;
    }

    public void setMemberBankAccountMemberId(Integer memberBankAccountMemberId) {
        this.memberBankAccountMemberId = memberBankAccountMemberId;
    }

    public String getMemberBankAccountAs() {
        return memberBankAccountAs;
    }

    public void setMemberBankAccountAs(String memberBankAccountAs) {
        this.memberBankAccountAs = memberBankAccountAs;
    }

    public String getMemberBankAccountOwnerName() {
        return memberBankAccountOwnerName;
    }

    public void setMemberBankAccountOwnerName(String memberBankAccountOwnerName) {
        this.memberBankAccountOwnerName = memberBankAccountOwnerName;
    }

    public String getMemberBankAccountNumber() {
        return memberBankAccountNumber;
    }

    public void setMemberBankAccountNumber(String memberBankAccountNumber) {
        this.memberBankAccountNumber = memberBankAccountNumber;
    }

    public String getMemberBankAccountBankName() {
        return memberBankAccountBankName;
    }

    public void setMemberBankAccountBankName(String memberBankAccountBankName) {
        this.memberBankAccountBankName = memberBankAccountBankName;
    }

    public String getMemberBankAccountBranch() {
        return memberBankAccountBranch;
    }

    public void setMemberBankAccountBranch(String memberBankAccountBranch) {
        this.memberBankAccountBranch = memberBankAccountBranch;
    }

    public Integer getMemberBankAccountSmsCode() {
        return memberBankAccountSmsCode;
    }

    public void setMemberBankAccountSmsCode(Integer memberBankAccountSmsCode) {
        this.memberBankAccountSmsCode = memberBankAccountSmsCode;
    }

    public String getMemberBankAccountSmsExpired() {
        return memberBankAccountSmsExpired;
    }

    public void setMemberBankAccountSmsExpired(String memberBankAccountSmsExpired) {
        this.memberBankAccountSmsExpired = memberBankAccountSmsExpired;
    }

    public String getMemberBankAccountIsPrimary() {
        return memberBankAccountIsPrimary;
    }

    public void setMemberBankAccountIsPrimary(String memberBankAccountIsPrimary) {
        this.memberBankAccountIsPrimary = memberBankAccountIsPrimary;
    }
}
