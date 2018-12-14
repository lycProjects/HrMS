package pdsu.hrms.dao;

import java.sql.SQLException;

import pdsu.hrms.model.History;

/**
* @ClassName: HistoryDao
* @Description: ��ʷ��¼�����ӿ�
* @author ���³�
* @date 2018��12��9������2:53:24
*
*/
public interface HistoryDao {
	//��ȡĳ�ֱ䶯���͵����ݣ����Զ�ά�������ʽ����
	public String[][] getAllByType(String type) throws SQLException;
	//�䶯��ʷ���
	public void add(History h) throws SQLException;
	//��ȡһ���䶯��ʷ��ӵı��
	public long getNextId() throws SQLException;
	//������Ա��źͱ䶯���ͣ����ĳ�˵�ĳ�ֱ䶯���͵Ĵ���
	public int getChgCount(long personId, String type) throws SQLException;
	//��ѯ���ڴ����Ƿ��б䶯��ʷ���еĻ�����ֱ��ɾ������
	public boolean hasData(long personId) throws SQLException;
	//��Ա�䶯���
	String[][] getAllByTypes(String type) throws SQLException;
}
