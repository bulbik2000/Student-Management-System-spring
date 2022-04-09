package com.sms.project.entity.websockets;


public class Message {
	private String senderId;
	private String recipientId;
	private String senderName;
	private String content;
	
	public Message() {}
	
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

	

	@Override
	public String toString() {
		return "Message [senderId=" + senderId + ", recipientId=" + recipientId + ", senderName=" + senderName
				+ ", content=" + content;
	}
	
}
