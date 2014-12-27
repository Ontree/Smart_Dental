package com.edu.thss.smartdental.RemoteDB;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsDBUtil {
	private ArrayList<String> parametername = new ArrayList<String>();
	private ArrayList<String> parametervalue = new ArrayList<String>();
	private ArrayList<String> resultinfo = new ArrayList<String>();
	private HttpConnSoap Soap = new HttpConnSoap();
	/**
	 * 获得用户全部新消息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectAllUnreadNewsByUsername(String username) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		parametername.add("username");
		parametervalue.add(username);
		try{			
			resultinfo = Soap.GetWebService("selectAllUnreadNewsByUsername", parametername, parametervalue);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("newsId", "newsId");
		tempHash.put("newstype", "newstype");
		tempHash.put("username", "username");
		tempHash.put("content", "content");
		tempHash.put("time", "time");
		tempHash.put("postname", "postname");
		tempHash.put("postId", "postId");
		list.add(tempHash);
		
		for (int j = 0; j < resultinfo.size(); j += 7) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("newsId", resultinfo.get(j));
			hashMap.put("newstype", resultinfo.get(j + 1));
			hashMap.put("username", resultinfo.get(j + 2));
			hashMap.put("content", resultinfo.get(j + 3));
			hashMap.put("time", resultinfo.get(j + 4));
			hashMap.put("postname", resultinfo.get(j + 5));
			hashMap.put("postId", resultinfo.get(j + 6));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * 获取用户全部已读消息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectAllReadNewsByUsername(String username) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		parametername.add("username");
		parametervalue.add(username);
		try{
			
			resultinfo = Soap.GetWebService("selectAllReadNewsByUsername", parametername, parametervalue);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("newsId", "newsId");
		tempHash.put("newstype", "newstype");
		tempHash.put("username", "username");
		tempHash.put("content", "content");
		tempHash.put("time", "time");
		tempHash.put("postname", "postname");
		tempHash.put("postId", "postId");
		list.add(tempHash);
		
		for (int j = 0; j < resultinfo.size(); j += 7) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("newsId", resultinfo.get(j));
			hashMap.put("newstype", resultinfo.get(j + 1));
			hashMap.put("username", resultinfo.get(j + 2));
			hashMap.put("content", resultinfo.get(j + 3));
			hashMap.put("time", resultinfo.get(j + 4));
			hashMap.put("postname", resultinfo.get(j + 5));
			hashMap.put("postId", resultinfo.get(j + 6));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * 新建一条消息
	 * 
	 * @return
	 */
	public String insertNews(String newstype, String username, String replytouser,String newscontent,int postId) {

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		
		parametername.add("username");
		parametername.add("replytouser");
		parametername.add("newscontent");
		parametername.add("postId");
		parametervalue.add(username);
		parametervalue.add(replytouser);
		parametervalue.add(newscontent);
		parametername.add(Integer.toString(postId));
		try{
			resultinfo = Soap.GetWebService("insertNews", parametername, parametervalue);
		}
		catch(Exception e) {
		}	
		if(resultinfo.size() == 0){
			return "fail to connect to Database";
		}
		return resultinfo.get(0);
	}
	
	/**
	 * 更新消息为已读
	 * 
	 * @return
	 */
	public String haveread(String newsid) {

		parametername.clear();
		parametervalue.clear();
		resultinfo.clear();
		
		parametername.add("newsid");
		parametervalue.add(newsid);
		try{
			resultinfo = Soap.GetWebService("haveread", parametername, parametervalue);
		}
		catch(Exception e) {
		}
		if(resultinfo.size() == 0){
			return "fail to connect to Database";
		}
		return resultinfo.get(0);
	}
}
