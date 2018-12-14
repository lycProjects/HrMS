package pdsu.hrms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author gosling：数据库连接（access）实现类
 *
 */
public class DBUtils {

	/**
	 * 获取连接
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
	 * 关闭结果集
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
	 * 关闭预编译
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
	 * 关闭连接
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
	 * 针对更新操作,关闭资源
	 * @param prst
	 * @param conn
	 */
	public static void close(PreparedStatement prst, Connection conn) {
		close(prst);
		close(conn);
	}

	/**
	 * 针对查询操作关闭资源
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
