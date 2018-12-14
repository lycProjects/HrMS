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
* @Description: ɾ����Ա��Ϣ
* @author ���³�
* @date 2018��12��13������1:29:31
*
*/
public class Panel13 extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
    //������͹������
	private JTable table;
	private JScrollPane js;
	
    //����BorderLayout�ı����У���������
	private JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	
    //��ǩ����ţ����������ţ�
	private JLabel lbPersonId;
	private JLabel lbName;
	private JLabel lbDept;
	
    //�ı��򣨱�ţ����������ţ�
	private JTextField tfPersonId;
	private JTextField tfName;
	private JTextField tfDept;
	
    //ɾ����ť
	private JButton btnDetele;
	PersonDao personDao = null;
	HistoryDao historyDao = null;
	/**
	 * 	����������������Ϊ�߽粼�֣������������ֵĳ�ʼ����
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
	 * 	��ʼ����������
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
		JLabel lbTitle = new JLabel("��Ա��Ϣɾ��");
		lbTitle.setFont(new Font("����",0,16));
		
        //������������趨λ�á���С�ͼ��Ȳ����������λ�ã�
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //�Ѳ�������������������ص㣩
		layout.setConstraints(lbTitle, cons);
        //�����ӵ�������
		pTop.add(lbTitle);
		
		personDao = new PersonDaoImpl();
		String[] colTitle = new String[] {"���","����","��������","����","��ַ","����"};
		String[][] colValue = personDao.getAllForBasic();
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
                //��ȡѡ����
				int row = table.getSelectedRow();
                //����Ӧ��������ʾ���ı���
				tfPersonId.setText(colValue[row][0]);
				tfName.setText(colValue[row][1]);
				tfDept.setText(colValue[row][5]);
				//�������Ϣ���ʱ��ɾ����ť�ɲ���
				btnDetele.setEnabled(true);
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
	 * 	��ʼ���в�����
	 */
	private void initCenter() {
		pCenter = new JPanel();
        //��� ���������������ţ�
		lbPersonId = new JLabel("���");
		tfPersonId = new JTextField(15);
		lbName = new JLabel("����");
		tfName = new JTextField(15);
		lbDept = new JLabel("����");
		tfDept = new JTextField(15);
		
		pCenter.add(lbPersonId); 
		pCenter.add(tfPersonId);
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbDept);
		pCenter.add(tfDept);
		
		add(pCenter,BorderLayout.CENTER);
		
        //��ʼ��ʱ���ı��򲻿��޸�
		tfPersonId.setEditable(false);
		tfName.setEditable(false);
		tfDept.setEditable(false);
	}
	
	/**
	 * 	��ʼ���ײ�����
	 */
	private void initBottom() {
		pBottom = new JPanel();
		btnDetele = new JButton("ɾ��");
		pBottom.add(btnDetele);
		add(pBottom,BorderLayout.SOUTH);
		
        //��ʼ��ʱ����ɾ����ť���ɲ���
		btnDetele.setEnabled(false);
	}
	
	
	
	/**
	 * ����ɾ����ťΪ�����¼�
	 */
	private void addListener() {
		btnDetele.addActionListener(this);
	}
	
	/**
	 * Ҫ������ѡ��¼�Ƿ��ɾ�������е�����ʷ�Ĳ���ɾ����
	 * ���ð�ť�¼���Ӧ
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
					JOptionPane.showMessageDialog(null, "������ɾ����Ա��", "��ܰ��ʾ", JOptionPane.ERROR_MESSAGE);
				}else {
					personDao.delete(personId);
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ܰ��ʾ", JOptionPane.INFORMATION_MESSAGE);
					Panel13 panel13 = new Panel13();
					HrMain.splitPane.setRightComponent(panel13);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
