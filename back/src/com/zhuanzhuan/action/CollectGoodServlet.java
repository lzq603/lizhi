package com.zhuanzhuan.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuanzhuan.dao.GoodDaoImpl;
import com.zhuanzhuan.util.DaoFactory;

/**
 * Servlet implementation class CollectGoodServlet
 */
@WebServlet("/CollectGood")
public class CollectGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		String action = request.getParameter("action");
		GoodDaoImpl goodDao = DaoFactory.getGoodDao();
		if ("collect".equals(action)) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int goodId = Integer.parseInt(request.getParameter("goodId"));
			int status = goodDao.collect(userId, goodId);
			if (status > 0) {
				writer.print("{\"status\":\"OK\"}");	
			}else {
				writer.print("{\"status\":\"ERROR\"}");
			}

		}else if ("unCollect".equals(action)) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int goodId = Integer.parseInt(request.getParameter("goodId"));
			int status = goodDao.unCollect(userId, goodId);
			if (status > 0) {
				writer.print("{\"status\":\"OK\"}");	
			}else {
				writer.print("{\"status\":\"ERROR\"}");
			}
		}else if ("isCollected".equals(action)) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int goodId = Integer.parseInt(request.getParameter("goodId"));
			boolean isCollect = goodDao.isCollect(userId, goodId);
			writer.print("{\"isCollect\":" + isCollect + "}");
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
