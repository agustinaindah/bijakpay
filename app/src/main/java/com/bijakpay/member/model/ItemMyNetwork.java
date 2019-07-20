package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 22/08/2017.
 */

public class ItemMyNetwork implements Serializable{

    @SerializedName("member_id")
    @Expose
    private Integer memberId;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("jalur")
    @Expose
    private Integer jalur;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getJalur() {
        return jalur;
    }

    public void setJalur(Integer jalur) {
        this.jalur = jalur;
    }
}
