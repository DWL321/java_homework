package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentOn extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton modifypm = new JButton("修改个人信息"), grade = new JButton("成绩查询"), out = new JButton("登出");
	//标题
	private JLabel	title = new JLabel(Client.id + "  "+Client.name);//需要确定是哪个用户登录了。
	private int num;
	public StudentOn(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（学生端）");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(modifypm);
		add(grade);
		add(out);
		add(title);
		
		modifypm.setBounds(65, 225, 120, 20);
		grade.setBounds(200, 225, 90, 20);
		out.setBounds(315, 225, 90, 20);
		
		title.setBounds(50, 45, 400, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		modifypm.addActionListener(new ActionListener() {
			//跳转到修改个人信息界面。
			public void actionPerformed(ActionEvent e) {
				StudentModifyPM.start(num);
				StudentOn.this.setVisible(false);
			}
		});
		grade.addActionListener(new ActionListener() {
			//跳转到查询成绩界面。
			public void actionPerformed(ActionEvent e) {
				StudentGrade.start(num);
				StudentOn.this.setVisible(false);
			}
		});
		out.addActionListener(new ActionListener() {
			//退出账号，
			public void actionPerformed(ActionEvent e) {
				SystemOn.start(num);
				StudentOn.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new StudentOn(i).setVisible(true);
	}
}
