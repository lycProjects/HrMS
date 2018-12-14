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

import pdsu.hrms.dao.HistoryDao;
import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.daoImpl.HistoryDaoImpl;
import pdsu.hrms.daoImpl.PersonDaoImpl;
import pdsu.hrms.model.History;

/**
* @ClassName: Panel31
* @Description: 人员考核界面
* @author 李勇超
* @date 2018年12月13日下午8:37:48
*
*/
public class Panel31 extends JPanel implements ActionListener,ItemListener{
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane js;
	private JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	private JLabel lbName;
	private JLabel lbOldAssess;
	private JLabel lbNowAssess;
	private JTextField tfName;
	private JTextField tfOldAssess;
	JButton btnConfirm;
	JButton btnClear;
	JComboBox<String> comboAssess = null;
	
	String OldAssess = "";
	String NowAssess = "未审核";
	String PersonID = null;
	History history = null;
	HistoryDao historyDao = null;
	PersonDao personDao = null;
	
	/**
	 * 构造器
	 * @throws SQLException 
	 */
	public Panel31() throws SQLException {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		addListener();
	}
	
	/**
	 * 初始化顶部信息
	 * @throws SQLException
	 */
	private void initTop() throws SQLException {
		pTop = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		pTop.setLayout(layout);
		GridBagConstraints cons = null;
		JLabel lbTitle = new JLabel("人员考核");
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
				tfOldAssess.setText(colValue[row][5]);
				tfName.setEditable(false);
				tfOldAssess.setEditable(false);
				comboAssess.setEnabled(true);
				btnConfirm.setEnabled(true);
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
	 */
	private void initCenter() {
		pCenter = new JPanel();
		//添加 （姓名，原部门，新部门）
		lbName = new JLabel("姓名");
		tfName = new JTextField(15);
		lbOldAssess = new JLabel("上次考核");
		tfOldAssess = new JTextField(15);
		lbNowAssess = new JLabel("本次考核");
		String []deptMessage = new String[] {"优秀","合格","不合格"};
		comboAssess = new JComboBox<String>(deptMessage);
		
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbOldAssess);
		pCenter.add(tfOldAssess);
		pCenter.add(lbNowAssess);
		pCenter.add(comboAssess);
		
		add(pCenter,BorderLayout.CENTER);
		
		//初始化时三文本域不可修改
		tfName.setEditable(false);
		tfOldAssess.setEditable(false);
		comboAssess.setEnabled(false);
	}
	
	/**
	 * 初始化底部信息
	 */
	private void initBottom() {
		pBottom = new JPanel();
		btnConfirm = new JButton("确定");
		btnClear = new JButton("清空");
		pBottom.add(btnConfirm);
		pBottom.add(btnClear);
		add(pBottom,BorderLayout.SOUTH);
		
		//初始化时设置删除按钮不可操作
		btnConfirm.setEnabled(false);
	}
	
	/**
	 * 将控件添加到事件监听
	 */
	private void addListener() {
		btnConfirm.addActionListener(this);
		btnClear.addActionListener(this);
		comboAssess.addItemListener(this);
	}

	/**
	 * 获取下拉列表的新考核信息
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
			NowAssess = (String) e.getItem();
		}
	}
	
	/**
	 * 事件监听器
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnConfirm) {
			personDao = new PersonDaoImpl();
			try {
				PersonID = personDao.getPersonIdByName(tfName.getText());
				personDao.updateAssess(Long.parseLong(PersonID), NowAssess);
				historyDao = new HistoryDaoImpl();
				String f1 = String.valueOf(historyDao.getNextId());
				OldAssess = tfOldAssess.getText();
				Date now = new Date();
				DateFormat date = DateFormat.getDateTimeInstance();
				String f5 = date.format(now);
				Long f6 = (long) historyDao.getChgCount(Long.parseLong(PersonID),"人员考核");
				History h = new History(Long.parseLong(f1), "人员考核", OldAssess, NowAssess, f5, f6, Long.parseLong(PersonID));
				historyDao.add(h);
				JOptionPane.showMessageDialog(null, "人员考核成功", "温馨提示",  JOptionPane.INFORMATION_MESSAGE);
				Panel31 panel31 = new Panel31();
				HrMain.splitPane.setRightComponent(panel31);
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}else if(obj == btnClear) {
			setNull();
		}
	}

	/**
	 * 	清空方法
	 */
	private void setNull()
	{
		tfName.setText(null);
		tfOldAssess.setText(null);
		tfName.setEditable(false);
		tfOldAssess.setEditable(false);
		comboAssess.setEnabled(false);
		btnConfirm.setEnabled(false);
		btnClear.setEnabled(true);
	}
}
