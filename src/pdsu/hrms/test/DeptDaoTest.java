package pdsu.hrms.test;


import java.sql.SQLException;

import org.junit.Test;

import pdsu.hrms.dao.DeptDao;
import pdsu.hrms.daoImpl.DeptDaoImpl;
import pdsu.hrms.model.Dept;

/**
* @ClassName: DeptDaoTest
* @Description: ���Ų���
* @author ���³�
* @date 2018��12��13������6:53:25
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
	
	//����
	@Test
	public void insertTest() throws SQLException {
		
		Dept dept=new Dept();
		dept.setDeptId(12);
		dept.setdept1Name("ѧ����");
		dept.setdept2Name("�칫��");
		deptDao.insert(dept);
	}
	
	//�޸�
	@Test
	public void updateTest() throws SQLException {
		
		Dept dept=new Dept();
		dept.setDeptId(12);
		dept.setdept2Name("�칫��1");
		deptDao.update(dept);
	}
	
	//ɾ��
	@Test
	public void deleteTest() throws SQLException {
		
		deptDao.delete(12);
	}
	
	
	@Test
	public void getDeptsSelectTest() throws SQLException {
		
		//��ȡ�ַ�����ʽ�Ĳ�����Ϣ:�����-һ������-�������ţ�
		String[] deptsForSelect = deptDao.getDeptsForSelect();
		for (String string : deptsForSelect) {
			System.out.println(string);
		}
		
		//���ݲ���id��ȡ������1-����2����ʽ���ַ���
		String deptNameByDeptId = deptDao.getDeptNameByDeptId(1);
		System.out.println(deptNameByDeptId);
	}
	
	//��ȡ�±��
	@Test
	public void getIdTest() throws SQLException {
		long id = deptDao.getId();
		System.out.println(id);
	}

}
