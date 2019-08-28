package com.zhuanzhuan.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.zhuanzhuan.dao.GoodDaoImpl;
import com.zhuanzhuan.dao.UserDaoImpl;
import com.zhuanzhuan.model.Good;
import com.zhuanzhuan.model.User;
import com.zhuanzhuan.util.DaoFactory;

/**
 * Servlet implementation class AddGoodServlet
 */
@WebServlet("/AddGoodServlet")
public class AddGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		
		if (ServletFileUpload.isMultipartContent(request)) {
			
			// �����ϴ�����
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        
	        upload.setHeaderEncoding("UTF-8"); 
	        String uploadPath = request.getServletContext().getRealPath(".") + File.separator + "img" + File.separator + "goods";
	         
	        int goodid = 0;
	        try {
	            
	        	Map<String, List<FileItem>> map = upload.parseParameterMap(request);
	            System.out.println(map.toString());
	            List<FileItem> formItems = map.get("goodimg");
	            List<FileItem> items = map.get("goodid");
	            if (items != null && items.size() > 0) {
		            for (FileItem item : items) {  
		                
	                    String goodIdStr = item.getString("utf-8");  
	                    goodid = Integer.parseInt(goodIdStr);
		            }
	            }
	            if (formItems != null && formItems.size() > 0) {
	            	
	            	String imgStr = "";
	                //
	                for (FileItem item : formItems) {
	                    //
	                    if (!item.isFormField()) {
	                        String fileName = new File(item.getName()).getName();
	                        
	                        System.out.println(fileName);
	                        imgStr = imgStr + fileName + ";";
	                        String filePath = uploadPath + File.separator + fileName;
	                        File storeFile = new File(filePath);

	                        System.out.println(filePath);

	                        item.write(storeFile);
	                        request.setAttribute("message",
	                            "�ļ��ϴ��ɹ�!");
	                    }
	                }
	                
	                //����ͼƬ·����Ϣ
	                GoodDaoImpl goodDao = DaoFactory.getGoodDao();
	                Good good = goodDao.load(goodid);
	                good.setImages(good.getImagesStr() + imgStr);
	                goodDao.update(good);
	            }
	            writer.print("{\"status\":\"OK\"}");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		}
		
		else{
			
			String description = request.getParameter("description");
//			String forepriceStr = request.getParameter("foreprice");
			String goodName = request.getParameter("goodName");
//			String method = request.getParameter("method");
			String priceStr = request.getParameter("price");
			String ownerIdStr = request.getParameter("ownerId");
			String numStr = request.getParameter("num");
			String catagory = request.getParameter("catagory");
			
			UserDaoImpl userDao = DaoFactory.getUserDao();
			User goodowner = null;
			
			
			try {
//				double forprice = Double.parseDouble(forepriceStr);
				double price = Double.parseDouble(priceStr);
				int num = Integer.parseInt(numStr);
				int ownerId = Integer.parseInt(ownerIdStr);
				goodowner = userDao.load(ownerId);
				
				GoodDaoImpl goodDao = DaoFactory.getGoodDao();
				Good good = new Good(goodName,goodowner,price,description,"",num,catagory,new Timestamp(System.currentTimeMillis()));
				int goodid = goodDao.add(good);
				
				writer.write("{\"status\":\"OK\",\"goodid\":" + goodid + "}");
			} catch (Exception e) {
				writer.write("{status:'ERROR'}");
				e.printStackTrace();
			}
			
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
