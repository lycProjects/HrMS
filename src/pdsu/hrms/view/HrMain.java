package pdsu.hrms.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * @ClassName: HrMain
 * @Description: ������
 * @author ���³�
 * @date 2018��12��12������1:26:13
 *
 */
public class HrMain extends JFrame implements TreeSelectionListener {

	private static final long serialVersionUID = 1L;

    //���˵�
	public JTree tree;
	private DefaultMutableTreeNode root;  //���ĸ��ڵ�
    //����ָ����
	public static JSplitPane splitPane;
	
	//���延ӭ���
	private JPanel pWelcome;
	
	public HrMain() throws Exception {
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�ر�ʱ�˳�����
		setSize(700, 450);  //���ô�С
		setLocationRelativeTo(null);  //�����Ļ����
		setResizable(false);  //��С���ɱ�
		setTitle("���¹���ϵͳ"); //���ñ���

        //���ô���ͼ��
		//1.�ҵ��ļ�λ��
		URL url = getClass().getClassLoader().getResource("db/welcome.jpg");
		//2.����ͼƬ���ڴ�
		Image image = Toolkit.getDefaultToolkit().getImage(url);
		//3.��ͼƬ����Ϊ����ͼ��
		setIconImage(image);
		
		init();   //���ó�ʼ������
		setVisible(true);  //���ô����Ƿ�ɼ�
	}

	public void init() throws Exception {

        //�������ڵ�
		root = new DefaultMutableTreeNode("���¹���ϵͳ");
        //�������ڵ�
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("������Ϣ����");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("��Ա��������");
		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("��Ա���˹���");
		DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("���ʹ���");
		node1.add(new DefaultMutableTreeNode("�����Ա��Ϣ"));
		node1.add(new DefaultMutableTreeNode("�޸���Ա��Ϣ"));
		node1.add(new DefaultMutableTreeNode("ɾ����Ա��Ϣ"));
		node1.add(new DefaultMutableTreeNode("��ѯ��Ա��Ϣ"));
		node1.add(new DefaultMutableTreeNode("���Ź���"));
		node2.add(new DefaultMutableTreeNode("��Ա����"));
		node2.add(new DefaultMutableTreeNode("������ʷ��ѯ"));
		node3.add(new DefaultMutableTreeNode("��Ա����"));
		node3.add(new DefaultMutableTreeNode("������ʷ��ѯ"));
		node4.add(new DefaultMutableTreeNode("���ʷ������"));
		node4.add(new DefaultMutableTreeNode("������ʷ��ѯ"));
		root.add(node1);
		root.add(node2);
		root.add(node3);
		root.add(node4);

        //�����˵���ӵ�������
		tree = new JTree(root);
        //��ӶԵ�ǰҳ���ģ�����
		tree.addTreeSelectionListener(this);

        //���ô���Ϊ��ֱ��֣���ֱ�����ϵĲ�֣�����y��ƽ�еĲ�֣���ˮƽ���Ϊ��ˮƽ�����ϵĲ�֣�����x��ƽ�еĲ�� VERTICAL_SOLIT��
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //���÷ָ�����λ�ã������ʿɵ�����
		splitPane.setDividerLocation(168);
        //���÷ָ��������仯��������������ָ���ʱ������ַָ�������ԭ�أ��ȴ���꽫�ָ����ϵ�ָ��λ��ʱ��ʧ��
		splitPane.setContinuousLayout(true);
        //���������͵����أ�2����ʾ�ָ����Ŀ��
		splitPane.setDividerSize(2);

        //������壬��������뵽��벿���У���������
		JPanel pleft = new JPanel();
		JScrollPane js = new JScrollPane(tree);
		js.setPreferredSize(new Dimension(150, 400));
		pleft.add(js);
		splitPane.setLeftComponent(pleft);

        //������壬��������뵽�Ұ벿���У���ӭ���棩
		pWelcome = new JPanel();
		JLabel label = new JLabel("��ӭ��ʹ�����¹���ϵͳ");
		label.setFont(new Font("����", Font.PLAIN, 29));
		pWelcome.add(label);
		splitPane.setRightComponent(pWelcome);

        //�ָ������뵽������
		add(splitPane);
	}

    //�����˵������¼�����
	@Override
	public void valueChanged(TreeSelectionEvent e) {

        //��ȡ��굥���������ַ���ִ��ѭ��
		String menustr = e.getPath().getLastPathComponent().toString();
		switch (menustr) {
		//������ڵ�
		case "���¹���ϵͳ":
			splitPane.setRightComponent(pWelcome);
			break;
		//���һ���˵���չ�����۵��˵��
		case "������Ϣ����":
		case "��Ա��������":
		case "��Ա���˹���":
		case "���ʹ���":
			// 1.��ȡ��ǰ���·��
			TreePath path = e.getPath();
			// 2.�����·����չ��״̬�������Ϊ�۵�״̬
			if (tree.isExpanded(path)) {
				tree.collapsePath(path);
			} else {
				tree.expandPath(path);
			}
			break;
		case "�����Ա��Ϣ":
			try {
				splitPane.setRightComponent(new Panel11());
			} catch (Exception e4) {
				e4.printStackTrace();
			}
			break;
		case "�޸���Ա��Ϣ":
			try {
				splitPane.setRightComponent(new Panel12());
			} catch (Exception e4) {
				e4.printStackTrace();
			}
			break;
		case "ɾ����Ա��Ϣ":
			try {
				splitPane.setRightComponent(new Panel13());
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			break;
		case "��ѯ��Ա��Ϣ":
			try {
				splitPane.setRightComponent(new Panel14());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "���Ź���":
			try {
				splitPane.setRightComponent(new Panel15());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "��Ա����":
			try {
				splitPane.setRightComponent(new Panel21());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "������ʷ��ѯ":
			try {
				splitPane.setRightComponent(new Panel22());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "��Ա����":
			try {
				splitPane.setRightComponent(new Panel31());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "������ʷ��ѯ":
			try {
				splitPane.setRightComponent(new Panel32());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "���ʷ������":
			try {
				splitPane.setRightComponent(new Panel41());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			break;
		default:
			try {
				splitPane.setRightComponent(new Panel42());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		}
	}

	public static void main(String[] args) throws Exception {
        //�ı��ʼʱ���Ľڵ��ͼ�꣨��ϵͳ��ȡ��
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //���÷���
		new HrMain();
	}
}
