package pdsu.hrms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import pdsu.hrms.dao.DeptDao;
import pdsu.hrms.daoImpl.DeptDaoImpl;
import pdsu.hrms.model.Dept;

/**
* @ClassName: Panel15
* @Description: 部门管理界面
* @author 李勇超
* @date 2018年12月13日下午5:35:05
*
*/
public class Panel15 extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	//定义各种属性
	//定义面板
	private JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	
	//定义上部所需组件
	private JTable table;
	private JScrollPane js;
	
	//定义中部所需组件
	private JLabel lbDeptId;
	private JLabel lbDept1Name;
	private JLabel lbDept2Name;
	
	private JTextField tfDeptId;
	private JTextField tfDept1Name;
	private JTextField tfDept2Name;
	
	private JButton btnNextId;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnbClear;
	DeptDao deptDao = null;
	/**
	 * 	初始化顶部
	 * @throws SQLException
	 */
	private void initTop() throws SQLException {
		//创建顶部面板
		pTop = new JPanel();
		//创建表格
		String[] colTitle = new String[] {"部门","一级部门","二级部门"};
		deptDao= new DeptDaoImpl();
		String[][] colValue = deptDao.getDeptsForTable();
		
		table = new JTable(colValue,colTitle);
		//多区域选择
		//table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//单区域选择
		//table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//只能选一行
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//获取选择行
				int row = table.getSelectedRow();
				//将对应的数据显示在文本中
				tfDeptId.setText(colValue[row][0]);
				tfDept1Name.setText(colValue[row][1]);
				tfDept2Name.setText(colValue[row][2]);
				tfDept1Name.setEditable(true);
				tfDept2Name.setEditable(true);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		});
		//设置表格的默认大小
		table.setPreferredScrollableViewportSize(new Dimension(430, 300));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 300));
		pTop.add(js);
		
		add(pTop,BorderLayout.NORTH);
	}
	
	/**
	 * 	构造方法
	 * @throws SQLException
	 */
	public Panel15() throws SQLException{
		//设置为边界布局
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		addListener();
	}
	
	/**
	 * 	初始化中间
	 */
	private void initCenter() {
		pCenter = new JPanel();
		//编号项
		lbDeptId = new JLabel("部门编号");
		tfDeptId = new JTextField(15);
		//一级
		lbDept1Name = new JLabel("一级部门");
		tfDept1Name = new JTextField(15);
		//二级
		lbDept2Name = new JLabel("二级部门");
		tfDept2Name = new JTextField(15);
		
		pCenter.add(lbDeptId); 
		pCenter.add(tfDeptId);
		pCenter.add(lbDept1Name);
		pCenter.add(tfDept1Name);
		pCenter.add(lbDept2Name);
		pCenter.add(tfDept2Name);
		
		add(pCenter,BorderLayout.CENTER);
		
        //初始化时设置为禁用状态
		tfDeptId.setEditable(false);
		tfDept1Name.setEditable(false);
		tfDept2Name.setEditable(false);
	}
	
	/**
	 * 	初始化底部
	 */
	private void initBottom() {
        //新建底部，面板
		pBottom = new JPanel();
		
        //五个按钮，分别是，获取新编号，增加，修改，删除，清空
		btnNextId = new JButton("获取新编号");
		btnAdd = new JButton("增加");
		btnUpdate = new JButton("修改");
		btnDelete = new JButton("删除");
		btnbClear = new JButton("清空");
		
        //将按钮加载面板中
		pBottom.add(btnNextId);
		pBottom.add(btnAdd);
		pBottom.add(btnUpdate);
		pBottom.add(btnDelete);
		pBottom.add(btnbClear);
		
        //加面板进入边界布局的底部
		add(pBottom,BorderLayout.SOUTH);
		
		btnNextId.setEnabled(true);
		btnAdd.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnbClear.setEnabled(true);
	}
	
	/**
	 * 	监听器
	 */
	private void addListener() {
		btnNextId.addActionListener(this);
		btnAdd.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnbClear.addActionListener(this);
	}
	
	/**
	 *	 监听器处理时间
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnNextId) {
			setNull();
			deptDao = new DeptDaoImpl();
			try {
				this.tfDeptId.setText(String.valueOf(deptDao.getId()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.tfDept1Name.setEditable(true);
			this.tfDept2Name.setEditable(true);
			this.btnAdd.setEnabled(true);
			this.btnUpdate.setEnabled(false);
			this.btnDelete.setEnabled(false);
		}else if(obj == btnAdd) {
			deptDao = new DeptDaoImpl();
			Dept dept = new Dept(Long.parseLong(tfDeptId.getText()), tfDept1Name.getText(), tfDept2Name.getText());
			try {
				deptDao.insert(dept);
				JOptionPane.showMessageDialog(null, "成功添加一条记录", "温馨提示",  JOptionPane.INFORMATION_MESSAGE);
				Panel15 panel15 = new Panel15();
				HrMain.splitPane.setRightComponent(panel15);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(obj == btnUpdate) {
			deptDao = new DeptDaoImpl();
			Dept dept = new Dept(Long.parseLong(tfDeptId.getText()), tfDept1Name.getText(), tfDept2Name.getText());
			try {
				JOptionPane.showMessageDialog(null, "成功修改一条记录", "温馨提示",  JOptionPane.INFORMATION_MESSAGE);
				deptDao.update(dept);
				Panel15 panel15 = new Panel15();
				HrMain.splitPane.setRightComponent(panel15);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}else if(obj == btnDelete) {
			deptDao = new DeptDaoImpl();
			long deptId = Long.parseLong(tfDeptId.getText());
			try {
				deptDao.delete(deptId);
				JOptionPane.showMessageDialog(null, "成功删除一条记录", "温馨提示",  JOptionPane.INFORMATION_MESSAGE);
				Panel15 panel15 = new Panel15();
				HrMain.splitPane.setRightComponent(panel15);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}else if(obj == btnbClear) {
			setNull();
		}else {
			
		}
	}
	
	/**
	 * 	清空方法
	 */
	private void setNull()
	{
		tfDeptId.setText(null);
		tfDept1Name.setText(null);
		tfDept2Name.setText(null);
		tfDept1Name.setEditable(false);
		tfDept2Name.setEditable(false);
		btnNextId.setEnabled(true);
		btnAdd.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnbClear.setEnabled(true);
	}
	
}
