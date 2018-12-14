package pdsu.hrms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import pdsu.hrms.dao.HistoryDao;
import pdsu.hrms.daoImpl.HistoryDaoImpl;

/**
* @ClassName: Panel32
* @Description: 考核历史查询界面
* @author 李勇超
* @date 2018年12月14日上午10:58:23
*
*/
public class Panel32 extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane js;
	private JPanel pContent;
	HistoryDao historyDao = null;
	/**
	 * 	主方法：设置整体为边界布局，并调用三部分的初始方法
	 * @throws SQLException 
	 */
	public Panel32() throws SQLException {
		setLayout(new BorderLayout());
		initContent();
	}
	
	/**
	 * 初始化内容区域
	 * @throws SQLException
	 */
	private void initContent() throws SQLException {
		pContent = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		pContent.setLayout(layout);
		GridBagConstraints cons = null;
		JLabel lbTitle = new JLabel("人员考核历史查询查询");
		lbTitle.setFont(new Font("宋体",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		layout.setConstraints(lbTitle, cons);
		pContent.add(lbTitle);
		String[] colTitle = new String[] {"流水号","姓名","上次考核","本次考核","变更次数","变更日期"};
		historyDao = new HistoryDaoImpl();
		String[][] colValue = historyDao.getAllByType("人员考核");
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(430, 380));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 380));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		layout.setConstraints(js, cons);
		pContent.add(js);
		add(pContent,BorderLayout.NORTH);
	}
}
