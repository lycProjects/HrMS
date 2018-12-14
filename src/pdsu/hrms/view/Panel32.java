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

import pdsu.hrms.dao.HistoryDao;
import pdsu.hrms.daoImpl.HistoryDaoImpl;

/**
* @ClassName: Panel32
* @Description: ������ʷ��ѯ����
* @author ���³�
* @date 2018��12��14������10:58:23
*
*/
public class Panel32 extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane js;
	private JPanel pContent;
	HistoryDao historyDao = null;
	/**
	 * 	����������������Ϊ�߽粼�֣������������ֵĳ�ʼ����
	 * @throws SQLException 
	 */
	public Panel32() throws SQLException {
		setLayout(new BorderLayout());
		initContent();
	}
	
	/**
	 * ��ʼ����������
	 * @throws SQLException
	 */
	private void initContent() throws SQLException {
		pContent = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		pContent.setLayout(layout);
		GridBagConstraints cons = null;
		JLabel lbTitle = new JLabel("��Ա������ʷ��ѯ��ѯ");
		lbTitle.setFont(new Font("����",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		layout.setConstraints(lbTitle, cons);
		pContent.add(lbTitle);
		String[] colTitle = new String[] {"��ˮ��","����","�ϴο���","���ο���","�������","�������"};
		historyDao = new HistoryDaoImpl();
		String[][] colValue = historyDao.getAllByType("��Ա����");
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(430, 380));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 380));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		layout.setConstraints(js, cons);
		pContent.add(js);
		add(pContent,BorderLayout.NORTH);
	}
}
