package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.test.Utils.ConfigUtils;

public class JDBCTools {

	private static String CONFIG_PATH = "/config/jdbc.properties";
	private static Properties prop = ConfigUtils.getConfig(CONFIG_PATH);

	private static String VAR = "#1.#2";
	private static String PRE;
	private static String DIRVER;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;

	private JDBCTools() {
	}

	public static void main(String[] args) {
		Connection conn = getInstance("sybase");
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from Persons ");

			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBC(rs, stmt, conn);
		}
	}
	
	
	public static Connection getInstance(String jdbcType) {
		PRE = jdbcType;
		DIRVER = getParam("driver");
		URL = getParam("url");
		USERNAME = getParam("username");
		PASSWORD = getParam("password");
		
		Connection conn = null;

		try {
			Class.forName(DIRVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 获取配置文件中的JDBC连接
	 * 
	 * @param pre
	 * @param str
	 * @return
	 */
	private static String getParam(String str) {
		String varString = VAR.replace("#1", PRE).replace("#2", str);
		String s = prop.getProperty(varString, "");
		varString = null;
		return s;
	}

	public static void closeJDBC(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		closeJDBC(stmt, conn);
	}

	public static void closeJDBC(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
