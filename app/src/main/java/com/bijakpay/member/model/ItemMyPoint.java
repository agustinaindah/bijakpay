package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 22/06/2017.
 */

public class ItemMyPoint implements Serializable {

    @SerializedName("member_point_reward_detail_id")
    @Expose
    private Integer memberPointRewardDetailId;
    @SerializedName("member_point_reward_detail_member_id")
    @Expose
    private Integer memberPointRewardDetailMemberId;
    @SerializedName("member_point_reward_detail_in")
    @Expose
    private String memberPointRewardDetailIn;
    @SerializedName("member_point_reward_detail_out")
    @Expose
    private String memberPointRewardDetailOut;
    @SerializedName("member_point_reward_detail_desc")
    @Expose
    private String memberPointRewardDetailDesc;
    @SerializedName("member_point_reward_detail_input_datetime")
    @Expose
    private String memberPointRewardDetailInputDatetime;

    public Integer getMemberPointRewardDetailId() {
        return memberPointRewardDetailId;
    }

    public void setMemberPointRewardDetailId(Integer memberPointRewardDetailId) {
        this.memberPointRewardDetailId = memberPointRewardDetailId;
    }

    public Integer getMemberPointRewardDetailMemberId() {
        return memberPointRewardDetailMemberId;
    }

    public void setMemberPointRewardDetailMemberId(Integer memberPointRewardDetailMemberId) {
        this.memberPointRewardDetailMemberId = memberPointRewardDetailMemberId;
    }

    public String getMemberPointRewardDetailIn() {
        return memberPointRewardDetailIn;
    }

    public void setMemberPointRewardDetailIn(String memberPointRewardDetailIn) {
        this.memberPointRewardDetailIn = memberPointRewardDetailIn;
    }

    public String getMemberPointRewardDetailOut() {
        return memberPointRewardDetailOut;
    }

    public void setMemberPointRewardDetailOut(String memberPointRewardDetailOut) {
        this.memberPointRewardDetailOut = memberPointRewardDetailOut;
    }

    public String getMemberPointRewardDetailDesc() {
        return memberPointRewardDetailDesc;
    }

    public void setMemberPointRewardDetailDesc(String memberPointRewardDetailDesc) {
        this.memberPointRewardDetailDesc = memberPointRewardDetailDesc;
    }

    public String getMemberPointRewardDetailInputDatetime() {
        return memberPointRewardDetailInputDatetime;
    }

    public void setMemberPointRewardDetailInputDatetime(String memberPointRewardDetailInputDatetime) {
        this.memberPointRewardDetailInputDatetime = memberPointRewardDetailInputDatetime;
    }
}
