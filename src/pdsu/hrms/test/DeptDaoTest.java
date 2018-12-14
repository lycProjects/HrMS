package pdsu.hrms.test;


import java.sql.SQLException;

import org.junit.Test;

import pdsu.hrms.dao.DeptDao;
import pdsu.hrms.daoImpl.DeptDaoImpl;
import pdsu.hrms.model.Dept;

/**
* @ClassName: DeptDaoTest
* @Description: 部门测试
* @author 李勇超
* @date 2018年12月13日下午6:53:25
*
*/
public class DeptDaoTest {
	
	private DeptDao deptDao=new DeptDaoImpl();

	@Test
	public void getDeptsForTableTest() throws SQLException {
		
		String[][] deptsForTable = deptDao.getDeptsForTable();
		for(int i=0;i<deptsForTable.length;i++)
		{
				System.out.println(deptsForTable[i][0]+" "+deptsForTable[i][1]+" "+deptsForTable[i][2]);
		}	
		
	}
	
	//增加
	@Test
	public void insertTest() throws SQLException {
		
		Dept dept=new Dept();
		dept.setDeptId(12);
		dept.setdept1Name("学生处");
		dept.setdept2Name("办公室");
		deptDao.insert(dept);
	}
	
	//修改
	@Test
	public void updateTest() throws SQLException {
		
		Dept dept=new Dept();
		dept.setDeptId(12);
		dept.setdept2Name("办公室1");
		deptDao.update(dept);
	}
	
	//删除
	@Test
	public void deleteTest() throws SQLException {
		
		deptDao.delete(12);
	}
	
	
	@Test
	public void getDeptsSelectTest() throws SQLException {
		
		//获取字符串形式的部门信息:（编号-一级部门-二级部门）
		String[] deptsForSelect = deptDao.getDeptsForSelect();
		for (String string : deptsForSelect) {
			System.out.println(string);
		}
		
		//根据部门id获取（部门1-部门2）形式的字符串
		String deptNameByDeptId = deptDao.getDeptNameByDeptId(1);
		System.out.println(deptNameByDeptId);
	}
	
	//获取新编号
	@Test
	public void getIdTest() throws SQLException {
		long id = deptDao.getId();
		System.out.println(id);
	}

}
