package com.zhuanzhuan.model;

import java.sql.Timestamp;

public class Feedback {
	
	private int id;
	private String content;
	private String contact;
	private Timestamp time;
	
	public Feedback(String content, String contact, Timestamp time) {
		this.content = content;
		this.contact = contact;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public String getContact() {
		return contact;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
