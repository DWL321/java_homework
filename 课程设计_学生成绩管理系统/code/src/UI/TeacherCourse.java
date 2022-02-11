package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeacherCourse extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton lac = new JButton("列出所有课程"), qid = new JButton("按课程号查询"), addc = new JButton("增加课程"),rmc = new JButton("删除课程"),mc = new JButton("按课程号修改"),back = new JButton("返回");
	//标题
	private JLabel	title = new JLabel("课程管理页面");
	private int num;
	public TeacherCourse(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（教师端）");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(lac);
		add(qid);
		add(addc);
		add(rmc);
		add(mc);
		add(back);
		add(title);
		
		lac.setBounds(40, 190, 120, 20);
		qid.setBounds(180, 190, 120, 20);
		addc.setBounds(320, 190, 120, 20);
		rmc.setBounds(40, 225, 120, 20);
		mc.setBounds(180, 225, 120, 20);
		back.setBounds(320, 225, 120, 20);
		
		title.setBounds(180, 45, 250, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		lac.addActionListener(new ActionListener() {
			//跳转到列出所有课程界面。
			public void actionPerformed(ActionEvent e) {
				TeacherLAC.start(num);
				TeacherCourse.this.setVisible(false);
			}
		});
		qid.addActionListener(new ActionListener() {
			//跳转到按课程号查课程界面。
			public void actionPerformed(ActionEvent e) {
				TeacherQCid.start(num);
				TeacherCourse.this.setVisible(false);
			}
		});
		addc.addActionListener(new ActionListener() {
			//跳转到加课界面。
			public void actionPerformed(ActionEvent e) {
				TeacherAddC.start(num);
				TeacherCourse.this.setVisible(false);
			}
		});
		rmc.addActionListener(new ActionListener() {
			//跳转到删课界面。
			public void actionPerformed(ActionEvent e) {
				TeacherRemoveC.start(num);
				TeacherCourse.this.setVisible(false);
			}
		});
		mc.addActionListener(new ActionListener() {
			//跳转到按课程号修改课程信息界面。
			public void actionPerformed(ActionEvent e) {
				TeacherModifyC.start(num);
				TeacherCourse.this.setVisible(false);
			}
		});
		back.addActionListener(new ActionListener() {
			//返回用户界面。
			public void actionPerformed(ActionEvent e) {
				TeacherOn.start(num);
				TeacherCourse.this.setVisible(false);
			}
		});
	}

	
	public static void start(int i) {
		new TeacherCourse(i).setVisible(true);
	}
}
