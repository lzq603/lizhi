package com.zhuanzhuan.dao;

import java.util.List;

import com.zhuanzhuan.model.Good;

public interface IGoodDao {
	public int add(Good good);
	public void delete(Good good);
	public void delete(int id);
	public void update(Good good);
	public List<Good> load(String goodname);
	public List<Good> loadByCatagory(String goodname,String catagory);
	public List<Good> loadWithCondition(String goodname,String catagory,double minPrice,double maxPrice,int start,int end);
	public List<Good> loadByUser(int userId);
	public Good load(int id);
	public int collect(int userId,int goodId);
	public int unCollect(int userId,int goodId);
	public boolean isCollect(int userId,int goodId);
	public List<Good> loadByCollectUser(int userId);
}
