package com.zhuanzhuan.model;

import java.sql.Timestamp;

import com.google.gson.JsonObject;

public class LeaveMsg {
	
	private int id;
	private User send;
	private User receive;
	private Good good;
	private String content;
	private Timestamp time;
	private int reply;
	
	public LeaveMsg() {}
	
	public LeaveMsg(User send, User receive, Good good, String content, Timestamp time, int reply) {

		this.send = send;
		this.receive = receive;
		this.good = good;
		this.content = content;
		this.time = time;
		this.reply = reply;
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
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	
	public JsonObject toJSON() {
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", this.getId());
		jsonObject.add("send", this.getSend().toJson());
		jsonObject.add("receive", this.getReceive().toJson());
		jsonObject.add("good", this.getGood().toJson());
		jsonObject.addProperty("content", this.getContent());
		jsonObject.addProperty("time", this.getTime().toString());
		jsonObject.addProperty("reply", this.getReply());
		
		return jsonObject;
		
	}
}
