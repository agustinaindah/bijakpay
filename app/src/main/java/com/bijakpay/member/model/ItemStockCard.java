package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 03/05/2017.
 */

public class ItemStockCard implements Serializable {

    @SerializedName("stock_card_id")
    @Expose
    private Integer stockCardId;
    @SerializedName("stock_card_member_id")
    @Expose
    private Integer stockCardMemberId;
    @SerializedName("stock_card_token")
    @Expose
    private String stockCardToken;
    @SerializedName("stock_card_create_datetime")
    @Expose
    private String stockCardCreateDatetime;
    @SerializedName("stock_card_status")
    @Expose
    private String stockCardStatus;
    @SerializedName("stock_card_activated_datetime")
    @Expose
    private Object stockCardActivatedDatetime;
    @SerializedName("stock_card_pin")
    @Expose
    private Integer stockCardPin;
    @SerializedName("stock_card_member_type")
    @Expose
    private String stockCardMemberType;

    public Integer getStockCardId() {
        return stockCardId;
    }

    public void setStockCardId(Integer stockCardId) {
        this.stockCardId = stockCardId;
    }

    public Integer getStockCardMemberId() {
        return stockCardMemberId;
    }

    public void setStockCardMemberId(Integer stockCardMemberId) {
        this.stockCardMemberId = stockCardMemberId;
    }

    public String getStockCardToken() {
        return stockCardToken;
    }

    public void setStockCardToken(String stockCardToken) {
        this.stockCardToken = stockCardToken;
    }

    public String getStockCardCreateDatetime() {
        return stockCardCreateDatetime;
    }

    public void setStockCardCreateDatetime(String stockCardCreateDatetime) {
        this.stockCardCreateDatetime = stockCardCreateDatetime;
    }

    public String getStockCardStatus() {
        return stockCardStatus;
    }

    public void setStockCardStatus(String stockCardStatus) {
        this.stockCardStatus = stockCardStatus;
    }

    public Object getStockCardActivatedDatetime() {
        return stockCardActivatedDatetime;
    }

    public void setStockCardActivatedDatetime(Object stockCardActivatedDatetime) {
        this.stockCardActivatedDatetime = stockCardActivatedDatetime;
    }

    public Integer getStockCardPin() {
        return stockCardPin;
    }

    public void setStockCardPin(Integer stockCardPin) {
        this.stockCardPin = stockCardPin;
    }

    public String getStockCardMemberType() {
        return stockCardMemberType;
    }

    public void setStockCardMemberType(String stockCardMemberType) {
        this.stockCardMemberType = stockCardMemberType;
    }
}
