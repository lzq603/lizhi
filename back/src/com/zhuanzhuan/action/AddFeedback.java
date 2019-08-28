package com.zhuanzhuan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuanzhuan.dao.FeedbackDaoImpl;
import com.zhuanzhuan.model.Feedback;
import com.zhuanzhuan.util.DaoFactory;

/**
 * Servlet implementation class AddFeedback
 */
@WebServlet("/AddFeedback")
public class AddFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		String content = request.getParameter("content");
		String contact = request.getParameter("contact");
		FeedbackDaoImpl feedbackDao = DaoFactory.getFeedbackDao();
		Feedback feedback = new Feedback(content, contact, new Timestamp(System.currentTimeMillis()));
		int status = feedbackDao.addFeedback(feedback);
		if (status > 0) {
			writer.print("{\"status\":\"OK\"}");
		}else {
			writer.print("{\"status\":\"ERROR\",\"err\":\"未知错误\"}");
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
