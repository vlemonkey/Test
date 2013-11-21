package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleTools {

	private final static String oracleDriver = "oracle.jdbc.driver.OracleDriver";
	private final static String oracleUrl = "jdbc:oracle:thin:@10.0.7.14:1521:obidb";
	private final static String oracleUserName = "hbdwdb";
	private final static String oraclePassword = "hbdwdb";
	
	Connection c = null;
	Statement conn;
	ResultSet rs = null;

	public OracleTools() {
		try {
			Class.forName(oracleDriver).newInstance();
			c = DriverManager.getConnection(oracleUrl, oracleUserName, oraclePassword);
			conn = c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean executeUpdate(String sql) {
		try {
			conn.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet executeQuery(String sql) {
		rs = null;
		try {
			rs = conn.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void close() {
		try {
			conn.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ResultSet rs;
		OracleTools conn = new OracleTools();
		rs = conn.executeQuery("select * from cm_d_gn_cell where rownum<5");
		try {
			while (rs.next()) {
				System.out.println(rs.getString("username") + "--"
						+ rs.getString("user_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
