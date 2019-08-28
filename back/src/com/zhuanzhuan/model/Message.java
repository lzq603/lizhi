package com.zhuanzhuan.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.google.gson.JsonObject;

public class Message {
	public static final int UNREAD = 0;
	public static final int READ = 1;
	public static final int ALL = 2;
	private int id;
	private User send;
	private User receive;
	private Good good;
	private int status;
	private String content;
	private Timestamp time;
	private String title;
	
	public Message() {}
	public Message(User send, User receive, Good good, int status, String content, Timestamp time,
			String title) {
		this.send = send;
		this.receive = receive;
		this.good = good;
		this.status = status;
		this.content = content;
		this.time = time;
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getSend() {
		return send;
	}
	public void setSend(User send) {
		this.send = send;
	}
	public User getReceive() {
		return receive;
	}
	public void setReceive(User receive) {
		this.receive = receive;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getTimeStr(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String timeStr = sdf.format(this.time);
		return timeStr;
	}
	
	public JsonObject toJson() {
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", id);
		jsonObject.add("send", send.toJson());
		jsonObject.add("receive", receive.toJson());
		jsonObject.add("good", good.toJson());
		jsonObject.addProperty("status", status);
		jsonObject.addProperty("content", content);
		jsonObject.addProperty("time", time.toString());
		jsonObject.addProperty("title", title);
		return jsonObject;
	}
}
