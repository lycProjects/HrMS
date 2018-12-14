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
* @Description: �䶯��ʷ��Ϣʵ����
* @author ���³�
* @date 2018��12��9������2:50:51
*
*/
public class HistoryDaoImpl implements HistoryDao{
	/**
	 *  ��ȡĳ�ֱ䶯���͵��������ݣ����Զ�ά�������ʽ���� ��ˮ�� ��Ա���� �ϴο��� ���ο��� ������� �������
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
	 * ��Ա�䶯���
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
	 * �䶯��ʷ���
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
	 * ����±��
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
	 *  ��ȡĳԱ����ĳ�ֱ䶯�����ϵı䶯���������ѯ�����ڲ��ŵ�������䶯�Ĵ�����
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
	 *  �ж�ĳԱ���Ƿ��б䶯��ʷ�����б䶯��ʷ������ɾ����Ա����ͬʱ������ʾ��Ϣ��
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
