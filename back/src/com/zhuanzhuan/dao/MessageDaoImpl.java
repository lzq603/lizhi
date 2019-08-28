package com.zhuanzhuan.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhuanzhuan.model.Message;
import com.zhuanzhuan.util.DBUtil;
import com.zhuanzhuan.util.DaoFactory;

public class MessageDaoImpl implements IMessageDao {

	@Override
	public void add(Message message) {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "insert into message(senduserid,reciveuserid,status,content,time,title,goodid) value(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, message.getSend().getId());
			preparedStatement.setInt(2, message.getReceive().getId());
			preparedStatement.setInt(3, message.getStatus());
			preparedStatement.setString(4, message.getContent());
			preparedStatement.setTimestamp(5, new Timestamp(new Date().getTime()));
			preparedStatement.setString(6, message.getTitle());
			preparedStatement.setInt(7, message.getGood().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "delete from message where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}

	@Override
	public Message loadone(int id) {
		
		UserDaoImpl userDao = DaoFactory.getUserDao();
		GoodDaoImpl goodDao = DaoFactory.getGoodDao();
		
		Connection connection = DBUtil.getConnection();
		String sql = "select * from message where id=?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Message message = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				message=new Message();
				message.setId(id);
				message.setSend(userDao.load(resultSet.getInt("senduserid")));
				message.setReceive(userDao.load(resultSet.getInt("reciveuserid")));
				message.setContent(resultSet.getString("content"));
				message.setStatus(resultSet.getInt("status"));
				message.setTime(resultSet.getTimestamp("time"));
				message.setTitle(resultSet.getString("title"));
				message.setGood(goodDao.load(resultSet.getInt("goodid")));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
				
		return message;
	}

	@Override
	public List<Message> load(int sendid, int reciveid) {
		// TODO Auto-generated method stub
		UserDaoImpl userDao = DaoFactory.getUserDao();
		GoodDaoImpl goodDao = DaoFactory.getGoodDao();
		
		List<Message> messages = new ArrayList<Message>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from message where senduserid=? and reciveuserid=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sendid);
			preparedStatement.setInt(2, reciveid);
			resultSet = preparedStatement.executeQuery();
			Message message = null;
			while(resultSet.next()) {
				message = new Message();
				message.setId(resultSet.getInt("id"));
				message.setSend(userDao.load(sendid));
				message.setReceive(userDao.load(reciveid));
				message.setContent(resultSet.getString("content"));
				message.setStatus(resultSet.getInt("status"));
				message.setTime(resultSet.getTimestamp("time"));
				message.setTitle(resultSet.getString("title"));
				message.setGood(goodDao.load(resultSet.getInt("goodid")));
				messages.add(message);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return messages;
	}
	@Override
	public List<Message> load(int reciveid) {
		// TODO Auto-generated method stub
		UserDaoImpl userDao = DaoFactory.getUserDao();
		GoodDaoImpl goodDao = DaoFactory.getGoodDao();
		
		List<Message> messages = new ArrayList<Message>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from message where reciveuserid=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reciveid);
			resultSet = preparedStatement.executeQuery();
			Message message = null;
			while(resultSet.next()) {
				message = new Message();
				message.setId(resultSet.getInt("id"));
				message.setSend(userDao.load(resultSet.getInt("senduserid")));
				message.setReceive(userDao.load(reciveid));
				message.setContent(resultSet.getString("content"));
				message.setStatus(resultSet.getInt("status"));
				message.setTime(resultSet.getTimestamp("time"));
				message.setTitle(resultSet.getString("title"));
				message.setGood(goodDao.load(resultSet.getInt("goodid")));
				messages.add(message);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return messages;
	}

	@Override
	public List<Message> loadWithStatus(int sendid, int receiveid, int status) {
		// TODO Auto-generated method stub
		UserDaoImpl userDao = DaoFactory.getUserDao();
		GoodDaoImpl goodDao = DaoFactory.getGoodDao();
		
		List<Message> messages = new ArrayList<Message>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from first_message where reciveuserid=? or senduserid = ?";
			if (status != Message.ALL) {
				sql = "select * from first_message where (reciveuserid=? or senduserid=?) and status=?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(3, status);
			}else {
				preparedStatement = connection.prepareStatement(sql);
			}
			preparedStatement.setInt(1, receiveid);
			preparedStatement.setInt(2, sendid);
			
			System.out.println(preparedStatement);
			
			if (status != Message.ALL) 
				preparedStatement.setInt(2, status);
			resultSet = preparedStatement.executeQuery();
			Message message = null;
			while(resultSet.next()) {
				message = new Message();
				message.setId(resultSet.getInt("id"));
				message.setSend(userDao.load(resultSet.getInt("senduserid")));
				message.setReceive(userDao.load(resultSet.getInt("reciveuserid")));
				message.setContent(resultSet.getString("content"));
				message.setStatus(resultSet.getShort("status"));
				message.setTime(resultSet.getTimestamp("time"));
				message.setTitle(resultSet.getString("title"));
				message.setGood(goodDao.load(resultSet.getInt("goodid")));
				messages.add(message);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return messages;
	}

	@Override
	public void update(Message message) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		String sql = "update message set senduserid=? , reciveuserid=?,status=?,content=?,title=?,goodid=? where id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1,message.getSend().getId());
			ps.setInt(2,message.getReceive().getId());
			ps.setInt(3,message.getStatus());		
			ps.setString(4, message.getContent());
			ps.setString(5,message.getTitle());
			ps.setInt(6, message.getGood().getId());
			ps.setInt(7, message.getId());
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
	public void deleteByReciveId(int reciveid) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "delete from message where reciveuserid=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reciveid);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}

	@Override
	public List<Message> loadWithCondition(int receiveid, int sendid, int goodid) {
		// TODO Auto-generated method stub
		UserDaoImpl userDao = DaoFactory.getUserDao();
		GoodDaoImpl goodDao = DaoFactory.getGoodDao();
		
		List<Message> messages = new ArrayList<Message>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from message where ((reciveuserid=? and senduserid=?) or (reciveuserid=? and senduserid=?)) and goodid=? order by time";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, receiveid);
			preparedStatement.setInt(2, sendid);
			preparedStatement.setInt(3, sendid);
			preparedStatement.setInt(4, receiveid);
			preparedStatement.setInt(5, goodid);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			Message message = null;
			while(resultSet.next()) {
				message = new Message();
				message.setId(resultSet.getInt("id"));
				message.setSend(userDao.load(resultSet.getInt("senduserid")));
				message.setReceive(userDao.load(resultSet.getInt("reciveuserid")));
				message.setContent(resultSet.getString("content"));
				message.setStatus(resultSet.getShort("status"));
				message.setTime(resultSet.getTimestamp("time"));
				message.setTitle(resultSet.getString("title"));
				message.setGood(goodDao.load(goodid));
				messages.add(message);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return messages;
	}

}
