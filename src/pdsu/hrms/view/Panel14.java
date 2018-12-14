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
* @Description: 查询人员信息界面
* @author 李勇超
* @date 2018年12月13日下午3:33:23
*
*/
public class Panel14 extends JPanel{
	private static final long serialVersionUID = 1L;
	
    //定义表格和滚动面板
	private JTable table;
	private JScrollPane js;
	
	private JPanel pContent;
	PersonDao personDao = null;
	private void initContent() throws SQLException {
        //声明面板
		pContent = new JPanel();
        //声明布局方式：网格包布局
		GridBagLayout layout = new GridBagLayout();
		pContent.setLayout(layout);
		GridBagConstraints cons = null;
		
        //添加标题（增加人员信息）创建组件并设置字体样式
		JLabel lbTitle = new JLabel("人员信息查询");
		lbTitle.setFont(new Font("宋体",0,16));
		
        //用网格包布局设定位置、大小和间距等参数（组件的位置）
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
        //把参数和组件关联起来（重点）
		layout.setConstraints(lbTitle, cons);
        //组件添加到容器中
		pContent.add(lbTitle);
		
		personDao = new PersonDaoImpl();
		String[] colTitle = new String[] {"编号","姓名","出生年月","民族","地址","部门"};
		String[][] colValue = personDao.getAllForBasic();
		table = new JTable(colValue,colTitle);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //设置表格的默认大小
		table.setPreferredScrollableViewportSize(new Dimension(430, 380));
		js = new JScrollPane(table);
		js.setPreferredSize(new Dimension(480, 380));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		layout.setConstraints(js, cons);
		pContent.add(js);
        //组件添加到容器中
		add(pContent,BorderLayout.NORTH);
	}
	
	/**
	 * 	主方法：设置整体为边界布局，并调用三部分的初始方法
	 * @throws SQLException 
	 */
	public Panel14() throws SQLException {
		setLayout(new BorderLayout());
		initContent();
	}
	
}
