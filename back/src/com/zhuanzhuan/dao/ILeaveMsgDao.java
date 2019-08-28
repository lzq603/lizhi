package com.zhuanzhuan.dao;

import java.util.List;

import com.zhuanzhuan.model.LeaveMsg;

public interface ILeaveMsgDao {
	public int add(LeaveMsg leaveMsg);
	public List<LeaveMsg> load(int goodid,int reply);
}
