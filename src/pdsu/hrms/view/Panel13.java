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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import pdsu.hrms.daoImpl.PersonDaoImpl;
import pdsu.hrms.dao.HistoryDao;
import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.daoImpl.HistoryDaoImpl;

/**
* @ClassName: Panel13
* @Description: 删除人员信息
* @author 李勇超
* @date 2018年12月13日下午1:29:31
*
*/
public class Panel13 extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
    //定义表格和滚动面板
	private JTable table;
	private JScrollPane js;
	
    //定义BorderLayout的北，中，南三部分
	private JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	
    //标签（编号，姓名，部门）
	private JLabel lbPersonId;
	private JLabel lbName;
	private JLabel lbDept;
	
    //文本框（编号，姓名，部门）
	private JTextField tfPersonId;
	private JTextField tfName;
	private JTextField tfDept;
	
    //删除按钮
	private JButton btnDetele;
	PersonDao personDao = null;
	HistoryDao historyDao = null;
	/**
	 * 	主方法：设置整体为边界布局，并调用三部分的初始方法
	 * @throws SQLException 
	 */
	public Panel13() throws SQLException {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		addListener();
	}
	
	/**
	 * 	初始化顶部方法
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
		JLabel lbTitle = new JLabel("人员信息删除");
		lbTitle.setFont(new Font("宋体",0,16));
		
        //用网格包布局设定位置、大小和间距等参数（组件的位置）
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //把参数和组件关联起来（重点）
		layout.setConstraints(lbTitle, cons);
        //组件添加到容器中
		pTop.add(lbTitle);
		
		personDao = new PersonDaoImpl();
		String[] colTitle = new String[] {"编号","姓名","出生年月","民族","地址","部门"};
		String[][] colValue = personDao.getAllForBasic();
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
                //获取选择行
				int row = table.getSelectedRow();
                //将对应的数据显示在文本中
				tfPersonId.setText(colValue[row][0]);
				tfName.setText(colValue[row][1]);
				tfDept.setText(colValue[row][5]);
				//当点击信息项的时候，删除按钮可操作
				btnDetele.setEnabled(true);
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
	 * 	初始化中部方法
	 */
	private void initCenter() {
		pCenter = new JPanel();
        //添加 （编号项，姓名，部门）
		lbPersonId = new JLabel("编号");
		tfPersonId = new JTextField(15);
		lbName = new JLabel("姓名");
		tfName = new JTextField(15);
		lbDept = new JLabel("部门");
		tfDept = new JTextField(15);
		
		pCenter.add(lbPersonId); 
		pCenter.add(tfPersonId);
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbDept);
		pCenter.add(tfDept);
		
		add(pCenter,BorderLayout.CENTER);
		
        //初始化时三文本域不可修改
		tfPersonId.setEditable(false);
		tfName.setEditable(false);
		tfDept.setEditable(false);
	}
	
	/**
	 * 	初始化底部方法
	 */
	private void initBottom() {
		pBottom = new JPanel();
		btnDetele = new JButton("删除");
		pBottom.add(btnDetele);
		add(pBottom,BorderLayout.SOUTH);
		
        //初始化时设置删除按钮不可操作
		btnDetele.setEnabled(false);
	}
	
	
	
	/**
	 * 设置删除按钮为监听事件
	 */
	private void addListener() {
		btnDetele.addActionListener(this);
	}
	
	/**
	 * 要考虑所选记录是否可删除，（有调动历史的不能删除）
	 * 设置按钮事件响应
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnDetele) {
			personDao = new PersonDaoImpl();
			long personId = Long.parseLong(tfPersonId.getText());
			try {
				historyDao = new HistoryDaoImpl();;
				if(historyDao.hasData(personId)) {
					JOptionPane.showMessageDialog(null, "您不能删除此员工", "温馨提示", JOptionPane.ERROR_MESSAGE);
				}else {
					personDao.delete(personId);
					JOptionPane.showMessageDialog(null, "删除成功", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
					Panel13 panel13 = new Panel13();
					HrMain.splitPane.setRightComponent(panel13);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
