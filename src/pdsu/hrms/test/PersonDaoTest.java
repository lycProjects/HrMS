package pdsu.hrms.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.daoImpl.PersonDaoImpl;

/**
* @ClassName: PersonDaoTest
* @Description: ��Ա��������
* @author ���³�
* @date 2018��12��13������7:57:28
*
*/
public class PersonDaoTest {

	private PersonDao personDao=new PersonDaoImpl();
	
	//�Զ�ά�������ʽ�������в�������
	@Test
	public void getAllForBasicTest() throws SQLException {
		
		String[][] allForBasic = personDao.getAllForBasic();
		for(int i=0;i<allForBasic.length;i++) {
			System.out.println(allForBasic[i][0]+" "+allForBasic[i][1]+" "+allForBasic[i][2]+" "+allForBasic[i][3]+" "+allForBasic[i][4]+" "+allForBasic[i][5]);
		}	
	}
	
	//����Ա��������ȡԱ�����
	@Test
	public void  getPersonIdByNameTest() throws SQLException {
		
		String personIdByName = personDao.getPersonIdByName("���³�");
		System.out.println(personIdByName);
	}
	
	//��ȡһ�����õı��
	@Test
	public void getNextIdTest() throws SQLException{
		
		long nextId = personDao.getNextId();
		System.out.println(nextId);
	}

}
