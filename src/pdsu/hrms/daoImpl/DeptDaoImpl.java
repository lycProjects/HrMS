package pdsu.hrms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import pdsu.hrms.dao.DeptDao;
import pdsu.hrms.db.C3P0Utils;
import pdsu.hrms.model.Dept;

/**
* @ClassName: DeptDaoImpl
* @Description: ������Ϣ����ʵ����
* @author ���³�
* @date 2018��12��11������5:48:04
*
*/
public class DeptDaoImpl implements DeptDao{
	/**
	 * �Զ�ά������ʽ�������в�������
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public String[][] getDeptsForTable() throws SQLException {
		//��ȡ���ݿ�����
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		//�½����ڱ���Ķ���
		String [][]data = null;
		List<Dept> list = new LinkedList<Dept>();
		String sql = "select * from dept";
		try {
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				Dept dept = new Dept();
				dept.setDeptId(rs.getLong("deptId"));
				dept.setdept1Name(rs.getString("dept1Name"));
				dept.setdept2Name(rs.getString("dept2Name"));
				list.add(dept);
				
				data = new String[list.size()][3];
				for(int i=0;i<list.size();i++) {
					Dept dep = list.get(i);
					data[i][0] = String.valueOf(dep.getDeptId());
					data[i][1] = dep.getdept1Name();
					data[i][2] = dep.getdept2Name();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * ����
	 * @param dept
	 * @throws SQLException
	 */
	@Override
	public void insert(Dept dept) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "insert into dept(deptId,dept1Name,dept2Name)"
				+ "value(?,?,?)";
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, dept.getDeptId());
			prst.setString(2,dept.getdept1Name());
			prst.setString(3,dept.getdept2Name());
			prst.executeUpdate();
			System.out.println("���ӳɹ�������");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	 �޸ķ���
	 * @param dept
	 * @throws SQLException 
	 */
	@Override
	public void update(Dept dept) throws SQLException {
		//��ȡ���ݿ�����
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "update dept set dept1Name = ?,dept2Name = ?"
				+ " where deptId = ?";
		try {
			prst = conn.prepareStatement(sql);
			prst.setString(1, dept.getdept1Name());
			prst.setString(2, dept.getdept2Name());
			prst.setLong(3, dept.getDeptId());
			prst.executeUpdate();
			System.out.println("�޸ĳɹ�������");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	���ݲ��ű��ɾ��
	 * @param deptId
	 * @throws SQLException
	 */
	@Override
	public void delete(long deptId) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "delete from Dept where deptId = ?";
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, deptId);
			prst.executeUpdate();
			System.out.println("ɾ���ɹ�������");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	��ȡ�ַ�����ʽ�Ĳ�����Ϣ:�����-һ������-�������ţ�
	 * @return
	 * @throws SQLException
	 */
	@Override
	public String[] getDeptsForSelect() throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String []data = null;
		List<Dept> list = new LinkedList<Dept>();
		String sql = "select * from dept";
		try {
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				Dept dept = new Dept();
				dept.setDeptId(rs.getLong("deptId"));
				dept.setdept1Name(rs.getString("dept1Name"));
				dept.setdept2Name(rs.getString("dept2Name"));
				list.add(dept);
				data = new String[list.size()];
				for(int i=0;i<list.size();i++) {
					Dept dep = list.get(i);
					data[i] = String.valueOf(dep.getDeptId())+"-"+dep.getdept1Name()+"-"+dep.getdept2Name();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * 	��ȡ�±��
	 * @return
	 * @throws SQLException
	 */
	@Override
	public long getId() throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql ="select * From dept where deptId = (select max(deptId) from dept)";
		Dept dept = null;
		try {
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				dept = new Dept();
				dept.setDeptId(rs.getLong("deptId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dept.getDeptId() + 1;
	}
	
	/**
	 * ���ݲ���id��ȡ������1-����2����ʽ���ַ���
	 */
	@Override
	public String getDeptNameByDeptId(long deptId) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select * from dept where deptId=?";
		String str = null;
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1,deptId);
			rs = prst.executeQuery();
			while(rs.next()) {
				str = rs.getString("dept1Name")+"-"+rs.getString("dept2Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
}
