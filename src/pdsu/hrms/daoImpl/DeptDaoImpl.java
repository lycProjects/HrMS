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
* @Description: 部门信息操作实现类
* @author 李勇超
* @date 2018年12月11日下午5:48:04
*
*/
public class DeptDaoImpl implements DeptDao{
	/**
	 * 以二维数组形式返回所有部门数据
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public String[][] getDeptsForTable() throws SQLException {
		//获取数据库连接
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		//新建用于保存的对象
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
	 * 增加
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
			System.out.println("增加成功！！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	 修改方法
	 * @param dept
	 * @throws SQLException 
	 */
	@Override
	public void update(Dept dept) throws SQLException {
		//获取数据库连接
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
			System.out.println("修改成功！！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	根据部门编号删除
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
			System.out.println("删除成功！！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	获取字符串形式的部门信息:（编号-一级部门-二级部门）
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
	 * 	获取新编号
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
	 * 根据部门id获取（部门1-部门2）形式的字符串
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
