package UI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SystemOn extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton login = new JButton("登录"), regist = new JButton("注册"), out = new JButton("退出");
	//标题
	private JLabel	title = new JLabel("欢迎来到学生成绩管理系统");
	private int num=0;
	public SystemOn(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(login);
		add(regist);
		add(out);
		add(title);
		
		login.setBounds(95, 225, 90, 20);
		regist.setBounds(200, 225, 90, 20);
		out.setBounds(315, 225, 90, 20);
		
		title.setBounds(55, 45, 400, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		login.addActionListener(new ActionListener() {
			//跳转到登录选择界面。
			public void actionPerformed(ActionEvent e) {
				LoginChoose.start(num);
				SystemOn.this.setVisible(false);
			}
		});
		regist.addActionListener(new ActionListener() {
			//跳转到注册选择界面。
			public void actionPerformed(ActionEvent e) {
				RegistChoose.start(num);
				SystemOn.this.setVisible(false);
			}
		});
		out.addActionListener(new ActionListener() {
			//退出系统，通知后端保存数据（未做）。
			public synchronized void actionPerformed(ActionEvent e) {
				Client.send.get(num).str="quit";
				System.exit(0);
			}
		});
	}
	
	public static void start(int i) {
		new SystemOn(i).setVisible(true);
	}
}
