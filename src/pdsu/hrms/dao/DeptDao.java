package pdsu.hrms.dao;

import java.sql.SQLException;

import pdsu.hrms.model.Dept;

/**
* @ClassName: DeptDao
* @Description: ���Ų����ӿ�
* @author ���³�
* @date 2018��12��11������5:52:49
*
*/
public interface DeptDao {
	//�Զ�ά������ʽ�������в�������
	public String[][] getDeptsForTable() throws SQLException;
	//���ŵ�����
	public void insert(Dept dept) throws SQLException;
	//���ŵ��޸�
	public void update(Dept dept) throws SQLException;
	//���ŵ�ɾ��
	public void delete(long deptId) throws SQLException;
	//��ȡ�ַ�����ʽ�Ĳ�����Ϣ�������-һ������-�������ţ�
	public String[] getDeptsForSelect() throws SQLException;
	//��ȡ�±��
	public long getId() throws SQLException;
	//���ݲ���id��ȡ������1-����2����ʽ���ַ���
	public String getDeptNameByDeptId(long deptId)throws SQLException;
}
