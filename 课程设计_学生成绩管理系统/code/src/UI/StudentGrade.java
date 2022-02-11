package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentGrade extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton qid = new JButton("按课程号查询"), lag = new JButton("列出所有成绩"), back = new JButton("返回");
	//标题
	private JLabel	title = new JLabel("成绩查询页面");
	private int num;
	public StudentGrade(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（学生端）");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(qid);
		add(lag);
		add(back);
		add(title);
		
		qid.setBounds(40, 225, 120, 20);
		lag.setBounds(180, 225, 120, 20);
		back.setBounds(320, 225, 120, 20);
		
		title.setBounds(180, 45, 250, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		qid.addActionListener(new ActionListener() {
			//跳转到按课程号查成绩界面。
			public void actionPerformed(ActionEvent e) {
				StudentQGid.start(num);
				StudentGrade.this.setVisible(false);
			}
		});
		lag.addActionListener(new ActionListener() {
			//跳转到列出所有成绩界面。
			public void actionPerformed(ActionEvent e) {
				StudentLAG.start(num);
				StudentGrade.this.setVisible(false);
			}
		});
		back.addActionListener(new ActionListener() {
			//返回用户界面。
			public void actionPerformed(ActionEvent e) {
				StudentOn.start(num);
				StudentGrade.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new StudentGrade(i).setVisible(true);
	}
}
