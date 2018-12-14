package pdsu.hrms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import pdsu.hrms.dao.PersonDao;
import pdsu.hrms.daoImpl.PersonDaoImpl;

/**
* @ClassName: Panel14
* @Description: ��ѯ��Ա��Ϣ����
* @author ���³�
* @date 2018��12��13������3:33:23
*
*/
public class Panel14 extends JPanel{
	private static final long serialVersionUID = 1L;
	
    //������͹������
	private JTable table;
	private JScrollPane js;
	
	private JPanel pContent;
	PersonDao personDao = null;
	private void initContent() throws SQLException {
        //�������
		pContent = new JPanel();
        //�������ַ�ʽ�����������
		GridBagLayout layout = new GridBagLayout();
		pContent.setLayout(layout);
		GridBagConstraints cons = null;
		
        //��ӱ��⣨������Ա��Ϣ���������������������ʽ
		JLabel lbTitle = new JLabel("��Ա��Ϣ��ѯ");
		lbTitle.setFont(new Font("����",0,16));
		
        //������������趨λ�á���С�ͼ��Ȳ����������λ�ã�
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //�Ѳ�������������������ص㣩
		layout.setConstraints(lbTitle, cons);
        //�����ӵ�������
		pContent.add(lbTitle);
		
		personDao = new PersonDaoImpl();
		String[] colTitle = new String[] {"���","����","��������","����","��ַ","����"};
		String[][] colValue = personDao.getAllForBasic();
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //���ñ���Ĭ�ϴ�С
		table.setPreferredScrollableViewportSize(new Dimension(430, 380));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 380));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		layout.setConstraints(js, cons);
		pContent.add(js);
        //�����ӵ�������
		add(pContent,BorderLayout.NORTH);
	}
	
	/**
	 * 	����������������Ϊ�߽粼�֣������������ֵĳ�ʼ����
	 * @throws SQLException 
	 */
	public Panel14() throws SQLException {
		setLayout(new BorderLayout());
		initContent();
	}
	
}
