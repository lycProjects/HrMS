package pdsu.hrms.model;

/**
* @ClassName: History
* @Description: �䶯��ʷ��Ϣ��
* @author ���³�
* @date 2018��12��11������5:57:04
*
*/
public class History {
	private long histId;      //�䶯��ʷ���
	private String histType;  //�䶯���ͣ����������ˡ����ʣ�
	private String oldInfo;   //ԭ��Ϣ
	private String newInfo;   //����Ϣ
	private String chgDate;   //�䶯����
	private long chgNum;      //�䶯����
	private long personId;    //Ա�����
	private String name;      // ��������

	public History() {
		super();
	}
	
	public History(long histId, String histType, String oldInfo, String newInfo, String chgDate, long chgNum,
			long personId) {
		super();
		this.histId = histId;
		this.histType = histType;
		this.oldInfo = oldInfo;
		this.newInfo = newInfo;
		this.chgDate = chgDate;
		this.chgNum = chgNum;
		this.personId = personId;
	}


	public long getHistId() {
		return histId;
	}

	public void setHistId(long histId) {
		this.histId = histId;
	}

	public String getHistType() {
		return histType;
	}

	public void setHistType(String histType) {
		this.histType = histType;
	}

	public String getOldInfo() {
		return oldInfo;
	}

	public void setOldInfo(String oldInfo) {
		this.oldInfo = oldInfo;
	}

	public String getNewInfo() {
		return newInfo;
	}

	public void setNewInfo(String newInfo) {
		this.newInfo = newInfo;
	}

	public String getChgDate() {
		return chgDate;
	}

	public void setChgDate(String chgDate) {
		this.chgDate = chgDate;
	}

	public long getChgTime() {
		return chgNum;
	}

	public void setChgTime(long chgTime) {
		this.chgNum = chgTime;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getChgNum() {
		return chgNum;
	}

	public void setChgNum(long chgNum) {
		this.chgNum = chgNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "History [histId=" + histId + ", histType=" + histType + ", oldInfo=" + oldInfo + ", newInfo=" + newInfo
				+ ", chgDate=" + chgDate + ", chgTime=" + chgNum + ", personId=" + personId + "]";
	}

}
