package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class ItemRoyalty implements Serializable {

    @SerializedName("cashback_log_id")
    @Expose
    private Integer cashbackLogId;
    @SerializedName("cashback_log_cashback_id")
    @Expose
    private Integer cashbackLogCashbackId;
    @SerializedName("cashback_log_transaction_id")
    @Expose
    private String cashbackLogTransactionId;
    @SerializedName("cashback_log_member_id")
    @Expose
    private Integer cashbackLogMemberId;
    @SerializedName("cashback_log_member_parent_id")
    @Expose
    private Integer cashbackLogMemberParentId;
    @SerializedName("cashback_log_nominal_full")
    @Expose
    private Integer cashbackLogNominalFull;
    @SerializedName("cashback_log_nominal_personal")
    @Expose
    private Integer cashbackLogNominalPersonal;
    @SerializedName("cashback_log_nominal_commission")
    @Expose
    private Integer cashbackLogNominalCommission;
    @SerializedName("cashback_log_nominal_royalty")
    @Expose
    private Integer cashbackLogNominalRoyalty;
    @SerializedName("cashback_log_nominal_internal")
    @Expose
    private Integer cashbackLogNominalInternal;
    @SerializedName("cashback_log_nominal_savenett")
    @Expose
    private Integer cashbackLogNominalSavenett;
    @SerializedName("cashback_log_datetime")
    @Expose
    private String cashbackLogDatetime;
    @SerializedName("cashback_log_desc")
    @Expose
    private String cashbackLogDesc;

    public Integer getCashbackLogId() {
        return cashbackLogId;
    }

    public void setCashbackLogId(Integer cashbackLogId) {
        this.cashbackLogId = cashbackLogId;
    }

    public Integer getCashbackLogCashbackId() {
        return cashbackLogCashbackId;
    }

    public void setCashbackLogCashbackId(Integer cashbackLogCashbackId) {
        this.cashbackLogCashbackId = cashbackLogCashbackId;
    }

    public String getCashbackLogTransactionId() {
        return cashbackLogTransactionId;
    }

    public void setCashbackLogTransactionId(String cashbackLogTransactionId) {
        this.cashbackLogTransactionId = cashbackLogTransactionId;
    }

    public Integer getCashbackLogMemberId() {
        return cashbackLogMemberId;
    }

    public void setCashbackLogMemberId(Integer cashbackLogMemberId) {
        this.cashbackLogMemberId = cashbackLogMemberId;
    }

    public Integer getCashbackLogMemberParentId() {
        return cashbackLogMemberParentId;
    }

    public void setCashbackLogMemberParentId(Integer cashbackLogMemberParentId) {
        this.cashbackLogMemberParentId = cashbackLogMemberParentId;
    }

    public Integer getCashbackLogNominalFull() {
        return cashbackLogNominalFull;
    }

    public void setCashbackLogNominalFull(Integer cashbackLogNominalFull) {
        this.cashbackLogNominalFull = cashbackLogNominalFull;
    }

    public Integer getCashbackLogNominalPersonal() {
        return cashbackLogNominalPersonal;
    }

    public void setCashbackLogNominalPersonal(Integer cashbackLogNominalPersonal) {
        this.cashbackLogNominalPersonal = cashbackLogNominalPersonal;
    }

    public Integer getCashbackLogNominalCommission() {
        return cashbackLogNominalCommission;
    }

    public void setCashbackLogNominalCommission(Integer cashbackLogNominalCommission) {
        this.cashbackLogNominalCommission = cashbackLogNominalCommission;
    }

    public Integer getCashbackLogNominalRoyalty() {
        return cashbackLogNominalRoyalty;
    }

    public void setCashbackLogNominalRoyalty(Integer cashbackLogNominalRoyalty) {
        this.cashbackLogNominalRoyalty = cashbackLogNominalRoyalty;
    }

    public Integer getCashbackLogNominalInternal() {
        return cashbackLogNominalInternal;
    }

    public void setCashbackLogNominalInternal(Integer cashbackLogNominalInternal) {
        this.cashbackLogNominalInternal = cashbackLogNominalInternal;
    }

    public Integer getCashbackLogNominalSavenett() {
        return cashbackLogNominalSavenett;
    }

    public void setCashbackLogNominalSavenett(Integer cashbackLogNominalSavenett) {
        this.cashbackLogNominalSavenett = cashbackLogNominalSavenett;
    }

    public String getCashbackLogDatetime() {
        return cashbackLogDatetime;
    }

    public void setCashbackLogDatetime(String cashbackLogDatetime) {
        this.cashbackLogDatetime = cashbackLogDatetime;
    }

    public String getCashbackLogDesc() {
        return cashbackLogDesc;
    }

    public void setCashbackLogDesc(String cashbackLogDesc) {
        this.cashbackLogDesc = cashbackLogDesc;
    }
}
