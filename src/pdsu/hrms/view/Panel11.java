package pdsu.hrms.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import pdsu.hrms.dao.DeptDao;
import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.daoImpl.DeptDaoImpl;
import pdsu.hrms.daoImpl.PersonDaoImpl;
import pdsu.hrms.model.Person;


/**
* @ClassName: Panel11
* @Description: 添加人员信息界面
* @author 李勇超
* @date 2018年12月12日下午7:25:02
*
*/
public class Panel11 extends JPanel implements ActionListener,ItemListener{
	private static final long serialVersionUID = 1L;
	
	JPanel pContent;  //添加人员信息内容区域面板
	JScrollPane js;   //添加人员信息所在的滚动面板
	 
	JTextField tfPersonId;  //人员编号
	JTextField tfName;  //姓名
	JTextField tfSex;  //性别
	JTextField tfBirth;  //出生日期
	JTextField tfNat;  //民族
	JTextField tfAddress;  //地址
	JTextField tfOther;  //其他说明
	
	JComboBox<String> comboDept;  //部门信息
	JButton btnAdd;  //增加
	JButton btnClear;  //清空
	
    //默认属性	
	String DeptID = "1";
	String Salary = "0";
	String Assess = "未考核";

	PersonDao personDao = null; 
	DeptDao deptDao = null;
	public Panel11() throws Exception {
        //设置为网格包布局
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints cons = null;
		setLayout(gridBag);
		
        //添加标题（增加人员信息）创建组件并设置字体样式
		JLabel lbTitle = new JLabel("添加人员信息");
		lbTitle.setFont(new Font("宋体",0,16));
		
        //用网格包布局设定位置、大小和间距等参数（组件的位置）
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //把参数和组件关联起来（重点）
		gridBag.setConstraints(lbTitle, cons);
		
        //组件添加到容器中
		add(lbTitle);
		
        //调用初始化添加人员信息区域
		initContent();
		js = new JScrollPane(pContent);
        //添加信息下方的区域（窗口设置大小，位置）
		js.setPreferredSize(new Dimension(480, 380));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		gridBag.setConstraints(js, cons);
		add(js);
		addListener();
	}
	
