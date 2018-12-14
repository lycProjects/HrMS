package pdsu.hrms.model;

/**
* @ClassName: Dept
* @Description: ������Ϣ��
* @author ���³�
* @date 2018��12��11������5:55:03
*
*/
public class Dept {
	private long deptId;         //���ű��
	private String dept1Name;   //һ������
	private String dept2Name;  //��������

	public Dept() {
		super();
	}
	
	public Dept(long deptId, String dept1Name, String dept2Name) {
		super();
		this.deptId = deptId;
		this.dept1Name = dept1Name;
		this.dept2Name = dept2Name;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getdept1Name() {
		return dept1Name;
	}

	public void setdept1Name(String dept1Name) {
		this.dept1Name = dept1Name;
	}

	public String getdept2Name() {
		return dept2Name;
	}

	public void setdept2Name(String dept2Name) {
		this.dept2Name = dept2Name;
	}

	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", dept1Name=" + dept1Name + ", dept2Name=" + dept2Name + "]";
	}

}
