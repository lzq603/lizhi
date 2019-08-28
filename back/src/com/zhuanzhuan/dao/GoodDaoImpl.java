package com.zhuanzhuan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhuanzhuan.model.Good;
import com.zhuanzhuan.model.User;
import com.zhuanzhuan.util.DBUtil;
import com.zhuanzhuan.util.DaoFactory;

/**  
 * @Description: ��ƷDao  
 * @author ��־ǿ  
 * @date 2018��4��15��    
 */  
public class GoodDaoImpl implements IGoodDao {

	@Override
	public int add(Good good) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		String sql = "INSERT INTO goods (goodname,goodownerid,price,description,image,num,catagory, time) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		try {
			ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, good.getGoodname());
			ps.setInt(2, good.getGoodowner().getId());
			ps.setDouble(3, good.getPrice());
			ps.setString(4, good.getDescription());
			ps.setString(5, good.getImagesStr());
			ps.setInt(6, good.getNum());
			ps.setString(7, good.getCatagory());
			ps.setTimestamp(8, good.getTime());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return id;
	}

	@Override
	public void delete(Good good) {
		System.out.println("�÷���δ����");
	}

	@Override
	public void update(Good good) {
		Connection connection = DBUtil.getConnection();
		String sql = "UPDATE goods SET "
				+ "goodname=?,"
				+ "price=?,"
				+ "description=?,"
				+ "image=?,"
				+ "num=?,"
				+ "catagory=?,"
				+ "time=? "
				+ "WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, good.getGoodname());
			ps.setDouble(2, good.getPrice());
			ps.setString(3, good.getDescription());
			ps.setString(4, good.getImagesStr());
			ps.setInt(5, good.getNum());
			ps.setString(6, good.getCatagory());
			ps.setTimestamp(7, good.getTime());
			ps.setInt(8, good.getId());
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
	public List<Good> load(String goodname) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT id,goodname,goodownerid,price,description,image,num,catagory,time, COUNT(DISTINCT lid) leave_msg,COUNT(DISTINCT userId) collect FROM good WHERE goodname like ? and num > 0 GROUP BY id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Good> goods = new ArrayList<Good>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%"+goodname+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				
				UserDaoImpl userDao = DaoFactory.getUserDao();
				User user = userDao.load(rs.getInt("goodownerid"));
				
				Good good = new Good();
				good.setId(rs.getInt("id"));
				good.setGoodname(rs.getString("goodname"));
				good.setGoodowner(user);
				good.setPrice(rs.getDouble("price"));
				good.setDescription(rs.getString("description"));
				good.setImages(rs.getString("image"));
				good.setNum(rs.getInt("num"));
				good.setCatagory(rs.getString("catagory"));
				good.setTime(rs.getTimestamp("time"));
				good.setLeaveMsgNum(rs.getInt("leave_msg"));
				good.setCollectNum(rs.getInt("collect"));
				goods.add(good);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return goods;
	}

	@Override
	public void delete(int id) {
		Connection connection = DBUtil.getConnection();
		String sql = "DELETE FROM goods WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
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
	public Good load(int id) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT id,goodname,goodownerid,price,description,image,num,catagory,time, COUNT(DISTINCT lid) leave_msg,COUNT(DISTINCT userId) collect FROM good WHERE id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Good good = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				UserDaoImpl userDao = DaoFactory.getUserDao();
				User user = userDao.load(rs.getInt("goodownerid"));
				
				good = new Good();
				good.setId(rs.getInt("id"));
				good.setGoodname(rs.getString("goodname"));
				good.setGoodowner(user);
				good.setPrice(rs.getDouble("price"));
				good.setDescription(rs.getString("description"));
				good.setImages(rs.getString("image"));
				good.setNum(rs.getInt("num"));
				good.setCatagory(rs.getString("catagory"));
				good.setTime(rs.getTimestamp("time"));
				good.setLeaveMsgNum(rs.getInt("leave_msg"));
				good.setCollectNum(rs.getInt("collect"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return good;
	}

	@Override
	public List<Good> loadByCatagory(String goodname, String catagory) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT id,goodname,goodownerid,price,description,image,num,catagory,time, COUNT(DISTINCT lid) leave_msg,COUNT(DISTINCT userId) collect FROM good WHERE goodname like ? and catagory like ? and num > 0 GROUP BY id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Good> goods = new ArrayList<Good>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + goodname + "%");
			ps.setString(2, "%" + catagory + "%");
			rs = ps.executeQuery();
			while(rs.next()) {
				
				UserDaoImpl userDao = DaoFactory.getUserDao();
				User user = userDao.load(rs.getInt("goodownerid"));
				
				Good good = new Good();
				good.setId(rs.getInt("id"));
				good.setGoodname(rs.getString("goodname"));
				good.setGoodowner(user);
				good.setPrice(rs.getDouble("price"));
				good.setDescription(rs.getString("description"));
				good.setImages(rs.getString("image"));
				good.setNum(rs.getInt("num"));
				good.setCatagory(rs.getString("catagory"));
				good.setTime(rs.getTimestamp("time"));
				good.setLeaveMsgNum(rs.getInt("leave_msg"));
				good.setCollectNum(rs.getInt("collect"));
				goods.add(good);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return goods;
	}
	
	public List<Good> loadWithCondition(String goodname,String catagory,double minPrice,double maxPrice,int start,int end)
	{
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT id,goodname,goodownerid,price,description,image,num,catagory,time, COUNT(DISTINCT lid) leave_msg,COUNT(DISTINCT userId) collect FROM good WHERE goodname like ? and catagory like ? and price > ? and price < ? and num > 0 GROUP BY id ORDER BY time DESC LIMIT ?,?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Good> goods = new ArrayList<Good>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + goodname + "%");
			ps.setString(2, "%" + catagory + "%");
			ps.setDouble(3, minPrice);
			ps.setDouble(4, maxPrice);
			ps.setInt(5, start);
			ps.setInt(6, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				UserDaoImpl userDao = DaoFactory.getUserDao();
				User user = userDao.load(rs.getInt("goodownerid"));
				
				Good good = new Good();
				good.setId(rs.getInt("id"));
				good.setGoodname(rs.getString("goodname"));
				good.setGoodowner(user);
				good.setPrice(rs.getDouble("price"));
				good.setDescription(rs.getString("description"));
				good.setImages(rs.getString("image"));
				good.setNum(rs.getInt("num"));
				good.setCatagory(rs.getString("catagory"));
				good.setTime(rs.getTimestamp("time"));
				good.setLeaveMsgNum(rs.getInt("leave_msg"));
				good.setCollectNum(rs.getInt("collect"));
				goods.add(good);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return goods;
	}

	@Override
	public List<Good> loadByUser(int userId) {

		Connection connection = DBUtil.getConnection();
		String sql = "SELECT id,goodname,goodownerid,price,description,image,num,catagory,time, COUNT(DISTINCT lid) leave_msg,COUNT(DISTINCT userId) collect FROM good WHERE goodownerid = ? AND num > 0 GROUP BY id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Good> goods = new ArrayList<Good>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				UserDaoImpl userDao = DaoFactory.getUserDao();
				User user = userDao.load(rs.getInt("goodownerid"));
				
				Good good = new Good();
				good.setId(rs.getInt("id"));
				good.setGoodname(rs.getString("goodname"));
				good.setGoodowner(user);
				good.setPrice(rs.getDouble("price"));
				good.setDescription(rs.getString("description"));
				good.setImages(rs.getString("image"));
				good.setNum(rs.getInt("num"));
				good.setCatagory(rs.getString("catagory"));
				good.setTime(rs.getTimestamp("time"));
				good.setLeaveMsgNum(rs.getInt("leave_msg"));
				good.setCollectNum(rs.getInt("collect"));
				goods.add(good);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return goods;
	}

	@Override
	public int collect(int userId, int goodId) {
		
		Connection connection = DBUtil.getConnection();
		String sql = "INSERT INTO `collect` (`userId`, `goodId`) VALUES (?, ?)";
		PreparedStatement ps = null;
		int status = 0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodId);
			status = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return status;
	}

	@Override
	public int unCollect(int userId, int goodId) {
		
		int status = 0;
		Connection connection = DBUtil.getConnection();
		String sql = "DELETE FROM `collect` WHERE (`userId`=?) AND (`goodId`=?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodId);
			status = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return status;
	}

	@Override
	public boolean isCollect(int userId, int goodId) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM collect WHERE userId = ? AND goodId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, goodId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return false;
	}

	@Override
	public List<Good> loadByCollectUser(int userId) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT id,goodname,goodownerid,price,description,image,num,catagory,time, COUNT(DISTINCT lid) leave_msg,COUNT(DISTINCT collect.userId) collect FROM good JOIN collect ON good.id = collect.goodId WHERE collect.userId = ? AND num > 0 GROUP BY id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Good> goods = new ArrayList<Good>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				UserDaoImpl userDao = DaoFactory.getUserDao();
				User user = userDao.load(rs.getInt("goodownerid"));
				
				Good good = new Good();
				good.setId(rs.getInt("id"));
				good.setGoodname(rs.getString("goodname"));
				good.setGoodowner(user);
				good.setPrice(rs.getDouble("price"));
				good.setDescription(rs.getString("description"));
				good.setImages(rs.getString("image"));
				good.setNum(rs.getInt("num"));
				good.setCatagory(rs.getString("catagory"));
				good.setTime(rs.getTimestamp("time"));
				good.setLeaveMsgNum(rs.getInt("leave_msg"));
				good.setCollectNum(rs.getInt("collect"));
				goods.add(good);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(connection);
		}
		return goods;
	}
}
