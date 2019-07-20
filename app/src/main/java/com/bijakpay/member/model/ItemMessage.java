package com.bijakpay.member.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 16/05/2017.
 */

public class ItemMessage implements Serializable {

    @SerializedName("message_id")
    @Expose
    private Integer messageId;
    @SerializedName("message_subject")
    @Expose
    private String messageSubject;
    @SerializedName("message_content")
    @Expose
    private String messageContent;
    @SerializedName("message_file")
    @Expose
    private Object messageFile;
    @SerializedName("message_sender")
    @Expose
    private String messageSender;
    @SerializedName("message_sender_type")
    @Expose
    private String messageSenderType;
    @SerializedName("message_recipient")
    @Expose
    private String messageRecipient;
    @SerializedName("message_recipient_type")
    @Expose
    private String messageRecipientType;
    @SerializedName("message_category")
    @Expose
    private String messageCategory;
    @SerializedName("message_sender_is_read")
    @Expose
    private String messageSenderIsRead;
    @SerializedName("message_recipient_is_read")
    @Expose
    private String messageRecipientIsRead;
    @SerializedName("message_input_datetime")
    @Expose
    private String messageInputDatetime;
    @SerializedName("message_target_photo")
    @Expose
    private String messageTargetPhoto;
    @SerializedName("message_target_name")
    @Expose
    private String messageTargetName;
    @SerializedName("message_sendtime")
    @Expose
    private String messageSendtime;
    @SerializedName("message_status")
    @Expose
    private String messageStatus;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Object getMessageFile() {
        return messageFile;
    }

    public void setMessageFile(Object messageFile) {
        this.messageFile = messageFile;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageSenderType() {
        return messageSenderType;
    }

    public void setMessageSenderType(String messageSenderType) {
        this.messageSenderType = messageSenderType;
    }

    public String getMessageRecipient() {
        return messageRecipient;
    }

    public void setMessageRecipient(String messageRecipient) {
        this.messageRecipient = messageRecipient;
    }

    public String getMessageRecipientType() {
        return messageRecipientType;
    }

    public void setMessageRecipientType(String messageRecipientType) {
        this.messageRecipientType = messageRecipientType;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    public String getMessageSenderIsRead() {
        return messageSenderIsRead;
    }

    public void setMessageSenderIsRead(String messageSenderIsRead) {
        this.messageSenderIsRead = messageSenderIsRead;
    }

    public String getMessageRecipientIsRead() {
        return messageRecipientIsRead;
    }

    public void setMessageRecipientIsRead(String messageRecipientIsRead) {
        this.messageRecipientIsRead = messageRecipientIsRead;
    }

    public String getMessageInputDatetime() {
        return messageInputDatetime;
    }

    public void setMessageInputDatetime(String messageInputDatetime) {
        this.messageInputDatetime = messageInputDatetime;
    }

    public String getMessageTargetPhoto() {
        return messageTargetPhoto;
    }

    public void setMessageTargetPhoto(String messageTargetPhoto) {
        this.messageTargetPhoto = messageTargetPhoto;
    }

    public String getMessageTargetName() {
        return messageTargetName;
    }

    public void setMessageTargetName(String messageTargetName) {
        this.messageTargetName = messageTargetName;
    }

    public String getMessageSendtime() {
        return messageSendtime;
    }

    public void setMessageSendtime(String messageSendtime) {
        this.messageSendtime = messageSendtime;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

}
