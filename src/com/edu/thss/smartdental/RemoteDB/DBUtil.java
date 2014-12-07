package com.edu.thss.smartdental.RemoteDB;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	private ArrayList<String> arrayList = new ArrayList<String>();
	private ArrayList<String> brrayList = new ArrayList<String>();
	private ArrayList<String> crrayList = new ArrayList<String>();
	private HttpConnSoap Soap = new HttpConnSoap();

	public static Connection getConnection() {
		Connection con = null;		
		return con;
	}

	/**
	 * ��ȡ�����û�����Ϣ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllpatients(String DoctorName) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("doctorname");
		brrayList.add(DoctorName);
		try{
			crrayList = Soap.GetWebService("selectAllUserByDoctor", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("userid", "userid");
		tempHash.put("username", "username");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 2) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("userid", crrayList.get(j));
			hashMap.put("username", crrayList.get(j + 1));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * ��ȡ����ҽ������Ϣ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllDoctors() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
			
		try{
			crrayList = Soap.GetWebService("selectAllDoctors", arrayList, brrayList);
		}
		catch(Exception e) {
		}
			

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("doctorid", "doctorid");
		tempHash.put("doctorname", "doctorname");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 2) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("doctorid", crrayList.get(j));
			hashMap.put("doctorname", crrayList.get(j + 1));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * ��ȡ��ǰ�û���Ȧ����Ϣ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> selectDoctorsByname(String userName) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("userName");
		brrayList.add(userName);
			
		try{
			crrayList = Soap.GetWebService("selectDoctorsByname", arrayList, brrayList);
		}
		catch(Exception e) {
		}
			

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("doctorid", "doctorid");
		tempHash.put("doctorname", "doctorname");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 2) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("doctorid", crrayList.get(j));
			hashMap.put("doctorname", crrayList.get(j + 1));
			list.add(hashMap);
		}

		return list;
	}
	
	/**
	 * ����һ���û�
	 * 
	 * @return
	 */
	public String insertUser(String username, String password,String identity) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		arrayList.add("password");
		arrayList.add("ident");
		brrayList.add(username);
		brrayList.add(password);
		brrayList.add(identity);
		try{
			crrayList = Soap.GetWebService("insertUser", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		return crrayList.get(0);
	}
	
	/**
	 * ��½
	 * if log in success return true
	 * else if user not exist return user does not exist
	 * else if wrong password return wrong password
	 * else return inner error
	 * @return
	 */
	public String login(String username, String password)
	{
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		arrayList.add("password");
		brrayList.add(username);
		brrayList.add(password);
		try{
			crrayList = Soap.GetWebService("login", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		return crrayList.get(0);
	}
	/**
	 * ɾ��һ���û�
	 * 
	 * @return
	 */
	public String deleteUser(String username) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("username");
		brrayList.add(username);
		try{
			crrayList = Soap.GetWebService("deleteUser", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		return crrayList.get(0);
	}
	/**
	 * ����Ȧ��
	 * 
	 * @return
	 */
	public String joinCircle(String username,String circlepassword,String doctorname){
		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("username");
		arrayList.add("doctorpassword");
		arrayList.add("doctorname");
		brrayList.add(username);
		brrayList.add(circlepassword);
		brrayList.add(doctorname);
		try{
			crrayList = Soap.GetWebService("joinDoctor", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		return crrayList.get(0);
	}
	
	/**
	 * ��ȡ�������ӵ���Ϣ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllPostInfo(int id) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("id");
		brrayList.add(Integer.toString(id));
		try{
			
			crrayList = Soap.GetWebService("selectAllPostByDoctor", arrayList, brrayList);
		}
		catch(Exception e) {
		}

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("postname", "postname");
		tempHash.put("postcontent", "postcontent");
		tempHash.put("time", "time");
		tempHash.put("author", "author");
		tempHash.put("PostId", "PostId");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 5) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("postname", crrayList.get(j));
			hashMap.put("postcontent", crrayList.get(j + 1));
			hashMap.put("time", crrayList.get(j + 2));
			hashMap.put("author", crrayList.get(j + 3));
			hashMap.put("PostId", crrayList.get(j + 4));
			list.add(hashMap);
		}

		return list;
	}

	/**
	 * �½�����
	 * 
	 * @return
	 */
	public String insertPost(String PostName, String postContent,String username, String doctorname) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("PostName");
		arrayList.add("postContent");
		arrayList.add("username");
		arrayList.add("doctorname");
		brrayList.add(PostName);
		brrayList.add(postContent);
		brrayList.add(username);
		brrayList.add(doctorname);		
		try{
			crrayList = Soap.GetWebService("insertPost", arrayList, brrayList);
		}
		catch(Exception e) {
		}			
		return crrayList.get(0);
	}
	
	/**
	 * ɾ������
	 * 
	 * @return
	 */
	public String deletePost(String postname) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("postname");
		brrayList.add(postname);
		try{
			crrayList = Soap.GetWebService("deletePost", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		return crrayList.get(0);
	}
	
	/**
	 * ��ȡ����������
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllComments(int PostId) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		arrayList.add("PostId");
		brrayList.add(Integer.toString(PostId));
			
		try{
			crrayList = Soap.GetWebService("selectAllCommentsByPostId", arrayList, brrayList);
		}
		catch(Exception e) {
		}
			

		HashMap<String, String> tempHash = new HashMap<String, String>();
		tempHash.put("commentusername", "commentusername");
		tempHash.put("commenttype", "commenttype");
		tempHash.put("commenttitle", "commenttitle");
		tempHash.put("commentcontent", "commentcontent");
		tempHash.put("replytouser", "replytouser");
		tempHash.put("time","time");
		list.add(tempHash);
		
		for (int j = 0; j < crrayList.size(); j += 6) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("commentusername", crrayList.get(j));
			hashMap.put("commenttype", crrayList.get(j + 1));
			hashMap.put("commenttitle", crrayList.get(j + 2));
			hashMap.put("commentcontent", crrayList.get(j + 3));
			hashMap.put("replytouser", crrayList.get(j + 4));
			hashMap.put("time", crrayList.get(j + 4));
			list.add(hashMap);
		}

		return list;
	}
	/**
	 * ��������
	 * 
	 * @return
	 */
	public String insertComment(String CommentName, String commentContent, String username, String CommentType, String ReplyUserName) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("CommentName");
		arrayList.add("commentContent");
		arrayList.add("username");
		arrayList.add("CommentType");
		arrayList.add("ReplyUserName");
		brrayList.add(CommentName);
		brrayList.add(commentContent);
		brrayList.add(username);
		brrayList.add(CommentType);	
		brrayList.add(ReplyUserName);
		try{
			crrayList = Soap.GetWebService("insertComment", arrayList, brrayList);
		}
		catch(Exception e) {
		}			
		return crrayList.get(0);
	}
	/**
	 * ɾ������
	 * 
	 * @return
	 */
	public String deleteComment(String commentId) {

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		
		arrayList.add("id");
		brrayList.add(commentId);
		try{
			crrayList = Soap.GetWebService("deleteComment", arrayList, brrayList);
		}
		catch(Exception e) {
		}
		return crrayList.get(0);
	}
}
