package pdsu.hrms.dao;

import java.sql.SQLException;

import pdsu.hrms.model.Person;

/**
* @ClassName: PersonDao
* @Description: 人员操作接口
* @author 李勇超
* @date 2018年12月11日下午1:53:58
*
*/
public interface PersonDao {
	//以二维数组的形式返回所有部门数据
	public String[][] getAllForBasic() throws SQLException;
	//根据  对象id 值获取该id对应的对象
	public Person getPersonById(long personId) throws SQLException;
	//员工信息的添加,修改,删除
	public void add(Person p) throws SQLException;
	//员工信息的修改
	public void update(Person p) throws SQLException;
	//员工信息的删除
	public void delete(long personId) throws SQLException;
	//获取一个可用的编号
	public long getNextId() throws SQLException;
	//以“编号-姓名”的形式查询所有员工的信息
	public String[] getNamesWithId() throws SQLException;
	//根据员工编号查询当前的部门编号
	public long getDeptId(long personId) throws SQLException;
	//根据员工姓名获取员工编号
	public String getPersonIdByName(String name) throws SQLException;
	//更新部门
	public void updateDept(long personId,long deptId) throws SQLException;
	//更新考核结果
	public void updateAssess(long personId,String assess) throws SQLException;
	//更新薪资
	public void updateSalary(long personId,String salary) throws SQLException;
	//查询所有员工的部门，考核和劳资信息
	public String[][] getAllForHistory() throws SQLException;
}