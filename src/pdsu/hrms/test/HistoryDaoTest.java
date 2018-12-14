package pdsu.hrms.test;


import java.sql.SQLException;

import org.junit.Test;

import pdsu.hrms.dao.HistoryDao;
import pdsu.hrms.daoImpl.HistoryDaoImpl;

/**
* @ClassName: HistoryDaoTest
* @Description: ��ʷ��¼��������
* @author ���³�
* @date 2018��12��13������7:12:34
*
*/
public class HistoryDaoTest {
	
	private HistoryDao historyDao=new  HistoryDaoImpl();

	@Test
	public void getAllByTypeTest() throws SQLException {
		
		String[][] allByType = historyDao.getAllByType("��Ա����");
		for(int i=0;i<allByType.length;i++) {
			System.out.println(allByType[i][0]+" "+allByType[i][1]+" "+allByType[i][2]+" "+allByType[i][3]+" "+allByType[i][4]+" "+allByType[i][5]);
		}
		
	}
	
	//��ȡ��һ�����
	@Test
	public void getNextIdTest() throws SQLException {
		
		long nextId = historyDao.getNextId();
		System.out.println(nextId);
	}

}
