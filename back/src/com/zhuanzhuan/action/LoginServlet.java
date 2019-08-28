package com.zhuanzhuan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zhuanzhuan.dao.UserDaoImpl;
import com.zhuanzhuan.model.User;
import com.zhuanzhuan.util.DaoFactory;
import com.zhuanzhuan.util.WX;


/**  
 * @Description: ��¼�Ի�ȡ�û���С�����ڵ�ID  
 * @author ��־ǿ  
 * @date 2018��4��14��    
 */  
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String openid = null;
		
		/** ��ȡappid **/
		PrintWriter writer = response.getWriter();
		String code = request.getParameter("code");

		String pattern = "\"openid\":\"(.*)\"";
		Pattern reg = Pattern.compile(pattern);
		
		System.out.println("code" + code);
		if (!(code == null || "".equals(code))) {
			
			String data = WX.getAppId(code);
			Matcher matcher = reg.matcher(data);
			if (matcher.find()) {
				openid = matcher.group(1);
			}
		}
		
		System.out.println("apid:" + openid);
		
		/****ͨ��appid����user***/
		if (openid != null) {
			
			UserDaoImpl userDao = DaoFactory.getUserDao();
			User user = userDao.loadByAppId(openid);
			//����û�δע��ת׬
			if (user == null) {
				String nickName = request.getParameter("nickname");
				String head = request.getParameter("head");
				String gender_s = request.getParameter("gender");
				Boolean gender = ("1".equals(gender_s) ? true : false);
				
				user = new User();
				user.setOpenid(openid);
				user.setNickname(nickName);
				user.setHead(head.trim());
				user.setSex(gender);
				user.setCollege("石家庄铁道大学");
				
				System.out.println(nickName);
				userDao.add(user);
				user = userDao.loadByAppId(openid);
			}
			System.out.println("用户：" + user.toJson().toString());
//			response.setContentType("application/json");
			
			writer.write(user.toJson().toString());
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
