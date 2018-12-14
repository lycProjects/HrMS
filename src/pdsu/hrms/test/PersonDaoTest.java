package pdsu.hrms.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.daoImpl.PersonDaoImpl;

/**
* @ClassName: PersonDaoTest
* @Description: 人员操作测试
* @author 李勇超
* @date 2018年12月13日下午7:57:28
*
*/
public class PersonDaoTest {

	private PersonDao personDao=new PersonDaoImpl();
	
	//以二维数组的形式返回所有部门数据
	@Test
	public void getAllForBasicTest() throws SQLException {
		
		String[][] allForBasic = personDao.getAllForBasic();
		for(int i=0;i<allForBasic.length;i++) {
			System.out.println(allForBasic[i][0]+" "+allForBasic[i][1]+" "+allForBasic[i][2]+" "+allForBasic[i][3]+" "+allForBasic[i][4]+" "+allForBasic[i][5]);
		}	
	}
	
	//根据员工姓名获取员工编号
	@Test
	public void  getPersonIdByNameTest() throws SQLException {
		
		String personIdByName = personDao.getPersonIdByName("李勇超");
		System.out.println(personIdByName);
	}
	
	//获取一个可用的编号
	@Test
	public void getNextIdTest() throws SQLException{
		
		long nextId = personDao.getNextId();
		System.out.println(nextId);
	}

}
