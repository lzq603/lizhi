package com.zhuanzhuan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuanzhuan.util.DBUtil;

/**
 * Servlet implementation class MyStatisticServlet
 */
@WebServlet("/Statistic")
public class MyStatisticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String sql = "SELECT COUNT(*) FROM goods WHERE goodownerid = " + userId + " AND num > 0";
		String sql2 = "SELECT COUNT(1) FROM collect WHERE userId = " + userId;
		System.out.println(sql);
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int myReleased = 0;		//我发布的
		int myCollect = 0;		//我收藏的
		int myBought = 0;		//我买到的
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				myReleased = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()) {
				myCollect = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write("{\"released\":" + myReleased + ",\"collect\":" + myCollect + ",\"bought\":" + myBought + "}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
