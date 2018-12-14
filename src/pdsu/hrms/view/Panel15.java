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
* @Description: ���Ź������
* @author ���³�
* @date 2018��12��13������5:35:05
*
*/
public class Panel15 extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	//�����������
	//�������
	private JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	
	//�����ϲ��������
	private JTable table;
	private JScrollPane js;
	
	//�����в��������
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
	 * 	��ʼ������
	 * @throws SQLException
	 */
	private void initTop() throws SQLException {
		//�����������
		pTop = new JPanel();
		//�������
		String[] colTitle = new String[] {"����","һ������","��������"};
		deptDao= new DeptDaoImpl();
		String[][] colValue = deptDao.getDeptsForTable();
		
		table = new JTable(colValue,colTitle);
		//������ѡ��
		//table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//������ѡ��
		//table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//ֻ��ѡһ��
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//��ȡѡ����
				int row = table.getSelectedRow();
				//����Ӧ��������ʾ���ı���
				tfDeptId.setText(colValue[row][0]);
				tfDept1Name.setText(colValue[row][1]);
				tfDept2Name.setText(colValue[row][2]);
				tfDept1Name.setEditable(true);
				tfDept2Name.setEditable(true);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
			}
		});
		//���ñ���Ĭ�ϴ�С
		table.setPreferredScrollableViewportSize(new Dimension(430, 300));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 300));
		pTop.add(js);
		
		add(pTop,BorderLayout.NORTH);
	}
	
	/**
	 * 	���췽��
	 * @throws SQLException
	 */
	public Panel15() throws SQLException{
		//����Ϊ�߽粼��
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		addListener();
	}
	
	/**
	 * 	��ʼ���м�
	 */
	private void initCenter() {
		pCenter = new JPanel();
		//�����
		lbDeptId = new JLabel("���ű��");
		tfDeptId = new JTextField(15);
		//һ��
		lbDept1Name = new JLabel("һ������");
		tfDept1Name = new JTextField(15);
		//����
		lbDept2Name = new JLabel("��������");
		tfDept2Name = new JTextField(15);
		
		pCenter.add(lbDeptId); 
		pCenter.add(tfDeptId);
		pCenter.add(lbDept1Name);
		pCenter.add(tfDept1Name);
		pCenter.add(lbDept2Name);
		pCenter.add(tfDept2Name);
		
		add(pCenter,BorderLayout.CENTER);
		
        //��ʼ��ʱ����Ϊ����״̬
		tfDeptId.setEditable(false);
		tfDept1Name.setEditable(false);
		tfDept2Name.setEditable(false);
	}
	
	/**
	 * 	��ʼ���ײ�
	 */
	private void initBottom() {
        //�½��ײ������
		pBottom = new JPanel();
		
        //�����ť���ֱ��ǣ���ȡ�±�ţ����ӣ��޸ģ�ɾ�������
		btnNextId = new JButton("��ȡ�±��");
		btnAdd = new JButton("����");
		btnUpdate = new JButton("�޸�");
		btnDelete = new JButton("ɾ��");
		btnbClear = new JButton("���");
		
        //����ť���������
		pBottom.add(btnNextId);
		pBottom.add(btnAdd);
		pBottom.add(btnUpdate);
		pBottom.add(btnDelete);
		pBottom.add(btnbClear);
		
        //��������߽粼�ֵĵײ�
		add(pBottom,BorderLayout.SOUTH);
		
		btnNextId.setEnabled(true);
		btnAdd.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnbClear.setEnabled(true);
	}
	
	/**
	 * 	������
	 */
	private void addListener() {
		btnNextId.addActionListener(this);
		btnAdd.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnbClear.addActionListener(this);
	}
	
	/**
	 *	 ����������ʱ��
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
				JOptionPane.showMessageDialog(null, "�ɹ����һ����¼", "��ܰ��ʾ",  JOptionPane.INFORMATION_MESSAGE);
				Panel15 panel15 = new Panel15();
				HrMain.splitPane.setRightComponent(panel15);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(obj == btnUpdate) {
			deptDao = new DeptDaoImpl();
			Dept dept = new Dept(Long.parseLong(tfDeptId.getText()), tfDept1Name.getText(), tfDept2Name.getText());
			try {
				JOptionPane.showMessageDialog(null, "�ɹ��޸�һ����¼", "��ܰ��ʾ",  JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "�ɹ�ɾ��һ����¼", "��ܰ��ʾ",  JOptionPane.INFORMATION_MESSAGE);
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
	 * 	��շ���
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
