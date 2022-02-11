package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginChoose extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton slog = new JButton("学生登录"), tlog = new JButton("教师登录"), out = new JButton("返回");
	//标题
	private JLabel title = new JLabel("请选择身份");
	private int num=0;
	public LoginChoose(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(slog);
		add(tlog);
		add(out);
		add(title);
		
		slog.setBounds(95, 225, 90, 20);
		tlog.setBounds(200, 225, 90, 20);
		out.setBounds(315, 225, 90, 20);
		
		title.setBounds(180, 45, 250, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		slog.addActionListener(new ActionListener() {
			//跳转到学生登录界面。
			public void actionPerformed(ActionEvent e) {
				LoginStudent.start(num);
				LoginChoose.this.setVisible(false);
			}
		});
		tlog.addActionListener(new ActionListener() {
			//跳转到教师登录界面。
			public void actionPerformed(ActionEvent e) {
				LoginTeacher.start(num);
				LoginChoose.this.setVisible(false);
			}
		});
		out.addActionListener(new ActionListener() {
			//返回初始界面。
			public void actionPerformed(ActionEvent e) {
				SystemOn.start(num);
				LoginChoose.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new LoginChoose(i).setVisible(true);
	}
}
