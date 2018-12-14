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
 * @Description: 主界面
 * @author 李勇超
 * @date 2018年12月12日下午1:26:13
 *
 */
public class HrMain extends JFrame implements TreeSelectionListener {

	private static final long serialVersionUID = 1L;

    //树菜单
	public JTree tree;
	private DefaultMutableTreeNode root;  //树的根节点
    //定义分割面板
	public static JSplitPane splitPane;
	
	//定义欢迎面板
	private JPanel pWelcome;
	
	public HrMain() throws Exception {
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭时退出程序
		setSize(700, 450);  //设置大小
		setLocationRelativeTo(null);  //相对屏幕居中
		setResizable(false);  //大小不可变
		setTitle("人事管理系统"); //设置标题

        //设置窗口图标
		//1.找到文件位置
		URL url = getClass().getClassLoader().getResource("db/welcome.jpg");
		//2.加载图片到内存
		Image image = Toolkit.getDefaultToolkit().getImage(url);
		//3.把图片设置为窗口图标
		setIconImage(image);
		
		init();   //调用初始化方法
		setVisible(true);  //设置窗口是否可见
	}

	public void init() throws Exception {

        //创建根节点
		root = new DefaultMutableTreeNode("人事管理系统");
        //创建父节点
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("基本信息管理");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("人员调动管理");
		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("人员考核管理");
		DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("劳资管理");
		node1.add(new DefaultMutableTreeNode("添加人员信息"));
		node1.add(new DefaultMutableTreeNode("修改人员信息"));
		node1.add(new DefaultMutableTreeNode("删除人员信息"));
		node1.add(new DefaultMutableTreeNode("查询人员信息"));
		node1.add(new DefaultMutableTreeNode("部门管理"));
		node2.add(new DefaultMutableTreeNode("人员调动"));
		node2.add(new DefaultMutableTreeNode("调动历史查询"));
		node3.add(new DefaultMutableTreeNode("人员考核"));
		node3.add(new DefaultMutableTreeNode("考核历史查询"));
		node4.add(new DefaultMutableTreeNode("劳资分配管理"));
		node4.add(new DefaultMutableTreeNode("劳资历史查询"));
		root.add(node1);
		root.add(node2);
		root.add(node3);
		root.add(node4);

        //把树菜单添加到窗口中
		tree = new JTree(root);
        //添加对当前页面的模块监听
		tree.addTreeSelectionListener(this);

        //设置窗格为垂直拆分（垂直方向上的拆分，即与y轴平行的拆分），水平拆分为（水平方向上的拆分，即与x轴平行的拆分 VERTICAL_SOLIT）
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //设置分隔条的位置（不合适可调整）
		splitPane.setDividerLocation(168);
        //设置分隔条连续变化（即当鼠标拉动分隔条时不会出现分隔条留在原地，等待鼠标将分隔条拖到指定位置时消失）
		splitPane.setContinuousLayout(true);
        //用整数类型的像素（2）表示分隔符的宽度
		splitPane.setDividerSize(2);

        //创建面板，并将其加入到左半部分中（导航栏）
		JPanel pleft = new JPanel();
		JScrollPane js = new JScrollPane(tree);
		js.setPreferredSize(new Dimension(150, 400));
		pleft.add(js);
		splitPane.setLeftComponent(pleft);

        //创建面板，并将其加入到右半部分中（欢迎界面）
		pWelcome = new JPanel();
		JLabel label = new JLabel("欢迎您使用人事管理系统");
		label.setFont(new Font("宋体", Font.PLAIN, 29));
		pWelcome.add(label);
		splitPane.setRightComponent(pWelcome);

        //分割面板加入到界面中
		add(splitPane);
	}

    //对树菜单进行事件处理
	@Override
	public void valueChanged(TreeSelectionEvent e) {

        //获取鼠标单击的内容字符串执行循环
		String menustr = e.getPath().getLastPathComponent().toString();
		switch (menustr) {
		//点击根节点
		case "人事管理系统":
			splitPane.setRightComponent(pWelcome);
			break;
		//点击一级菜单（展开或折叠菜单项）
		case "基本信息管理":
		case "人员调动管理":
		case "人员考核管理":
		case "劳资管理":
			// 1.获取当前项的路径
			TreePath path = e.getPath();
			// 2.如果此路径是展开状态，则将其改为折叠状态
			if (tree.isExpanded(path)) {
				tree.collapsePath(path);
			} else {
				tree.expandPath(path);
			}
			break;
		case "添加人员信息":
			try {
				splitPane.setRightComponent(new Panel11());
			} catch (Exception e4) {
				e4.printStackTrace();
			}
			break;
		case "修改人员信息":
			try {
				splitPane.setRightComponent(new Panel12());
			} catch (Exception e4) {
				e4.printStackTrace();
			}
			break;
		case "删除人员信息":
			try {
				splitPane.setRightComponent(new Panel13());
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			break;
		case "查询人员信息":
			try {
				splitPane.setRightComponent(new Panel14());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "部门管理":
			try {
				splitPane.setRightComponent(new Panel15());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "人员调动":
			try {
				splitPane.setRightComponent(new Panel21());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "调动历史查询":
			try {
				splitPane.setRightComponent(new Panel22());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "人员考核":
			try {
				splitPane.setRightComponent(new Panel31());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			break;
		case "考核历史查询":
			try {
				splitPane.setRightComponent(new Panel32());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "劳资分配管理":
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
        //改变初始时树的节点的图标（从系统获取）
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //调用方法
		new HrMain();
	}
}
