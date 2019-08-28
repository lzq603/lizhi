package com.zhuanzhuan.dao;

import com.zhuanzhuan.model.User;

public interface IUserDao {
	
	public void add(User user);			//注册用户
	
	public void update(User user);		//更新用户信息
	
	public User load(int id);			//加载用户信息
	
	User loadByAppId(String oppenid);	//使用AppId加载用户信息
	
}
