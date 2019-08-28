package com.zhuanzhuan.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentVerify {
	
	public int verify(String username,String password) {
		
		String resultStr = null;
		try {
			resultStr = load("http://www.pingfangx.com/xx/tieda/login/login/", "stu_no=" + username + "&stu_pwd=" + password + "&submit_login=%E7%99%BB%E5%BD%95");
			System.out.println(resultStr);
			if (resultStr == null || resultStr.equals("")) {
				return 0;
			}
			else if (resultStr.indexOf("密码错了") >= 0) {
				return -1;
			}else if(resultStr.indexOf("尝试次数过多") >= 0){
				return -2;
			}else if (resultStr.indexOf("鸣谢") >= 0) {
				return 1;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -4;
	}
	

	public static void main(String[] args) {
		
		StudentVerify sVerify = new StudentVerify();
		System.out.println(sVerify.verify("20163594", "103960"));
		
		
	}
	
	public String load(String url,String query) throws Exception
    {
        URL restURL = new URL(url);
        
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        
        conn.setRequestMethod("POST");
        
        conn.setDoOutput(true);
        
        conn.setAllowUserInteraction(false);

        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(query);

        ps.close();

        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

        
        String line,resultStr="";

        while(null != (line=bReader.readLine()))
        {
        	resultStr +=line;
        }
        bReader.close();

        return resultStr;

    }
	
}
