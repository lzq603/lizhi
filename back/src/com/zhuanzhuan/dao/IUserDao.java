package com.zhuanzhuan.dao;

import com.zhuanzhuan.model.User;

public interface IUserDao {
	
	public void add(User user);			//ע���û�
	
	public void update(User user);		//�����û���Ϣ
	
	public User load(int id);			//�����û���Ϣ
	
	User loadByAppId(String oppenid);	//ʹ��AppId�����û���Ϣ
	
}
