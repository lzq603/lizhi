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
import com.zhuanzhuan.util.StudentVerify;

/**
 * Servlet implementation class IdentifyServlet
 */
@WebServlet("/IdentifyServlet")
public class IdentifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid = request.getParameter("userid");
//		String neww = request.getParameter("new");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("u " + username);
		System.out.println("p " + password);
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		
		StudentVerify verify = new StudentVerify();
		int v = verify.verify(username, password);
		if (v == 1) {
			UserDaoImpl userDao = DaoFactory.getUserDao();
			User user = userDao.load(Integer.parseInt(userid));
			user.setUsername(username);
			userDao.update(user);
			writer.write("{\"result\":\"right\",\"str\":\"绑定成功\"}");
		}else if (v == -1) {
			writer.write("{\"result\":\"error\",\"str\":\"密码错误\"}");
		}else if (v == -2) {
			writer.write("{\"result\":\"error\",\"str\":\"尝试次数过多\"}");
		}else if (v == -4) {
			writer.write("{\"result\":\"error\",\"str\":\"未知错误\"}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
