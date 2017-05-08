package com;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

//���ݿ����ӳع淶
//DataSource�ӿ��ṩ������getConnection()���ط���������û���ṩclose()�رշ���
public class MyDataSource implements DataSource{//���ӳض�Ҫ�̳�sun��˾�Ĺ淶����ʵ��DataSource������Դ���ӿ�
	private static LinkedList<Connection> pool=new LinkedList<Connection>();
	//private static LinkedList<Connection> pool=(LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());���߳��������쳣
	static{
		for(int i=0;i<10;i++){//���ӳ��з���10������
			Connection con=null;
			try {
				con=Dbutil.getConnection();
				pool.add(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException {//�޲η���
		if(pool.size()>0){//���ӳ��л���Connection
			Connection con=pool.removeFirst();//����һ��
			MyConnection c=new MyConnection(con, pool);//��Connectionװ�Σ�����һ��MyConnection����
			return c;
			//return con;
		}
		else{//����Connection����
			//����Connection/�ȴ������û����� ��Connection�Ż�
			throw new RuntimeException("������æ��");
		}
	}
	
	public void colse(Connection con){//�رշ���(����д����)
		pool.addLast(con);//����ŵ����ϵ���󣬶�����ֱ�ӹرյ�con.close()
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {//�вη���
		return null;
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
