package pdsu.hrms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author gosling�����ݿ����ӣ�access��ʵ����
 *
 */
public class DBUtils {

	/**
	 * ��ȡ����
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			conn = DriverManager.getConnection("jdbc:ucanaccess://Hr.accdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �رս����
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر�Ԥ����
	 * @param prst
	 */
	public static void close(PreparedStatement prst) {
		if (prst != null) {
			try {
				prst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر�����
	 * @param conn
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��Ը��²���,�ر���Դ
	 * @param prst
	 * @param conn
	 */
	public static void close(PreparedStatement prst, Connection conn) {
		close(prst);
		close(conn);
	}

	/**
	 * ��Բ�ѯ�����ر���Դ
	 * @param prst
	 * @param conn
	 * @param rs
	 */
	public static void close(PreparedStatement prst, Connection conn, ResultSet rs) {
		close(rs);
		close(prst);
		close(conn);
	}
}
