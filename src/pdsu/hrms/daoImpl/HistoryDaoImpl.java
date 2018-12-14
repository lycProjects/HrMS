package pdsu.hrms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import pdsu.hrms.dao.DeptDao;
import pdsu.hrms.dao.HistoryDao;
import pdsu.hrms.db.C3P0Utils;
import pdsu.hrms.model.History;


/**
* @ClassName: HistoryDaoImpl
* @Description: 变动历史信息实现类
* @author 李勇超
* @date 2018年12月9日下午2:50:51
*
*/
public class HistoryDaoImpl implements HistoryDao{
	/**
	 *  获取某种变动类型的所有数据，并以二维数组的形式返回 流水号 人员姓名 上次考核 本次考核 变更次数 变更日期
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	@Override
	public String[][] getAllByType(String type) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String[][] data = null;
		List<History> list = new LinkedList<History>();
		String sql = "select * from history,person where person.personId=history.personId " + "and histType=?";
		History history = null;
		try {
			prst = conn.prepareStatement(sql);
			prst.setString(1, type);
			rs = prst.executeQuery();
			while (rs.next()) {
				history = new History();
				history.setHistId(rs.getLong("histId"));
				history.setHistType(rs.getString("histType"));
				history.setOldInfo(rs.getString("oldInfo"));
				history.setNewInfo(rs.getString("newInfo"));
				history.setChgDate(rs.getString("chgDate"));
				history.setChgNum(rs.getLong("chgNum"));
				history.setName(rs.getString("pName"));
				list.add(history);

				data = new String[list.size()][6];
				for (int i = 0; i < list.size(); i++) {
					History per = list.get(i);
					data[i][0] = String.valueOf(per.getHistId());
					data[i][1] = per.getName();
					data[i][2] = per.getOldInfo();
					data[i][3] = per.getNewInfo();
					data[i][4] = String.valueOf(per.getChgNum());
					data[i][5] = per.getChgDate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return data;
	}
	
	
	/**
	 * 人员变动添加
	 * @param type
	 * @throws SQLException
	 */
	@Override
	public String[][] getAllByTypes(String type) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		DeptDao deptDao=new DeptDaoImpl();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String[][] data = null;
		List<History> list = new LinkedList<History>();
		String sql = "select * from history,person where person.personId=history.personId " + "and histType=?";
		History history = null;
		try {
			prst = conn.prepareStatement(sql);
			prst.setString(1, type);
			rs = prst.executeQuery();
			while (rs.next()) {
				history = new History();
				history.setHistId(rs.getLong("histId"));
				history.setHistType(rs.getString("histType"));
				history.setOldInfo(rs.getString("oldInfo"));
				history.setNewInfo(rs.getString("newInfo"));
				history.setChgDate(rs.getString("chgDate"));
				history.setChgNum(rs.getLong("chgNum"));
				history.setName(rs.getString("pName"));
				list.add(history);

				data = new String[list.size()][6];
				for (int i = 0; i < list.size(); i++) {
					History per = list.get(i);
					data[i][0] = String.valueOf(per.getHistId());
					data[i][1] = per.getName();
					data[i][2] = deptDao.getDeptNameByDeptId(Integer.parseInt(per.getOldInfo()));
					data[i][3] = deptDao.getDeptNameByDeptId(Integer.parseInt(per.getNewInfo()));
					data[i][4] = String.valueOf(per.getChgNum());
					data[i][5] = per.getChgDate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return data;
	}

	

	/**
	 * 变动历史添加
	 * @param h
	 * @throws SQLException
	 */
	@Override
	public void add(History h) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "insert into history(histId,histType,oldInfo,newInfo,chgDate,chgNum,personId) "
				+ "value(?,?,?,?,?,?,?)";
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, h.getHistId());
			prst.setString(2, h.getHistType());
			prst.setString(3, h.getOldInfo());
			prst.setString(4, h.getNewInfo());
			prst.setString(5, h.getChgDate());
			prst.setLong(6, h.getChgNum());
			prst.setLong(7, h.getPersonId());
			prst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得新编号
	 * @return
	 * @throws SQLException
	 */
	@Override
	public long getNextId() throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select * from history where histId = (select max(histId) from history)";
		History history = null;
		try {
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while (rs.next()) {
				history = new History();
				history.setHistId(rs.getLong("histId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return history.getHistId() + 1;
	}

	/**
	 *  获取某员工在某种变动类型上的变动次数（如查询张三在部门调动方面变动的次数）
	 * @param personId
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int getChgCount(long personId, String type) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select count(*) from history where personId=? and histType=?";
		History history = null;
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, personId);
			prst.setString(2, type);
			rs = prst.executeQuery();
			while (rs.next()) {
				history = new History();
				history.setChgNum(Long.valueOf(rs.getString("count(*)")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return (int) (history.getChgNum()+1);
	}

	/**
	 *  判断某员工是否有变动历史（若有变动历史，则不能删除该员工，同时给出提示信息）
	 * @param personId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean hasData(long personId) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		Boolean bool = false;
		String sql = "select * from history where personId = ?";
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, personId);
			rs = prst.executeQuery();
			while (rs.next()) {
				bool = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
}
