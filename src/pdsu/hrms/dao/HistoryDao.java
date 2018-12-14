package pdsu.hrms.dao;

import java.sql.SQLException;

import pdsu.hrms.model.History;

/**
* @ClassName: HistoryDao
* @Description: 历史记录操作接口
* @author 李勇超
* @date 2018年12月9日下午2:53:24
*
*/
public interface HistoryDao {
	//获取某种变动类型的数据，并以二维数组的形式返回
	public String[][] getAllByType(String type) throws SQLException;
	//变动历史添加
	public void add(History h) throws SQLException;
	//获取一个变动历史添加的编号
	public long getNextId() throws SQLException;
	//根据人员编号和变动类型，查出某人的某种变动类型的次数
	public int getChgCount(long personId, String type) throws SQLException;
	//查询关于此人是否有变动历史，有的话不能直接删除此人
	public boolean hasData(long personId) throws SQLException;
	//人员变动添加
	String[][] getAllByTypes(String type) throws SQLException;
}
