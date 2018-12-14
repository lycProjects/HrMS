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
* @Description: ��Ա���˽���
* @author ���³�
* @date 2018��12��13������8:37:48
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
	String NowAssess = "δ���";
	String PersonID = null;
	History history = null;
	HistoryDao historyDao = null;
	PersonDao personDao = null;
	
	/**
	 * ������
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
	 * ��ʼ��������Ϣ
	 * @throws SQLException
	 */
	private void initTop() throws SQLException {
		pTop = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		pTop.setLayout(layout);
		GridBagConstraints cons = null;
		JLabel lbTitle = new JLabel("��Ա����");
		lbTitle.setFont(new Font("����",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		layout.setConstraints(lbTitle, cons);
		pTop.add(lbTitle);
		personDao = new PersonDaoImpl();
		String[] colTitle = new String[] {"����","����","�Ա�","����","н��","������Ϣ"};
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
	 * ��ʼ���м���Ϣ
	 */
	private void initCenter() {
		pCenter = new JPanel();
		//��� ��������ԭ���ţ��²��ţ�
		lbName = new JLabel("����");
		tfName = new JTextField(15);
		lbOldAssess = new JLabel("�ϴο���");
		tfOldAssess = new JTextField(15);
		lbNowAssess = new JLabel("���ο���");
		String []deptMessage = new String[] {"����","�ϸ�","���ϸ�"};
		comboAssess = new JComboBox<String>(deptMessage);
		
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbOldAssess);
		pCenter.add(tfOldAssess);
		pCenter.add(lbNowAssess);
		pCenter.add(comboAssess);
		
		add(pCenter,BorderLayout.CENTER);
		
		//��ʼ��ʱ���ı��򲻿��޸�
		tfName.setEditable(false);
		tfOldAssess.setEditable(false);
		comboAssess.setEnabled(false);
	}
	
	/**
	 * ��ʼ���ײ���Ϣ
	 */
	private void initBottom() {
		pBottom = new JPanel();
		btnConfirm = new JButton("ȷ��");
		btnClear = new JButton("���");
		pBottom.add(btnConfirm);
		pBottom.add(btnClear);
		add(pBottom,BorderLayout.SOUTH);
		
		//��ʼ��ʱ����ɾ����ť���ɲ���
		btnConfirm.setEnabled(false);
	}
	
	/**
	 * ���ؼ���ӵ��¼�����
	 */
	private void addListener() {
		btnConfirm.addActionListener(this);
		btnClear.addActionListener(this);
		comboAssess.addItemListener(this);
	}

	/**
	 * ��ȡ�����б���¿�����Ϣ
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
			NowAssess = (String) e.getItem();
		}
	}
	
	/**
	 * �¼�������
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
				Long f6 = (long) historyDao.getChgCount(Long.parseLong(PersonID),"��Ա����");
				History h = new History(Long.parseLong(f1), "��Ա����", OldAssess, NowAssess, f5, f6, Long.parseLong(PersonID));
				historyDao.add(h);
				JOptionPane.showMessageDialog(null, "��Ա���˳ɹ�", "��ܰ��ʾ",  JOptionPane.INFORMATION_MESSAGE);
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
	 * 	��շ���
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
