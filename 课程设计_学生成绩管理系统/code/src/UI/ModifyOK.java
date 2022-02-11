package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class ModifyOK extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton ok = new JButton("确定");
	private List<JLabel> titles= new ArrayList<JLabel>();	
	private int num;
	public ModifyOK(int mode,int i) {
		this.num=i;
		this.init(mode);
		this.addListener(mode);
	}

	private void init(int mode) {
		//整体布局
		this.setTitle("学生成绩管理系统");
		this.setSize(500, 350);
		
		titles.add(new JLabel("修改个人信息成功！") );//添加语句
		titles.add(new JLabel("修改个人信息成功！") );
		titles.add(new JLabel("修改课程成绩成功！") );
		titles.add(new JLabel("删除课程成功！") );
		titles.add(new JLabel("添加课程成功！") );
		titles.add(new JLabel("修改课程信息成功！") );

		contentPanel.setLayout(null);//设置为自由布局。
		add(ok);
		add(titles.get(mode));
		
		ok.setBounds(200, 225, 90, 20);
		
		titles.get(mode).setBounds(100, 45, 300, 50);
		titles.get(mode).setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener(int mode) {
		ok.addActionListener(new ActionListener() {
			//返回相应界面。
			public void actionPerformed(ActionEvent e) {
				if(mode==0) {
					StudentOn.start(num);
				}
				if(mode==1) {
					TeacherOn.start(num);
				}
				if(mode==2) {
					TeacherGrade.start(num);
				}
				if(mode==3||mode==4||mode==5) {
					TeacherCourse.start(num);
				}
				ModifyOK.this.setVisible(false);
			}
		});
	}
	
	public static void start(int mode,int i) {
		new ModifyOK(mode,i).setVisible(true);
	}
}
