package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 29/08/2017.
 */

public class ItemCommMember implements Serializable {

    @SerializedName("community_user_id")
    @Expose
    private Integer communityUserId;
    @SerializedName("community_user_community_id")
    @Expose
    private Integer communityUserCommunityId;
    @SerializedName("community_user_member_id")
    @Expose
    private Integer communityUserMemberId;
    @SerializedName("community_user_join_datetime")
    @Expose
    private String communityUserJoinDatetime;
    @SerializedName("community_user_note")
    @Expose
    private Object communityUserNote;
    @SerializedName("member_name")
    @Expose
    private String memberName;

    public Integer getCommunityUserId() {
        return communityUserId;
    }

    public void setCommunityUserId(Integer communityUserId) {
        this.communityUserId = communityUserId;
    }

    public Integer getCommunityUserCommunityId() {
        return communityUserCommunityId;
    }

    public void setCommunityUserCommunityId(Integer communityUserCommunityId) {
        this.communityUserCommunityId = communityUserCommunityId;
    }

    public Integer getCommunityUserMemberId() {
        return communityUserMemberId;
    }

    public void setCommunityUserMemberId(Integer communityUserMemberId) {
        this.communityUserMemberId = communityUserMemberId;
    }

    public String getCommunityUserJoinDatetime() {
        return communityUserJoinDatetime;
    }

    public void setCommunityUserJoinDatetime(String communityUserJoinDatetime) {
        this.communityUserJoinDatetime = communityUserJoinDatetime;
    }

    public Object getCommunityUserNote() {
        return communityUserNote;
    }

    public void setCommunityUserNote(Object communityUserNote) {
        this.communityUserNote = communityUserNote;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
