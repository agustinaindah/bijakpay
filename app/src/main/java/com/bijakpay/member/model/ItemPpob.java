package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 02/05/2017.
 */

public class ItemPpob implements Serializable {
    @SerializedName("trans_ppob_id")
    @Expose
    private Integer transPpobId;
    @SerializedName("trans_ppob_member_id")
    @Expose
    private Integer transPpobMemberId;
    @SerializedName("trans_ppob_transaction_id")
    @Expose
    private Integer transPpobTransactionId;
    @SerializedName("trans_ppob_number")
    @Expose
    private String transPpobNumber;
    @SerializedName("trans_ppob_nominal")
    @Expose
    private Integer transPpobNominal;
    @SerializedName("trans_ppob_datetime")
    @Expose
    private String transPpobDatetime;
    @SerializedName("trans_ppob_desc")
    @Expose
    private String transPpobDesc;

    public Integer getTransPpobId() {
        return transPpobId;
    }

    public void setTransPpobId(Integer transPpobId) {
        this.transPpobId = transPpobId;
    }

    public Integer getTransPpobMemberId() {
        return transPpobMemberId;
    }

    public void setTransPpobMemberId(Integer transPpobMemberId) {
        this.transPpobMemberId = transPpobMemberId;
    }

    public Integer getTransPpobTransactionId() {
        return transPpobTransactionId;
    }

    public void setTransPpobTransactionId(Integer transPpobTransactionId) {
        this.transPpobTransactionId = transPpobTransactionId;
    }

    public String getTransPpobNumber() {
        return transPpobNumber;
    }

    public void setTransPpobNumber(String transPpobNumber) {
        this.transPpobNumber = transPpobNumber;
    }

    public Integer getTransPpobNominal() {
        return transPpobNominal;
    }

    public void setTransPpobNominal(Integer transPpobNominal) {
        this.transPpobNominal = transPpobNominal;
    }

    public String getTransPpobDatetime() {
        return transPpobDatetime;
    }

    public void setTransPpobDatetime(String transPpobDatetime) {
        this.transPpobDatetime = transPpobDatetime;
    }

    public String getTransPpobDesc() {
        return transPpobDesc;
    }

    public void setTransPpobDesc(String transPpobDesc) {
        this.transPpobDesc = transPpobDesc;
    }
}
