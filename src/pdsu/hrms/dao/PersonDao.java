package pdsu.hrms.dao;

import java.sql.SQLException;

import pdsu.hrms.model.Person;

/**
* @ClassName: PersonDao
* @Description: ��Ա�����ӿ�
* @author ���³�
* @date 2018��12��11������1:53:58
*
*/
public interface PersonDao {
	//�Զ�ά�������ʽ�������в�������
	public String[][] getAllForBasic() throws SQLException;
	//����  ����id ֵ��ȡ��id��Ӧ�Ķ���
	public Person getPersonById(long personId) throws SQLException;
	//Ա����Ϣ�����,�޸�,ɾ��
	public void add(Person p) throws SQLException;
	//Ա����Ϣ���޸�
	public void update(Person p) throws SQLException;
	//Ա����Ϣ��ɾ��
	public void delete(long personId) throws SQLException;
	//��ȡһ�����õı��
	public long getNextId() throws SQLException;
	//�ԡ����-����������ʽ��ѯ����Ա������Ϣ
	public String[] getNamesWithId() throws SQLException;
	//����Ա����Ų�ѯ��ǰ�Ĳ��ű��
	public long getDeptId(long personId) throws SQLException;
	//����Ա��������ȡԱ�����
	public String getPersonIdByName(String name) throws SQLException;
	//���²���
	public void updateDept(long personId,long deptId) throws SQLException;
	//���¿��˽��
	public void updateAssess(long personId,String assess) throws SQLException;
	//����н��
	public void updateSalary(long personId,String salary) throws SQLException;
	//��ѯ����Ա���Ĳ��ţ����˺�������Ϣ
	public String[][] getAllForHistory() throws SQLException;
}