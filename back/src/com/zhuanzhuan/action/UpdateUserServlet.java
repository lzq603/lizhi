package com.zhuanzhuan.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuanzhuan.dao.UserDaoImpl;
import com.zhuanzhuan.model.User;
import com.zhuanzhuan.util.DaoFactory;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUser")
public class UpdateUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String userId = request.getParameter("userId");
		String nickName = request.getParameter("nickname");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		
		System.out.println(gender);
		UserDaoImpl userDao = DaoFactory.getUserDao();
		User user = userDao.load(Integer.parseInt(userId));
		if (nickName != null && !"".equals(nickName)) {
			user.setNickname(nickName);
		}
		
		if (address != null && !"".equals(address)) {
			user.setAddres(address);
		}
		
		if (gender != null && !"".equals(gender)) {
			user.setSex(Boolean.parseBoolean(gender));
		}
		
		if (phone != null && !"".equals(phone)) {
			user.setSex(Boolean.parseBoolean(phone));
		}
		
		userDao.update(user);
		
		PrintWriter writer = response.getWriter();
		writer.print("{\"status\":\"OK\",\"userId\":" + userId + "}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
