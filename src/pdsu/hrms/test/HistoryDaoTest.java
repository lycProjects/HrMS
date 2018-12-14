package pdsu.hrms.test;


import java.sql.SQLException;

import org.junit.Test;

import pdsu.hrms.dao.HistoryDao;
import pdsu.hrms.daoImpl.HistoryDaoImpl;

/**
* @ClassName: HistoryDaoTest
* @Description: 历史记录操作测试
* @author 李勇超
* @date 2018年12月13日下午7:12:34
*
*/
public class HistoryDaoTest {
	
	private HistoryDao historyDao=new  HistoryDaoImpl();

	@Test
	public void getAllByTypeTest() throws SQLException {
		
		String[][] allByType = historyDao.getAllByType("人员调动");
		for(int i=0;i<allByType.length;i++) {
			System.out.println(allByType[i][0]+" "+allByType[i][1]+" "+allByType[i][2]+" "+allByType[i][3]+" "+allByType[i][4]+" "+allByType[i][5]);
		}
		
	}
	
	//获取下一个编号
	@Test
	public void getNextIdTest() throws SQLException {
		
		long nextId = historyDao.getNextId();
		System.out.println(nextId);
	}

}
