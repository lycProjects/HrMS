package pdsu.hrms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.db.C3P0Utils;
import pdsu.hrms.model.Dept;
import pdsu.hrms.model.Person;

/**
* @ClassName: PersonDaoImpl
* @Description: Ա����Ϣʵ����
* @author ���³�
* @date 2018��12��12������1:50:31
*
*/
public class PersonDaoImpl implements PersonDao{
	/**
	 * 	�Զ�ά������ʽ�������в������ݣ��ο����Ź�����棩
	 * 	���  ����  ��������  ����  ��ַ  ���ţ�һ��-������
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public String[][] getAllForBasic() throws SQLException{
		//��ȡ���ݿ�����
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		//�½����ڱ���Ķ���
		String [][]data = null;
		List<Person> list = new LinkedList<Person>();
		String sql = "select * from dept,person where dept.deptId=person.deptId";
		try {
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				Person person = new Person();
				person.setPersonId(rs.getLong("personId"));
				person.setPname(rs.getString("pName"));
				person.setBirth(rs.getString("birth"));
				person.setNat(rs.getString("nat"));
				person.setAddress(rs.getString("address"));
				person.setDeptId(rs.getLong("deptId"));
				person.setDeptName(rs.getString("dept1Name") + "-" + rs.getString("dept2Name"));
				list.add(person);
				
				data = new String[list.size()][6];
				for(int i=0;i<list.size();i++) {
					Person per = list.get(i);
					data[i][0] = String.valueOf(per.getPersonId());
					data[i][1] = per.getPname();
					data[i][2] = per.getBirth();
					data[i][3] = per.getNat();
					data[i][4] = per.getAddress();
					data[i][5] = per.getDeptName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 	����  ����id ֵ��ȡ��id��Ӧ�Ķ���
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public Person getPersonById(long personId) throws SQLException{
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select * from person where personId=?";
		Person person = null;
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, personId);
			rs = prst.executeQuery();
			while(rs.next()) {
				person = new Person();
				person.setPersonId(rs.getLong("personId"));
				person.setPname(rs.getString("pName"));
				person.setSex(rs.getString("sex"));
				person.setBirth(rs.getString("birth"));
				person.setNat(rs.getString("nat"));
				person.setAddress(rs.getString("address"));
				person.setDeptId(rs.getLong("deptId"));
				person.setSalary(rs.getString("salary"));
				person.setAssess(rs.getString("assess"));
				person.setOther(rs.getString("other"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}
	
	/**
	 * 	Ա����Ϣ�����
	 * @throws SQLException 
	 */
	@Override
	public void add(Person p) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "insert into person(personId,pName,sex,birth,nat,address,deptId,salary,assess,other)"
				+ "value(?,?,?,?,?,?,?,?,?,?)";
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, p.getPersonId());
			prst.setString(2,p.getPname());
			prst.setString(3,p.getSex());
			prst.setString(4,p.getBirth());
			prst.setString(5,p.getNat());
			prst.setString(6,p.getAddress());
			prst.setLong(7,p.getDeptId());
			prst.setString(8,p.getSalary());
			prst.setString(9,p.getAssess());
			prst.setString(10,p.getOther());
			prst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Ա����Ϣ���޸�
	 * @throws SQLException 
	 */
	@Override
	public void update(Person p) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "update person set pName=?,sex=?,birth=?,nat=?,address=?,deptId=?,salary=?,assess=?,other=?"
				+ " where personId = ?";
		try {
			prst = conn.prepareStatement(sql);
			prst.setString(1, p.getPname());
			prst.setString(2, p.getSex());
			prst.setString(3, p.getBirth());
			prst.setString(4, p.getNat());
			prst.setString(5, p.getAddress());
			prst.setLong(6, p.getDeptId());
			prst.setString(7, p.getSalary());
			prst.setString(8, p.getAssess());
			prst.setString(9, p.getOther());
			prst.setLong(10, p.getPersonId());
			prst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	ͨ��personIdɾ��Ա����Ϣ
	 * @throws SQLException 
	 */
	@Override
	public void delete(long personId) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "delete from Person where personId = ?";
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, personId);
			prst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	��ȡһ�����õı��
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public long getNextId() throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql ="select * from person where personId = (select max(personId) from person)";
		Person person = null;
		try {
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				person = new Person();
				person.setPersonId(rs.getLong("personId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person.getPersonId() + 1;
	}
	
	/**
	 * 	�ԡ����-����������ʽ��ѯ����Ա������Ϣ���޸�Ա����Ϣʱ�ã�
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public String[] getNamesWithId() throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String []data = null;
		List<Person> list = new LinkedList<Person>();
		String sql = "select personId,pName from person";
		try {
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			while(rs.next()) {
				Person person = new Person();
				person.setPersonId(rs.getLong("personId"));
				person.setPname(rs.getString("pName"));
				list.add(person);
				data = new String[list.size()];
				for(int i=0;i<list.size();i++) {
					Person per = list.get(i);
					data[i] = String.valueOf(per.getPersonId()+"-"+per.getPname());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * ����ԱpersonId��ѯ��ǰ�Ĳ��ű�ţ�����ʱ�ã�
	 * @param personId
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public long getDeptId(long personId) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select deptId from person where personId=?";
		Dept dept = null;
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, personId);
			rs = prst.executeQuery();
			while(rs.next()) {
				dept = new Dept();
				dept.setDeptId(rs.getLong("deptId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dept.getDeptId();
	}
	
	/**
	 * ����name��ȡԱ�����
	 * @param personId
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public String getPersonIdByName(String name) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		String sql = "select personId from person where pName=?";
		Person person = null;
		try {
			prst = conn.prepareStatement(sql);
			prst.setString(1, name);
			rs = prst.executeQuery();
			while(rs.next()) {
				person = new Person();
				person.setPersonId(rs.getLong("personId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(person.getPersonId());
	}
	
	/**
	 * ���²���
	 * @param personId
	 * @param deptId
	 * @throws SQLException 
	 */
	@Override
	public void updateDept(long personId,long deptId) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "update person set deptId=? where personId=?";
		try {
			prst = conn.prepareStatement(sql);
			prst.setLong(1, deptId);
			prst.setLong(2, personId);
			prst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���¿��˽��
	 * @param personId
	 * @param assess
	 * @throws SQLException 
	 */
	@Override
	public void updateAssess(long personId,String assess) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "update person set assess=? where personId=?";
		try {
			prst = conn.prepareStatement(sql);
			prst.setString(1, assess);
			prst.setLong(2, personId);
			prst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����н��
	 * @param personId
	 * @param salary
	 * @throws SQLException 
	 */
	@Override
	public void updateSalary(long personId,String salary) throws SQLException {
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		String sql = "update person set salary=? where personId=?";
		try {
			prst = conn.prepareStatement(sql);
			prst.setString(1, salary);
			prst.setLong(2, personId);
			prst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	��ѯ����Ա���Ĳ��ţ����˺�������Ϣ
	 * 	����  ����  �Ա�  ���� н��  ������Ϣ
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public String[][] getAllForHistory() throws SQLException{
		//��ȡ���ݿ�����
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement prst = null;
		ResultSet rs = null;
		//�½����ڱ���Ķ���
		String [][]data = null;
		List<Person> list = new LinkedList<Person>();
		String sql = "select * from dept,person where dept.deptId=person.deptId";
		try {
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			Person person = null;
			while(rs.next()) {
				person = new Person();
				person.setPersonId(rs.getLong("personId"));
				person.setPname(rs.getString("pName"));
				person.setSex(rs.getString("sex"));
				person.setDeptName(rs.getString("dept1Name") + "-" + rs.getString("dept2Name"));
				person.setSalary(rs.getString("salary"));
				person.setAssess(rs.getString("assess"));
				list.add(person);
				data = new String[list.size()][6];
				for(int i=0;i<list.size();i++) {
					Person per = list.get(i);
					data[i][0] = String.valueOf(per.getPersonId());
					data[i][1] = per.getPname();
					data[i][2] = per.getSex();
					data[i][3] = per.getDeptName();
					data[i][4] = per.getSalary();
					data[i][5] = per.getAssess();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
