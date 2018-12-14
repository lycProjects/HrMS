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
* @Description: ���ʷ���������
* @author ���³�
* @date 2018��12��14������1:38:49
*
*/
public class Panel41 extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
    //������͹������
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
	 * ������
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
	 * ��ʼ������
	 * @throws SQLException
	 */
	private void initTop() throws SQLException {
        //�������
		pTop = new JPanel();
        //�������ַ�ʽ�����������
		GridBagLayout layout = new GridBagLayout();
		pTop.setLayout(layout);
		GridBagConstraints cons = null;
		
        //��ӱ��⣨������Ա��Ϣ���������������������ʽ
		JLabel lbTitle = new JLabel("���ʷ���");
		lbTitle.setFont(new Font("����",0,16));
		
        //������������趨λ�á���С�ͼ��Ȳ����������λ�ã�
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //�Ѳ�������������������ص㣩
		layout.setConstraints(lbTitle, cons);
        //�����ӵ�������
		pTop.add(lbTitle);
		
        //��Ա��������Ĳ�������
		personDao = new PersonDaoImpl();
		String[] colTitle = new String[] {"����","����","�Ա�","����","н��","������Ϣ"};
		String[][] colValue = personDao.getAllForHistory();
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
                //��ȡѡ����
				int row = table.getSelectedRow();
                //����Ӧ��������ʾ���ı���
				tfName.setText(colValue[row][1]);
				tfOldSalary.setText(colValue[row][4]);
				//�������Ϣ���ʱ��ɾ����ť�ɲ���
				btnConfirm.setEnabled(true);
			}
		});
        //���ñ���Ĭ�ϴ�С
		table.setPreferredScrollableViewportSize(new Dimension(430, 300));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 300));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		layout.setConstraints(js, cons);
		pTop.add(js);
        //�����ӵ�������
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
		lbOldSalary = new JLabel("����ǰ����");
		tfOldSalary = new JTextField(15);
		lbNewSalary = new JLabel("��������");
		tfNewSalary = new JTextField(15);
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbOldSalary);
		pCenter.add(tfOldSalary);
		pCenter.add(lbNewSalary);
		pCenter.add(tfNewSalary);
		add(pCenter,BorderLayout.CENTER);
		
		//��ʼ��ʱ���ı��򲻿��޸�
		tfName.setEditable(false);
		tfOldSalary.setEditable(false);
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
				Long f6 = (long) historyDao.getChgCount(Long.parseLong(PersonID),"���ʷ���");
				History h = new History(Long.parseLong(f1), "���ʷ���", tfOldSalary.getText(), tfNewSalary.getText(), f5, f6, Long.parseLong(PersonID));
				historyDao.add(h);
				JOptionPane.showMessageDialog(null, "н�ʵ����ɹ�", "��ܰ��ʾ",  JOptionPane.INFORMATION_MESSAGE);
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
	 * 	��շ���
	 */
	private void setNull()
	{
		tfName.setText(null);
		tfOldSalary.setText(null);
		btnClear.setEnabled(true);
	}
}
