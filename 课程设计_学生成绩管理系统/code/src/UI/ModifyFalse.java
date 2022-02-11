package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ModifyFalse extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton ok = new JButton("确定");
	private List<JLabel> titles= new ArrayList<JLabel>();	
	private JLabel jlb = new JLabel("错误原因: ");
	private JTextArea jtf = new JTextArea();

	public ModifyFalse(int mode,String errmsg) {
		this.init(mode,errmsg);
		this.addListener(mode);
	}

	private void init(int mode,String errmsg) {
		//整体布局
		this.setTitle("学生成绩管理系统");
		this.setSize(500, 350);
		
		titles.add(new JLabel("修改个人信息失败！") );//添加语句
		titles.add(new JLabel("修改个人信息失败！") );
		titles.add(new JLabel("修改课程成绩失败！") );
		titles.add(new JLabel("删除课程失败！") );
		titles.add(new JLabel("添加课程失败！") );
		titles.add(new JLabel("修改课程信息失败！") );
		
		contentPanel.setLayout(null);//设置为自由布局。
		add(ok);
		add(titles.get(mode));
		add(jlb);
		add(jtf);
		
		jtf.setText(errmsg);
		
		ok.setBounds(200, 225, 90, 20);
		
		titles.get(mode).setBounds(100, 20, 300, 50);
		titles.get(mode).setFont(new Font("微软雅黑", Font.BOLD, 30));
		
		jlb.setBounds(40, 85, 100, 25);
		jtf.setBounds(95, 85, 300, 100);
		jtf.setEditable(false);

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener(int mode) {
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyFalse.this.setVisible(false);
			}
		});
	}
	
	public static void start(int mode,String errmsg) {
		new ModifyFalse(mode,errmsg).setVisible(true);
	}
}
