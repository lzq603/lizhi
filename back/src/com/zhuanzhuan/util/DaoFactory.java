package com.zhuanzhuan.util;

import com.zhuanzhuan.dao.FeedbackDaoImpl;
import com.zhuanzhuan.dao.GoodDaoImpl;
import com.zhuanzhuan.dao.LeaveMsgDaoImpl;
import com.zhuanzhuan.dao.MessageDaoImpl;
import com.zhuanzhuan.dao.UserDaoImpl;

public class DaoFactory {
	public static UserDaoImpl getUserDao() {
		return new UserDaoImpl();
	}
	public static GoodDaoImpl getGoodDao() {
		return new GoodDaoImpl();
	}
	public static MessageDaoImpl getMessageDao() {
		return new MessageDaoImpl();
	}
	public static LeaveMsgDaoImpl getLeaveMsgDao() {
		return new LeaveMsgDaoImpl();
	}
	public static FeedbackDaoImpl getFeedbackDao() {
		return new FeedbackDaoImpl();
	}
}
