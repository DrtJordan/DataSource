package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;;

//������

public class Test {
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement p=null;
		ResultSet r=null;
		DataSource ds=new MyDataSource();
		try {
			con=ds.getConnection();
			p=con.prepareStatement("select *from Student;");
			r=p.executeQuery();
			while(r.next()){
				System.out.print("id:"+r.getInt(1));
				System.out.println("   name:"+r.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				//ʹ��װ����MyConnection�Ķ���,��Ϊ�Ǹ�װ�������ṩ��close()����
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//����2
	@org.junit.Test
	public void text1(){
		Connection con=null;
		PreparedStatement p=null;
		ResultSet r=null;
		//MyDataSource mds=new MyDataSource(); ���淶 ��Ϊ���˲���ʶMyDataSource ֻ��ʶsun��˾�ṩ�Ĺ淶DataSource
		DataSource ds=new MyDataSource();
		try {
			con=ds.getConnection();
			p=con.prepareStatement("select *from Student;");
			r=p.executeQuery();
			while(r.next()){
				System.out.print("id:"+r.getInt(1));
				System.out.println("   name:"+r.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//ds.close(); �رղ��� ��Ϊ����DataSource�в�����close()����
			((MyDataSource)ds).colse(con);//ǿת �������Ҳ���淶 ���˲���ʶMyDataSource
			//����Ҫ��װ�����ģʽ����mysql�ṩ��jar����� Connection����װ��
		}
	}
}
