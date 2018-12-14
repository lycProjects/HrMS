package pdsu.hrms.model;

/**
* @ClassName: Dept
* @Description: 部门信息类
* @author 李勇超
* @date 2018年12月11日下午5:55:03
*
*/
public class Dept {
	private long deptId;         //部门编号
	private String dept1Name;   //一级部门
	private String dept2Name;  //二级部门

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
