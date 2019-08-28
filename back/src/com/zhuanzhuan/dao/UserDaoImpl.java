package com.zhuanzhuan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zhuanzhuan.model.User;
import com.zhuanzhuan.util.DBUtil;

public class UserDaoImpl implements IUserDao{

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		String sql = "INSERT INTO `users` (`username`, `nickname`, `head`, `mobilephone`, `address`, `sex`, `college`, `openid`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getNickname());
			ps.setString(3, user.getHead());
			ps.setString(4, user.getMobilephone());
			ps.setString(5, user.getAddres());
			ps.setBoolean(6, user.getSex());
			ps.setString(7, user.getCollege());
			ps.setString(8, user.getOpenid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
	}

	@Override
	public void update(User user) {
		
		Connection connection = DBUtil.getConnection();
		String sql = "UPDATE `users` SET `username`=?, `nickname`=?, `head`=?, `mobilephone`=?, `address`=?, `sex`=?, `college`=?, `openid`=? WHERE (`id`=?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getNickname());
			ps.setString(3, user.getHead());
			ps.setString(4, user.getMobilephone());
			ps.setString(5, user.getAddres());
			ps.setBoolean(6, user.getSex());
			ps.setString(7, user.getCollege());
			ps.setString(8, user.getOpenid());
			ps.setInt(9, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
	}

	@Override
	public User load(int id) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setHead(rs.getString("head"));
				user.setMobilephone(rs.getString("mobilephone"));
				user.setAddres(rs.getString("address"));
				user.setSex(rs.getBoolean("sex"));
				user.setCollege(rs.getString("college"));
				user.setOpenid(rs.getString("openid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return user;
	}

	@Override
	public User loadByAppId(String openid) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE openid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, openid);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setHead(rs.getString("head"));
				user.setMobilephone(rs.getString("mobilephone"));
				user.setAddres(rs.getString("address"));
				user.setSex(rs.getBoolean("sex"));
				user.setCollege(rs.getString("college"));
				user.setOpenid(rs.getString("openid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return user;
	}
}
