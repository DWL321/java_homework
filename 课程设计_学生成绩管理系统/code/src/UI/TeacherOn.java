package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TeacherOn extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton modifypm = new JButton("修改个人信息"), course = new JButton("课程管理"), grade = new JButton("成绩管理"), out = new JButton("登出");
	//标题
	private JLabel	title = new JLabel(Client.id + "  "+Client.name);//需要确定是哪个用户登录了。
	private int num;
	public TeacherOn(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（教师端）");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(modifypm);
		add(course);
		add(grade);
		add(out);
		add(title);
		
		modifypm.setBounds(10, 225, 120, 20);
		course.setBounds(140, 225, 90, 20);
		grade.setBounds(240, 225, 90, 20);
		out.setBounds(340, 225, 90, 20);
		
		title.setBounds(50, 45, 400, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		modifypm.addActionListener(new ActionListener() {
			//跳转到修改个人信息界面。
			public void actionPerformed(ActionEvent e) {
				TeacherModifyPM.start(num);
				TeacherOn.this.setVisible(false);
			}
		});
		course.addActionListener(new ActionListener() {
			//跳转到查询成绩界面。
			public void actionPerformed(ActionEvent e) {
				TeacherCourse.start(num);
				TeacherOn.this.setVisible(false);
			}
		});
		grade.addActionListener(new ActionListener() {
			//跳转到查询成绩界面。
			public void actionPerformed(ActionEvent e) {
				TeacherGrade.start(num);
				TeacherOn.this.setVisible(false);
			}
		});
		out.addActionListener(new ActionListener() {
			//退出账号，通知后端保存数据（未做）。
			public void actionPerformed(ActionEvent e) {
				SystemOn.start(num);
				TeacherOn.this.setVisible(false);
			}
		});
	}
	public static void start(int i) {
		new TeacherOn(i).setVisible(true);
	}
}
