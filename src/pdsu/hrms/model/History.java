package pdsu.hrms.model;

/**
* @ClassName: History
* @Description: 变动历史信息类
* @author 李勇超
* @date 2018年12月11日下午5:57:04
*
*/
public class History {
	private long histId;      //变动历史编号
	private String histType;  //变动类型（调动、考核、劳资）
	private String oldInfo;   //原信息
	private String newInfo;   //新信息
	private String chgDate;   //变动日期
	private long chgNum;      //变动次数
	private long personId;    //员工编号
	private String name;      // 补充属性

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
