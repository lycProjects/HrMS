package pdsu.hrms.model;

/**
* @ClassName: Person
* @Description: Ա����Ϣ��
* @author ���³�
* @date 2018��12��11������5:57:38
*
*/
public class Person {

	private long personId;  //Ա�����
	private String pname;   //����
	private String sex;     //�Ա�
	private String birth;   //��������
	private String nat;     //����
	private String address; //��ַ
	private long deptId;    //���ű��
	private String salary;  //н��
	private String assess;  //����
	private String other;   //����˵��
	
	private String deptName;//������ӵ�����
	
	public Person() {
		super();
	}
	
	public Person(long personId, String pname, String sex, String birth, String nat, String address, long deptId,
			String salary, String assess, String other, String deptName) {
		super();
		this.personId = personId;
		this.pname = pname;
		this.sex = sex;
		this.birth = birth;
		this.nat = nat;
		this.address = address;
		this.deptId = deptId;
		this.salary = salary;
		this.assess = assess;
		this.other = other;
		this.deptName = deptName;
	}
	
	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getNat() {
		return nat;
	}

	public void setNat(String nat) {
		this.nat = nat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAssess() {
		return assess;
	}

	public void setAssess(String assess) {
		this.assess = assess;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", pname=" + pname + ", sex=" + sex + ", birth=" + birth + ", nat="
				+ nat + ", address=" + address + ", deptId=" + deptId + ", salary=" + salary + ", assess=" + assess
				+ ", other=" + other + "]";
	}
	
	public Person(long personId, String pname, String sex, String birth, String nat, String address, long deptId,
			String salary, String assess, String other) {
		super();
		this.personId = personId;
		this.pname = pname;
		this.sex = sex;
		this.birth = birth;
		this.nat = nat;
		this.address = address;
		this.deptId = deptId;
		this.salary = salary;
		this.assess = assess;
		this.other = other;
	}
	
}
