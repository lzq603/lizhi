package com.zhuanzhuan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.zhuanzhuan.dao.GoodDaoImpl;
import com.zhuanzhuan.model.Good;
import com.zhuanzhuan.util.DaoFactory;

/**
 * ������ʾ��Ʒ��������
 * ����request���ԣ�
 * 	id����Ʒid
 * ����session���ԣ�
 * 	user����ǰ��¼�û�
 */
@WebServlet("/GoodServlet")
public class GoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String type = request.getParameter("loadBy");
		GoodDaoImpl goodDao = new GoodDaoImpl();	// ��Ʒʵ����
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		
		if ("id".equals(type)) {
			
			String action = request.getParameter("action");
			if ("delete".equals(action)) {
				
				int id=Integer.parseInt(request.getParameter("id"));
				//根据id查找到商品信息，将数量修改为-1
				GoodDaoImpl gooddao=DaoFactory.getGoodDao();
				Good good=gooddao.load(id);
				good.setNum(-1);
			 	try{
			 		gooddao.update(good);
			 		writer.print("{\"status\":\"OK\",\"goodId\":\"" + id + "\"}");
			 	}catch(Exception e){
			 		writer.print("{\"status\":\"ERROR\",\"goodId\":\"" + id + "\"}");
			 	}
				
			}else{
			
				int id = 0;		//��ƷID
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (Exception e) {	// ������ֵ�����쳣
					writer.print("{\"err\":\"系统内部错误\"}");
					return;
				}
				Good good = goodDao.load(id);				// ������Ʒ
				if(good == null) {		// ��������ڸ���Ʒ�����������Ϣ
					writer.print("{\"err\":\"系统内部错误\"}");
				}else {
					writer.print(good.toJson().toString());
				}
			}
		}else if("condition".equals(type)){

			//���մ��ݹ����Ĺؼ���
			double small_price = 0;				//Ĭ����С�۸�Ϊ0
			double big_price = 2147483647;		//���Ϊ2147483647;
			String key = null;	//��ȡ�ؼ���
			String catagory = null;				//����
			int start = 0;
			int end = 1000;
			try{
				key = request.getParameter("key").trim();
			}catch(Exception e){
				key = "";
			}
			
			try{		//������ܲ����ڣ���׽���쳣
				catagory = request.getParameter("catagory").trim();
			}catch(Exception e){
				catagory = "";
			}
			try{		//��С�۸���ܲ����ڣ���׽�쳣
				small_price = Double.parseDouble(request.getParameter("small_price"));
			}catch(Exception e){
				System.out.println("��С�۸��������");
			}
			try{		//���۸���ܲ����ڣ���׽�쳣
				big_price = Double.parseDouble(request.getParameter("big_price"));
			}catch(Exception e){
				System.out.println("���۸��������");
			}
			try{		//���۸���ܲ����ڣ���׽�쳣
				start = Integer.parseInt(request.getParameter("start"));
			}catch(Exception e){
				System.out.println("���۸��������");
			}try{		//���۸���ܲ����ڣ���׽�쳣
				end = Integer.parseInt(request.getParameter("end"));
			}catch(Exception e){
				System.out.println("���۸��������");
			}

			System.out.println("key:" + key);
			System.out.println("catagory:" + catagory);
			System.out.println("small:" + small_price);
			System.out.println("big:" + big_price);
			//返回商品JSON数据
			List<Good> goods=goodDao.loadWithCondition(key, catagory, small_price, big_price,start,end);		
			System.out.println(goods.size());
			JsonArray jsonArray = new JsonArray();
			for(Good good:goods) {
				jsonArray.add(good.toJson());
			}
			System.out.println(jsonArray.toString());
			writer.println(jsonArray.toString());
		}else if ("userId".equals(type)) {
			
			String userId_str = request.getParameter("userId");
			int userId = Integer.parseInt(userId_str);
			List<Good> goods = goodDao.loadByUser(userId);
			
			JsonArray jsonArray = new JsonArray();
			for(Good good:goods) {
				jsonArray.add(good.toJson());
			}
			System.out.println(jsonArray.toString());
			writer.println(jsonArray.toString());
		}else if ("CollectUser".equals(type)) {
			String userId_str = request.getParameter("userId");
			int userId = Integer.parseInt(userId_str);
			List<Good> goods = goodDao.loadByCollectUser(userId);
			
			JsonArray jsonArray = new JsonArray();
			for(Good good:goods) {
				jsonArray.add(good.toJson());
			}
			System.out.println(jsonArray.toString());
			writer.println(jsonArray.toString());
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
