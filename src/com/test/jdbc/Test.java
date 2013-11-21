package com.test.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Test {
	
	public static void main(String[] args) {
		test2();
	}

	public static void test2() {
		Connection conn = JDBCTools.getInstance("sybase");
		Statement stmt = null;
		ResultSet rs = null;
		
//		String sql = "SELECT a.scan_start_time, a.m_int_id, a.count_ ca, b.count_ cb  FROM (    SELECT scan_start_time, m_int_id, COUNT(*) count_      FROM tma_pol_utr_test3   where 1=1  AND  scan_start_time between  '2013-06-18 00:21:53'   AND  '2013-06-18 15:22:01' AND  startdate  between  '2013-06-18_00'   AND  '2013-06-18_15'   AND m_int_id in ( 1943601406   )    GROUP BY scan_start_time, m_int_id     ) a Join (     SELECT scan_start_time, m_int_id, COUNT(*) count_         FROM tma_pol_utr_test3             WHERE 1=1    AND m_int_id in ( 1943601406   )    and TDSCPCCPCHRSCP >=30    and pccpchrscp4 >=30    AND pccpchrscp1-pccpchrscp4 <=6 AND  scan_start_time between  '2013-06-18 00:21:53'   AND  '2013-06-18 15:22:01' AND  startdate  between  '2013-06-18_00'   AND  '2013-06-18_15'   GROUP BY scan_start_time, m_int_id          ) b               on( a.scan_start_time = b.scan_start_time    AND a.m_int_id = b.m_int_id )            ";
		String sql = "SELECT count(*) from config_room_resource";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.closeJDBC(rs, stmt, conn);
		}

	}
	
	public static void test3() {
		Connection conn = JDBCTools.getInstance("impala");
		String sqlModel = "insert into hb values ('3')";
		String sql = sqlModel.replaceAll("#1", "wq2");
		System.out.println(sql);
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
			int count = stmt.getUpdateCount();
			System.out.println("update count:" + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.closeJDBC(stmt, conn);
		}
	}

	public static void test() {
		Connection conn = JDBCTools.getInstance("impala");
		String sqlModel = "insert into table #1 select * from #1";
		String sql = sqlModel.replaceAll("#1", "wq2");
		System.out.println(sql);
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
			int count = stmt.getUpdateCount();
			System.out.println("update count:" + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.closeJDBC(stmt, conn);
		}
	}

}
