package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlTools {

	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/classify";
	private final static String userName = "root";
	private final static String password = "qeephp";
	
	private static Connection conn = null;

	private MysqlTools() {
		
	}
	
	public Connection getInstance() {
		if (null == conn) {
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url, userName, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}


	private static void close(Statement stmt) {
		if (null != stmt) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = new MysqlTools().getInstance();
		String sql = "insert into URL_DICT (URL_TYPE_ID, WORDS) values (?,?)";
		
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "20");
			stmt.setString(2, "篮球比赛");
			stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
}
