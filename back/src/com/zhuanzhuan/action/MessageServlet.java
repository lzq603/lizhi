package com.zhuanzhuan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.zhuanzhuan.dao.GoodDaoImpl;
import com.zhuanzhuan.dao.LeaveMsgDaoImpl;
import com.zhuanzhuan.dao.MessageDaoImpl;
import com.zhuanzhuan.dao.UserDaoImpl;
import com.zhuanzhuan.model.Good;
import com.zhuanzhuan.model.LeaveMsg;
import com.zhuanzhuan.model.Message;
import com.zhuanzhuan.model.User;
import com.zhuanzhuan.util.DaoFactory;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();

		//在商品下留言
		if ("leave_msg".equals(request.getParameter("type"))) {
			
			String send = request.getParameter("send");
			String receive = request.getParameter("receive");
			String good = request.getParameter("good");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			String content = request.getParameter("content");
			String reply = request.getParameter("reply");
			int sendid = 0;
			int receiveid = 0;
			int goodid = 0;
			int replyid = 0;
			try {
				sendid = Integer.parseInt(send);
				receiveid = Integer.parseInt(receive);
				goodid = Integer.parseInt(good);
				if (reply != null) {
					replyid = Integer.parseInt(reply);
				}
				System.out.println(sendid);
				System.out.println(receiveid);
				System.out.println(goodid);
				System.out.println(replyid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UserDaoImpl userDao = DaoFactory.getUserDao();
			GoodDaoImpl goodDao = DaoFactory.getGoodDao();
			LeaveMsgDaoImpl leaveMsgDao = DaoFactory.getLeaveMsgDao();
			User sendU = userDao.load(sendid);
			User receiveU = userDao.load(receiveid);
			Good goodG = goodDao.load(goodid);
			LeaveMsg leaveMsg = new LeaveMsg(sendU, receiveU, goodG, content, time, replyid);
			leaveMsgDao.add(leaveMsg);
			writer.print("{'status':'OK'}");
		//获取在指定商品信息下留言
		}else if("getLeaveMsg".equals(request.getParameter("type"))){
			
			LeaveMsgDaoImpl leaveMsgDao = new LeaveMsgDaoImpl();
			String goodid = request.getParameter("goodid");
			int goodid_value = Integer.parseInt(goodid);
			List<LeaveMsg> leaveMsgs = leaveMsgDao.load(goodid_value, 0);
			JsonArray jsonArray = new JsonArray();
			for(LeaveMsg l_msg:leaveMsgs) {
				jsonArray.add(l_msg.toJSON());
			}
			System.out.println(jsonArray.toString());
			writer.print(jsonArray.toString());
		//加载与指定用户在指定商品下私信消息列表
		}else if ("msgList".equals(request.getParameter("type"))) {
			
			MessageDaoImpl messageDao = DaoFactory.getMessageDao();
			
			String sendid = request.getParameter("sendid");
			String receiveid = request.getParameter("receiveid");
			String goodid = request.getParameter("goodid");
			
			int send_value = 0;
			int receive_value = 0;
			int good_value = 0;
			try {
				send_value = Integer.parseInt(sendid);
				receive_value = Integer.parseInt(receiveid);
				good_value = Integer.parseInt(goodid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			List<Message> messages = messageDao.loadWithCondition(receive_value, send_value, good_value);
			JsonArray jsonArray = new JsonArray();
			for(Message m:messages) {
				jsonArray.add(m.toJson());
			}
			System.out.println(jsonArray.toString());
			writer.print(jsonArray.toString());
		//发送私聊消息
		}else if ("add".equals(request.getParameter("type"))) {
			
			String msg = request.getParameter("message");
			String sendid = request.getParameter("sendid");
			String receiveid = request.getParameter("receiveid");
			String goodid = request.getParameter("goodid");
			
			int send_value = 0;
			int receive_value = 0;
			int good_value = 0;
			try {
				send_value = Integer.parseInt(sendid);
				receive_value = Integer.parseInt(receiveid);
				good_value = Integer.parseInt(goodid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UserDaoImpl userDao = new UserDaoImpl();
			GoodDaoImpl goodDao = new GoodDaoImpl();
			User send = userDao.load(send_value);
			User receive = userDao.load(receive_value);
			Good good = goodDao.load(good_value);
			Message message = new Message(send, receive, good, 0, msg, new Timestamp(System.currentTimeMillis()), "用户消息");
			MessageDaoImpl messageDao = new MessageDaoImpl();
			messageDao.add(message);
			writer.print("{'status':'OK'}");
		//加载每用户每商品第一条消息列表
		}else{
			int status = -1;
			int receiveid = 0;
			try {
				status = Integer.parseInt(request.getParameter("status"));
				receiveid = Integer.parseInt(request.getParameter("receiveid"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			MessageDaoImpl messageDao = DaoFactory.getMessageDao();
			List<Message> messages = messageDao.loadWithStatus(receiveid, receiveid, status);
			JsonArray jsonArray = new JsonArray();
			for(Message m:messages) {
				jsonArray.add(m.toJson());
			}
			System.out.println(jsonArray.toString());
			writer.print(jsonArray.toString());
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
