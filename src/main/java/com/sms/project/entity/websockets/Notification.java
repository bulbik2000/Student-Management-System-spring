package com.sms.project.entity.websockets;

public class Notification {
    private String senderId;
    private String senderName;
    
    public Notification() {}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
    
}
