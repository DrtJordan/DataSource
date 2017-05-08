package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.junit.Test;

//��ȡһ�����ݿ����Ӷ���Connection�Ĺ淶

public class Dbutil {
	private static String driverurl;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		ResourceBundle res=ResourceBundle.getBundle("Dbinfo");//�������ļ��ж�ȡ������Ϣ��key-value��
		driverurl=res.getString("driverurl");
		url=res.getString("url");
		username=res.getString("username");
		password=res.getString("password");
		try {
			Class.forName(driverurl);//ע�����������䣩
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡһ������
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
	
	//�ر�����
	public static void closeAll(Connection c,PreparedStatement p,ResultSet r){
		if(r!=null){
			try {
				r.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				r=null;
			}
		}
		if(p!=null){
			try {
				p.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				p=null;
			}
		}
		if(c!=null){
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				c=null;
			}
		}
	}

	@Test
	public void text1(){  //JUnit����
		Connection c=null;
		try {
			c=Dbutil.getConnection();
			System.out.println("���ݿ�����������");
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ�ܣ�");
		}
		Dbutil.closeAll(c, null, null);
		System.out.println("���ݿ�ر�������");
	}
}
