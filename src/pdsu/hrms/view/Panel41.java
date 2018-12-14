package pdsu.hrms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
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
* @ClassName: Panel41
* @Description: 劳资分配管理界面
* @author 李勇超
* @date 2018年12月14日下午1:38:49
*
*/
public class Panel41 extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
    //定义表格和滚动面板
	private JTable table;
	private JScrollPane js;
	private JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	private JLabel lbName;
	private JLabel lbOldSalary;
	private JLabel lbNewSalary;
	private JTextField tfName;
	private JTextField tfOldSalary;
	private JTextField tfNewSalary;
	
	JButton btnConfirm;
	JButton btnClear;
	String PersonID = null;
	PersonDao personDao = null;
	HistoryDao historyDao = null;
	
	/**
	 * 构造器
	 * @throws SQLException
	 */
	public Panel41() throws SQLException {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		addListener();
	}
	
	/**
	 * 初始化顶部
	 * @throws SQLException
	 */
	private void initTop() throws SQLException {
        //声明面板
		pTop = new JPanel();
        //声明布局方式：网格包布局
		GridBagLayout layout = new GridBagLayout();
		pTop.setLayout(layout);
		GridBagConstraints cons = null;
		
        //添加标题（增加人员信息）创建组件并设置字体样式
		JLabel lbTitle = new JLabel("劳资分配");
		lbTitle.setFont(new Font("宋体",0,16));
		
        //用网格包布局设定位置、大小和间距等参数（组件的位置）
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //把参数和组件关联起来（重点）
		layout.setConstraints(lbTitle, cons);
        //组件添加到容器中
		pTop.add(lbTitle);
		
        //人员调动界面的操作方法
		personDao = new PersonDaoImpl();
		String[] colTitle = new String[] {"工号","姓名","性别","部门","薪酬","考核信息"};
		String[][] colValue = personDao.getAllForHistory();
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
                //获取选择行
				int row = table.getSelectedRow();
                //将对应的数据显示在文本中
				tfName.setText(colValue[row][1]);
				tfOldSalary.setText(colValue[row][4]);
				//当点击信息项的时候，删除按钮可操作
				btnConfirm.setEnabled(true);
			}
		});
        //设置表格的默认大小
		table.setPreferredScrollableViewportSize(new Dimension(430, 300));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 300));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		layout.setConstraints(js, cons);
		pTop.add(js);
        //组件添加到容器中
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
		lbOldSalary = new JLabel("调整前工资");
		tfOldSalary = new JTextField(15);
		lbNewSalary = new JLabel("调整后工资");
		tfNewSalary = new JTextField(15);
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbOldSalary);
		pCenter.add(tfOldSalary);
		pCenter.add(lbNewSalary);
		pCenter.add(tfNewSalary);
		add(pCenter,BorderLayout.CENTER);
		
		//初始化时三文本域不可修改
		tfName.setEditable(false);
		tfOldSalary.setEditable(false);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnConfirm) {
			personDao = new PersonDaoImpl();
			try {
				PersonID = personDao.getPersonIdByName(tfName.getText());
				personDao.updateSalary(Long.parseLong(PersonID), tfNewSalary.getText());
				historyDao = new HistoryDaoImpl();
				String f1 = String.valueOf(historyDao.getNextId());
				Date now = new Date();
				DateFormat date = DateFormat.getDateTimeInstance();
				String f5 = date.format(now);
				Long f6 = (long) historyDao.getChgCount(Long.parseLong(PersonID),"劳资分配");
				History h = new History(Long.parseLong(f1), "劳资分配", tfOldSalary.getText(), tfNewSalary.getText(), f5, f6, Long.parseLong(PersonID));
				historyDao.add(h);
				JOptionPane.showMessageDialog(null, "薪资调整成功", "温馨提示",  JOptionPane.INFORMATION_MESSAGE);
				Panel41 panel41 = new Panel41();
				HrMain.splitPane.setRightComponent(panel41);
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
		tfOldSalary.setText(null);
		btnClear.setEnabled(true);
	}
}
