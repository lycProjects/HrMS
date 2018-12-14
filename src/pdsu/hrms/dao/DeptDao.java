package pdsu.hrms.dao;

import java.sql.SQLException;

import pdsu.hrms.model.Dept;

/**
* @ClassName: DeptDao
* @Description: 部门操作接口
* @author 李勇超
* @date 2018年12月11日下午5:52:49
*
*/
public interface DeptDao {
	//以二维数组形式返回所有部门数据
	public String[][] getDeptsForTable() throws SQLException;
	//部门的增加
	public void insert(Dept dept) throws SQLException;
	//部门的修改
	public void update(Dept dept) throws SQLException;
	//部门的删除
	public void delete(long deptId) throws SQLException;
	//获取字符串形式的部门信息：（编号-一级部门-二级部门）
	public String[] getDeptsForSelect() throws SQLException;
	//获取新编号
	public long getId() throws SQLException;
	//根据部门id获取（部门1-部门2）形式的字符串
	public String getDeptNameByDeptId(long deptId)throws SQLException;
}