	/**
	 * 
	 * 	初始化整个页面的方法
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initContent() throws SQLException {
		
        //创建内容面板并设置布局方式为网格布局
		pContent = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		pContent.setLayout(layout);
		
		GridBagConstraints cons = null;
		
        //添加第1行组件（人员编号标签、人员编号文本框、人员姓名标签、人员姓名文本框）
        //初始化人员编号标签并设置字体
		JLabel lbPersonId = new JLabel("人员编号");
		lbPersonId.setFont(new Font("宋体",0,12));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //设置上左下右间距
		cons.insets = new Insets(10,10,10,1);
		layout.setConstraints(lbPersonId, cons);
		pContent.add(lbPersonId);
		
        //人员编号文本框
		tfPersonId = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 0;
		cons.insets = new Insets(10,1,10,15);
		layout.setConstraints(tfPersonId, cons);
		pContent.add(tfPersonId);
		
        //人员姓名标签
		JLabel lbName = new JLabel("人员姓名");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 0;
		cons.insets = new Insets(10,15,10,1);
		layout.setConstraints(lbName, cons);
		pContent.add(lbName);
		
        //人员姓名文本框
		tfName = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 0;
		cons.insets = new Insets(10,1,10,10);
		layout.setConstraints(tfName, cons);
		pContent.add(tfName);
		
		//添加第2行组件（性别标签、性别文本框、出生年月标签、出生年月文本框）
		//性别标签
		JLabel lbSex = new JLabel("性别");
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		cons.insets = new Insets(10,10,10,1);
		layout.setConstraints(lbSex, cons);
		pContent.add(lbSex);
		
		//性别文本框
		tfSex = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 1;
		cons.insets = new Insets(10,1,10,15);
		layout.setConstraints(tfSex, cons);
		pContent.add(tfSex);
		
		//出生年月标签
		JLabel lbBirth = new JLabel("出生年月");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 1;
		cons.insets = new Insets(10,15,10,1);
		layout.setConstraints(lbBirth, cons);
		pContent.add(lbBirth);
		
		//出生年月文本框
		tfBirth = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 1;
		cons.insets = new Insets(10,1,10,10);
		layout.setConstraints(tfBirth, cons);
		pContent.add(tfBirth);
		
		//添加第3行组件（民族标签、民族文本框、地址标签、地址文本框）
		//民族标签
		JLabel lbNat = new JLabel("民族");
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 2;
		cons.insets = new Insets(10,10,10,1);
		layout.setConstraints(lbNat, cons);
		pContent.add(lbNat);
		
		//民族文本框
		tfNat = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 2;
		cons.insets = new Insets(10,1,10,15);
		layout.setConstraints(tfNat, cons);
		pContent.add(tfNat);
		
		//地址标签
		JLabel lbAddress = new JLabel("地址");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 2;
		cons.insets = new Insets(10,15,10,1);
		layout.setConstraints(lbAddress, cons);
		pContent.add(lbAddress);
		
		//地址文本框
		tfAddress = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 2;
		cons.insets = new Insets(10,1,10,10);
		layout.setConstraints(tfAddress, cons);
		pContent.add(tfAddress);
		
		//添加第4行组件（部门标签、部门下拉列表、其他标签、其他文本框）
		//部门标签
		JLabel lbDept = new JLabel("部门");
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 3;
		cons.insets = new Insets(10,10,10,1);
		layout.setConstraints(lbDept, cons);
		pContent.add(lbDept);
		
		//部门下拉列表
		deptDao = new DeptDaoImpl();
		String [] allType = deptDao.getDeptsForSelect();
		comboDept = new JComboBox(allType);
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 3;
		cons.insets = new Insets(10,1,10,15);
		layout.setConstraints(comboDept, cons);
		pContent.add(comboDept);
		
		//其他标签
		JLabel lbOther = new JLabel("其他");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 3;
		cons.insets = new Insets(10,15,10,1);
		layout.setConstraints(lbOther, cons);
		pContent.add(lbOther);
		
		//其他文本框
		tfOther = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 3;
		cons.insets = new Insets(10,1,10,10);
		layout.setConstraints(tfOther, cons);
		pContent.add(tfOther);
		
		//添加第5行组件（增加按钮、清空按钮）
		//增加
		btnAdd = new JButton("增加");
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 4;

		cons.gridwidth = 2;   
		cons.insets = new Insets(10,10,10,10);
		layout.setConstraints(btnAdd, cons);
		pContent.add(btnAdd);
		
		//清空
		btnClear = new JButton("清空");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 4;
		cons.gridwidth = 2;
		cons.insets = new Insets(10,10,10,10);
		layout.setConstraints(btnClear, cons);
		pContent.add(btnClear);
		
		tfPersonId.setEditable(false);
		tfName.setEditable(true);
		tfSex.setEditable(true);
		tfBirth.setEditable(true);
		tfNat.setEditable(true);
		tfAddress.setEditable(true);
		tfOther.setEditable(true);
		personDao = new PersonDaoImpl();
		tfPersonId.setText(String.valueOf(personDao.getNextId()));
	}
	
	/**
	 * 	处理下拉列表
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
			Object tempStr = e.getItem();
			int i = ((String) tempStr).indexOf("-");
			DeptID = ((String) tempStr).substring(0, i);
		}
		
	}
	
	/**
	 * 	为需要进行事件响应的声明为事件监听
	 * @throws Exception
	 */
	public void addListener() throws Exception {
		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);
		comboDept.addItemListener(this);
	}
	
	/**
	 * 	处理页面中按钮事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Person p = null;
		if (obj == btnAdd) {
			if(tfName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入姓名！！！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				return ;
			}else if(tfSex.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入性别！！！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				return ;
			}else if(tfBirth.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入生生年月！！！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				return ;
			}else if(tfNat.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入民族！！！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				return ;
			}else if(tfAddress.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入地址！！！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				return ;
			}
			p = new Person();
			p.setPersonId(Long.parseLong(tfPersonId.getText()));
			p.setPname(tfName.getText());
			p.setSex(tfSex.getText());
			p.setBirth(tfBirth.getText());
			p.setNat(tfNat.getText());
			p.setAddress(tfAddress.getText());
			p.setDeptId(Long.parseLong(DeptID));
			p.setSalary(this.Salary);
			p.setAssess(this.Assess);
			p.setOther(tfOther.getText());
			try {
				PersonDao  personDao= new PersonDaoImpl();
				personDao.add(p);
				JOptionPane.showMessageDialog(null, "添加成功", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				Panel14 panel14 = new Panel14();
				HrMain.splitPane.setRightComponent(panel14);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (obj == this.btnClear) {
			setNull();
		}
	} 
	
	/**
	 * 清空方法调用具体实现
	 */
	public void setNull() {
		tfName.setText(null);
		tfSex.setText(null);
		tfBirth.setText(null);
		tfNat.setText(null);
		tfAddress.setText(null);
		tfOther.setText(null);
	}
}
