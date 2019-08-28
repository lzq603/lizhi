package com.zhuanzhuan.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WX {
	
	private static final String APPID = "wx22299f8b14857643";
	private static final String APPSECRET = "83a8004df38aa69f6d279fd009931989";
	
	public String load(String url,String query) throws Exception
    {
        URL restURL = new URL(url);
        /*
         * �˴���urlConnection����ʵ�����Ǹ���URL������Э��(�˴���http)���ɵ�URLConnection�� ������HttpURLConnection
         */
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        //����ʽ
        conn.setRequestMethod("GET");
        //�����Ƿ��httpUrlConnection���룬Ĭ���������true; httpUrlConnection.setDoInput(true);
        conn.setDoOutput(true);
        //allowUserInteraction ���Ϊ true�����������û����������絯��һ����֤�Ի��򣩵��������жԴ� URL ���м�顣
        conn.setAllowUserInteraction(false);

        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(query);

        ps.close();

        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line,resultStr="";

        while(null != (line=bReader.readLine()))
        {
        	resultStr +=line;
        }
        //System.out.println("3412412---"+resultStr);
        bReader.close();

        return resultStr;

    }
    
	public static String getAppId(String code) {
		try {

            WX restUtil = new WX();

            String resultString = restUtil.load(
                    "https://api.weixin.qq.com/sns/jscode2session",
                    "appid=" + APPID + "&secret=" + APPSECRET + "&js_code=" + code + "&grant_type=authorization_code");
            System.out.println("appid" + resultString);
            return resultString;
            
        } catch (Exception e) {

        	System.out.println("WX.java异常");
	        e.printStackTrace();
	
	        System.out.print("错误信息:" + e.getMessage());

        }
		
		return "error";
	}
}
