package com.zhuanzhuan.model;

import com.google.gson.JsonObject;

/**  
 * @Description: �û�bean  
 * @author ��־ǿ  
 * @date 2018��4��14��    
 */  
public class User {
	
	private int id;					//С������id
	private String username;		//ѧ��
	private String nickname;		//�ǳ�
	private String head;			//ͷ���ļ���
	private String mobilephone;		//11λ�ֻ�����
	private String addres;			//סַ
	private boolean sex;			//�Ա�
	private String college;			//���ڸ�У
	private String openid;			//΢��Ψһ��ʶ
	
	public User() {
		this.head = "default.png";
	}
	
	public User(String username, String nickname, String head, String mobilephone, String addres, boolean sex,String college, String openid) {
		this.username = username;
		this.nickname = nickname;
		this.head = head;
		this.mobilephone = mobilephone;
		this.addres = addres;
		this.sex = sex;
		this.college = college;
		this.openid = openid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public boolean getSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public JsonObject toJson() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("userid", id);
		jsonObject.addProperty("username", username);
		jsonObject.addProperty("nickname", nickname);
		jsonObject.addProperty("head", head);
		jsonObject.addProperty("mobilephone", mobilephone);
		jsonObject.addProperty("address", addres);
		jsonObject.addProperty("sex", sex);
		jsonObject.addProperty("college", college);
		return jsonObject;
	}
}
