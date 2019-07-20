package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public class ItemMessageDetail implements Serializable {
    // TODO: 16/05/2017 ntar dirumah

    @SerializedName("message_reply_id")
    @Expose
    private Integer messageReplyId;
    @SerializedName("message_reply_message_id")
    @Expose
    private Integer messageReplyMessageId;
    @SerializedName("message_reply_subject")
    @Expose
    private Object messageReplySubject;
    @SerializedName("message_reply_content")
    @Expose
    private String messageReplyContent;
    @SerializedName("message_reply_file")
    @Expose
    private Object messageReplyFile;
    @SerializedName("message_reply_sender")
    @Expose
    private String messageReplySender;
    @SerializedName("message_reply_sender_type")
    @Expose
    private String messageReplySenderType;
    @SerializedName("message_reply_recipient")
    @Expose
    private String messageReplyRecipient;
    @SerializedName("message_reply_recipient_type")
    @Expose
    private String messageReplyRecipientType;
    @SerializedName("message_reply_input_datetime")
    @Expose
    private String messageReplyInputDatetime;
    @SerializedName("message_reply_sender_photo")
    @Expose
    private String messageReplySenderPhoto;
    @SerializedName("message_reply_sender_name")
    @Expose
    private String messageReplySenderName;
    @SerializedName("message_reply_recipient_photo")
    @Expose
    private String messageReplyRecipientPhoto;
    @SerializedName("message_reply_recipient_name")
    @Expose
    private String messageReplyRecipientName;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("message_reply_sendtime")
    @Expose
    private String messageReplySendtime;

    public Integer getMessageReplyId() {
        return messageReplyId;
    }

    public void setMessageReplyId(Integer messageReplyId) {
        this.messageReplyId = messageReplyId;
    }

    public Integer getMessageReplyMessageId() {
        return messageReplyMessageId;
    }

    public void setMessageReplyMessageId(Integer messageReplyMessageId) {
        this.messageReplyMessageId = messageReplyMessageId;
    }

    public Object getMessageReplySubject() {
        return messageReplySubject;
    }

    public void setMessageReplySubject(Object messageReplySubject) {
        this.messageReplySubject = messageReplySubject;
    }

    public String getMessageReplyContent() {
        return messageReplyContent;
    }

    public void setMessageReplyContent(String messageReplyContent) {
        this.messageReplyContent = messageReplyContent;
    }

    public Object getMessageReplyFile() {
        return messageReplyFile;
    }

    public void setMessageReplyFile(Object messageReplyFile) {
        this.messageReplyFile = messageReplyFile;
    }

    public String getMessageReplySender() {
        return messageReplySender;
    }

    public void setMessageReplySender(String messageReplySender) {
        this.messageReplySender = messageReplySender;
    }

    public String getMessageReplySenderType() {
        return messageReplySenderType;
    }

    public void setMessageReplySenderType(String messageReplySenderType) {
        this.messageReplySenderType = messageReplySenderType;
    }

    public String getMessageReplyRecipient() {
        return messageReplyRecipient;
    }

    public void setMessageReplyRecipient(String messageReplyRecipient) {
        this.messageReplyRecipient = messageReplyRecipient;
    }

    public String getMessageReplyRecipientType() {
        return messageReplyRecipientType;
    }

    public void setMessageReplyRecipientType(String messageReplyRecipientType) {
        this.messageReplyRecipientType = messageReplyRecipientType;
    }

    public String getMessageReplyInputDatetime() {
        return messageReplyInputDatetime;
    }

    public void setMessageReplyInputDatetime(String messageReplyInputDatetime) {
        this.messageReplyInputDatetime = messageReplyInputDatetime;
    }

    public String getMessageReplySenderPhoto() {
        return messageReplySenderPhoto;
    }

    public void setMessageReplySenderPhoto(String messageReplySenderPhoto) {
        this.messageReplySenderPhoto = messageReplySenderPhoto;
    }

    public String getMessageReplySenderName() {
        return messageReplySenderName;
    }

    public void setMessageReplySenderName(String messageReplySenderName) {
        this.messageReplySenderName = messageReplySenderName;
    }

    public String getMessageReplyRecipientPhoto() {
        return messageReplyRecipientPhoto;
    }

    public void setMessageReplyRecipientPhoto(String messageReplyRecipientPhoto) {
        this.messageReplyRecipientPhoto = messageReplyRecipientPhoto;
    }

    public String getMessageReplyRecipientName() {
        return messageReplyRecipientName;
    }

    public void setMessageReplyRecipientName(String messageReplyRecipientName) {
        this.messageReplyRecipientName = messageReplyRecipientName;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getMessageReplySendtime() {
        return messageReplySendtime;
    }

    public void setMessageReplySendtime(String messageReplySendtime) {
        this.messageReplySendtime = messageReplySendtime;
    }
}
