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

import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.daoImpl.PersonDaoImpl;
import pdsu.hrms.model.Person;


/**
* @ClassName: Panel12
* @Description: 修改人员信息界面
* @author 李勇超
* @date 2018年12月12日下午10:51:38
*
*/
public class Panel12 extends JPanel implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;


	JPanel pContent;  //修改人员信息内容区域面板
	JScrollPane js;  //添加人员信息所在的滚动面板

	JTextField tfPersonId;  //人员编号
	JTextField tfName;  //姓名
	JTextField tfSex;  //性别
	JTextField tfBirth;  //出生日期
	JTextField tfNat;  //民族
	JTextField tfAddress;  //地址
	JTextField tfOther;  //其他说明

	JComboBox<String> comboPerson;  //人员信息
	JButton btnUpdate;  //增加
	JButton btnClear;  //清空
	
	//设置person的部分属性，不从页面中获取
	String DeptID = "";
	String Salary = "";
	String Assess = "";
	String personID = "";
    //新建person对象，及其数据库操作对象
	Person person = null;
	PersonDao personDao = null;
	
	public Panel12() throws Exception {
        //设置为网格包布局
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints cons = null;
		setLayout(gridBag);

        //添加标题（增加人员信息）创建组件并设置字体样式
		JLabel lbTitle = new JLabel("修改人员信息");
		lbTitle.setFont(new Font("宋体", 0, 16));

        //用网格包布局设定位置、大小和间距等参数（组件的位置）
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //把参数和组件关联起来
		gridBag.setConstraints(lbTitle, cons);
        //组件添加到容器中
		add(lbTitle);

        //调用初始化添加人员信息区域
		initContent();
		js = new JScrollPane(pContent);
        //添加信息下方的区域设置位置关联并添加到容器中（窗口设置大小，位置）
		js.setPreferredSize(new Dimension(480, 380));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		gridBag.setConstraints(js, cons);
		add(js);
		addListener();
	}

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
		lbPersonId.setFont(new Font("宋体", 0, 12));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //设置上左下右间距
		cons.insets = new Insets(10, 10, 10, 1);
		layout.setConstraints(lbPersonId, cons);
		pContent.add(lbPersonId);

        //人员编号文本框
		tfPersonId = new JTextField(15);
		tfPersonId.setText("请输入人员编号");
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 0;
		cons.insets = new Insets(10, 1, 10, 15);
		layout.setConstraints(tfPersonId, cons);
		pContent.add(tfPersonId);

        //人员姓名标签
		JLabel lbName = new JLabel("人员姓名");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 0;
		cons.insets = new Insets(10, 15, 10, 1);
		layout.setConstraints(lbName, cons);
		pContent.add(lbName);

        //人员姓名文本框
		tfName = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 0;
		cons.insets = new Insets(10, 1, 10, 10);
		layout.setConstraints(tfName, cons);
		pContent.add(tfName);

        //添加第2行组件（性别标签、性别文本框、出生年月标签、出生年月文本框）
        //初始化性别标签
		JLabel lbSex = new JLabel("性别");
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		cons.insets = new Insets(10, 10, 10, 1);
		layout.setConstraints(lbSex, cons);
		pContent.add(lbSex);

        //性别文本框
		tfSex = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 1;
		cons.insets = new Insets(10, 1, 10, 15);
		layout.setConstraints(tfSex, cons);
		pContent.add(tfSex);

        //出生年月标签
		JLabel lbBirth = new JLabel("出生年月");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 1;
		cons.insets = new Insets(10, 15, 10, 1);
		layout.setConstraints(lbBirth, cons);
		pContent.add(lbBirth);

        //出生年月文本框
		tfBirth = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 1;
		cons.insets = new Insets(10, 1, 10, 10);
		layout.setConstraints(tfBirth, cons);
		pContent.add(tfBirth);

        //添加第3行组件（民族标签、民族文本框、地址标签、地址文本框）
        //初始化民族标签
		JLabel lbNat = new JLabel("民族");
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 2;
		cons.insets = new Insets(10, 10, 10, 1);
		layout.setConstraints(lbNat, cons);
		pContent.add(lbNat);

        //民族文本框
		tfNat = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 2;
		cons.insets = new Insets(10, 1, 10, 15);
		layout.setConstraints(tfNat, cons);
		pContent.add(tfNat);

        //初始化地址标签
		JLabel lbAddress = new JLabel("地址");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 2;
		cons.insets = new Insets(10, 15, 10, 1);
		layout.setConstraints(lbAddress, cons);
		pContent.add(lbAddress);

        //地址文本框
		tfAddress = new JTextField(15);
		cons = new GridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 2;
		cons.insets = new Insets(10, 1, 10, 10);
		layout.setConstraints(tfAddress, cons);
		pContent.add(tfAddress);

        //添加第4行组件（其他标签、其他文本框）
        //初始化其他标签
		JLabel lbOther = new JLabel("其他");
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 3;
		cons.insets = new Insets(10, 15, 10, 1);
		layout.setConstraints(lbOther, cons);
		pContent.add(lbOther);

        //其他文本框
		tfOther = new JTextField(30);
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridwidth = 3;
		cons.anchor = GridBagConstraints.WEST;
		cons.insets = new Insets(10, 1, 10, 10);
		layout.setConstraints(tfOther, cons);
		pContent.add(tfOther);

        //添加第5行组件（部门标签、部门下拉列表、增加按钮、清空按钮）
        //初始化部门标签
		JLabel lbDept = new JLabel("选择人员信息");
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 4;
		cons.insets = new Insets(10, 10, 10, 1);
		layout.setConstraints(lbDept, cons);
		pContent.add(lbDept);

		//部门下拉列表
		personDao = new PersonDaoImpl();
		String [] personStr = personDao.getNamesWithId();
		comboPerson = new JComboBox(personStr);
		cons = new GridBagConstraints();
		cons.gridx = 1;
		cons.gridy = 4;
		cons.insets = new Insets(10, 1, 10, 15);
		layout.setConstraints(comboPerson, cons);
		pContent.add(comboPerson);
        //初始化修改按钮
		btnUpdate = new JButton("修改");
		cons = new GridBagConstraints();
		cons.gridx = 2;
		cons.gridy = 4;
        //水平占两格
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 15, 10, 1);
		layout.setConstraints(btnUpdate, cons);
		pContent.add(btnUpdate);

        //初始化清空按钮
		btnClear = new JButton("清空");
		cons = new GridBagConstraints();
		cons.gridx = 3;
		cons.gridy = 4;
		cons.gridwidth = 1;
		cons.insets = new Insets(10, 1, 10, 10);
		layout.setConstraints(btnClear, cons);
		pContent.add(btnClear);
		this.tfPersonId.setEditable(false);
		this.tfName.setEditable(false);
		this.tfSex.setEditable(false);
		this.tfBirth.setEditable(false);
		this.tfNat.setEditable(false);
		this.tfAddress.setEditable(false);
		this.tfOther.setEditable(false);
		btnUpdate.setEnabled(false);
	}

	/**
	 * JComboBox<String> comboPerson; JButton btnUpdate; JButton btnClear;
	 * 
	 * @throws Exception
	 */
	public void addListener() throws Exception {
		btnUpdate.addActionListener(this);
		btnClear.addActionListener(this);
		comboPerson.addItemListener(this);
	}

	
	/**
	 * 	处理下拉列表鼠标触击
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
			String tempStr = (String) e.getItem();
			int i = tempStr.indexOf("-");
			personID = tempStr.substring(0, i);
			personDao = new PersonDaoImpl();
			try {
				person = personDao.getPersonById(Long.valueOf(personID));
				tfPersonId.setText(String.valueOf(person.getPersonId()));
				tfName.setText(person.getPname());
				tfSex.setText(person.getSex());
				tfBirth.setText(person.getBirth());
				tfNat.setText(person.getNat());
				tfAddress.setText(person.getAddress());
				DeptID = String.valueOf(person.getDeptId());
				Salary = person.getSalary();
				Assess = person.getAddress();
				tfOther.setText(person.getOther());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			tfName.setEditable(true);
			tfSex.setEditable(true);
			tfBirth.setEditable(true);
			tfNat.setEditable(true);
			tfAddress.setEditable(true);
			tfOther.setEditable(true);
			btnUpdate.setEnabled(true);
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Person p = null;
		if (obj == btnUpdate) {
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
				personDao = new PersonDaoImpl();
				personDao.update(p);
				JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
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
	 * 	清空函数调用
	 */
	void setNull() {
		tfName.setText(null);
		tfSex.setText(null);
		tfBirth.setText(null);
		tfNat.setText(null);
		tfAddress.setText(null);
		tfOther.setText(null);
		tfPersonId.setText("请输入人员编号");
		
		tfName.setEditable(false);
		tfSex.setEditable(false);
		tfBirth.setEditable(false);
		tfNat.setEditable(false);
		tfAddress.setEditable(false);
		tfOther.setEditable(false);
		btnUpdate.setEnabled(false);
	}
	/**
	 * 	处理页面上按钮的方法
	 */
}
