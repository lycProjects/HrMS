package pdsu.hrms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import pdsu.hrms.dao.DeptDao;
import pdsu.hrms.dao.HistoryDao;
import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.daoImpl.DeptDaoImpl;
import pdsu.hrms.daoImpl.HistoryDaoImpl;
import pdsu.hrms.daoImpl.PersonDaoImpl;
import pdsu.hrms.model.History;

/**
* @ClassName: Panel21
* @Description: 人员调动界面
* @author 李勇超
* @date 2018年12月13日下午5:36:42
*
*/
public class Panel21 extends JPanel implements ActionListener,ItemListener{

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JScrollPane js;
	private JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	private JLabel lbName;
	private JLabel lbOldDept;
	private JLabel lbNewDept;
	private JTextField tfName;
	private JTextField tfOldDept;
	private JButton btnChangeDept;
	private JButton btnClear;
	private JComboBox<String> comboDept;

	String DeptID = "1";
	String PersonID = null;
	String oldDeptID = null;
	DeptDao deptDao = null;
	PersonDao personDao = null;
	HistoryDao historyDao = null;
	
	/**
	 * 构造器
	 * @throws SQLException
	 */
	public Panel21() throws SQLException {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		addListener();
	}
	/**
	 * 初始化上部
	 * @throws SQLException
	 */
	private void initTop() throws SQLException {
		pTop = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		pTop.setLayout(layout);
		GridBagConstraints cons = null;
		JLabel lbTitle = new JLabel("人员调动");
		lbTitle.setFont(new Font("宋体",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		layout.setConstraints(lbTitle, cons);
		pTop.add(lbTitle);
		
		personDao = new PersonDaoImpl();
		String[] colTitle = new String[] {"工号","姓名","性别","部门","薪酬","考核信息"};
		String[][] colValue = personDao.getAllForHistory();
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				tfName.setText(colValue[row][1]);
				tfOldDept.setText(colValue[row][3]);
				tfName.setEditable(true);
				tfOldDept.setEditable(true);
				comboDept.setEnabled(true);
				btnChangeDept.setEnabled(true);
			}
		});
		table.setPreferredScrollableViewportSize(new Dimension(430, 300));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 300));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		layout.setConstraints(js, cons);
		pTop.add(js);
		add(pTop,BorderLayout.NORTH);
	}
	
	/**
	 * 初始化中间信息
	 * @throws SQLException 
	 */
	private void initCenter() throws SQLException {
		pCenter = new JPanel();
		lbName = new JLabel("姓名");
		tfName = new JTextField(15);
		lbOldDept = new JLabel("原部门");
		tfOldDept = new JTextField(15);
		lbNewDept = new JLabel("新部门");
		deptDao = new DeptDaoImpl();
		String []deptMessage = deptDao.getDeptsForSelect();
		comboDept = new JComboBox<String>(deptMessage);
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbOldDept);
		pCenter.add(tfOldDept);
		pCenter.add(lbNewDept);
		pCenter.add(comboDept);
		add(pCenter,BorderLayout.CENTER);
		tfName.setEditable(false);
		tfOldDept.setEditable(false);
		comboDept.setEnabled(false);
	}
	
	/**
	 * 初始化底部信息
	 */
	private void initBottom() {
		pBottom = new JPanel();
		btnChangeDept = new JButton("调入新部门");
		btnClear = new JButton("清空信息");
		pBottom.add(btnChangeDept);
		pBottom.add(btnClear);
		add(pBottom,BorderLayout.SOUTH);
		btnChangeDept.setEnabled(false);
	}
	
	/**
	 * 将控件添加到事件监听
	 */
	private void addListener() {
		btnChangeDept.addActionListener(this);
		btnClear.addActionListener(this);
		comboDept.addItemListener(this);
	}
	
	/**
	 * 获取下拉列表属性项的DeptID
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
		     String str = (String) e.getItem();
		     int i = str.indexOf("-");
		     DeptID = str.substring(0, i);
		}
	}
	
	/**
	 * 事件监听器
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnChangeDept) {
			personDao = new PersonDaoImpl();
			try {
				PersonID = personDao.getPersonIdByName(tfName.getText());
				oldDeptID = String.valueOf(personDao.getDeptId(Long.parseLong(PersonID)));
				if ((oldDeptID.equals(DeptID))||(oldDeptID==DeptID)) {
					JOptionPane.showMessageDialog(null, "不能调往相同部门", "温馨提示", JOptionPane.ERROR_MESSAGE);
					oldDeptID = null;
					return;
				}else {
					personDao.updateDept(Long.parseLong(PersonID), Long.parseLong(DeptID));
					historyDao = new HistoryDaoImpl();
					String history1 = String.valueOf(historyDao.getNextId());
					String history2 = "人员调动";
					deptDao = new DeptDaoImpl();
					String history3 = deptDao.getDeptNameByDeptId(Long.parseLong(oldDeptID));
					String history4 = deptDao.getDeptNameByDeptId(Long.parseLong(DeptID));
					Date now = new Date();
					DateFormat date = DateFormat.getDateTimeInstance();
					String history5 = date.format(now);
					Long history6 = (long) historyDao.getChgCount(Long.parseLong(PersonID),"人员调动");
					History h = new History(Long.parseLong(history1), history2, history3, history4, history5, history6, Long.parseLong(PersonID));
					historyDao.add(h);
					JOptionPane.showMessageDialog(null, "人员调动成功", "温馨提示",  JOptionPane.INFORMATION_MESSAGE);
					Panel21 panel21 = new Panel21();
					HrMain.splitPane.setRightComponent(panel21);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (obj == btnClear) {
			setNull();
		}
	}

	/**
	 * 	清空方法
	 */
	private void setNull()
	{
		tfName.setText(null);
		tfOldDept.setText(null);
		tfName.setEditable(false);
		tfOldDept.setEditable(false);
		btnChangeDept.setEnabled(false);
		btnClear.setEnabled(true);
		comboDept.setEnabled(false);
	}
}
