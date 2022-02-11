package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class TeacherLAC extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton back = new JButton("返回");
	//标题
	private JLabel	title = new JLabel("列出所有课程");
	//数据
	private String msg = "1,Math;2,English";//这只是测试样例，真正的数据要从后端传过来的。
	private int num;
	public TeacherLAC(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（教师端）");
		this.setSize(500, 350);
		Client.send.get(num).str="9:"+Client.id.get(num);
		while(Client.receive.get(num).message=="") {
			msg=Client.receive.get(num).message;
		}
		msg=Client.receive.get(num).message;
		Client.receive.get(num).message="";
		//数据处理，添加表格
		if(msg == "-2") {
			JTextField jtf = new JTextField();
			jtf.setText("您没有任何课程！");
			jtf.setEditable(false);
			add(jtf);
			jtf.setBounds(10, 100, 400, 100);
		}else {
			String[] tmp = msg.split(";");
			JTable jt = new JTable(tmp.length+1,2);
			jt.setEnabled(false);//整个表格不可编辑
			
			jt.setValueAt("课程号", 0, 0);
			jt.setValueAt("课程名", 0, 1);
			
			for(int i=0;i<tmp.length;i++) {
				String[] stmp = tmp[i].split(",");
				jt.setValueAt(stmp[0], i+1, 0);
				jt.setValueAt(stmp[1], i+1, 1);
			}
			add(jt);
			jt.setBounds(10, 100, 400, 100);
		}
		
		contentPanel.setLayout(null);//设置为自由布局。
		
		add(back);
		add(title);
		
		back.setBounds(95, 275, 90, 20);
		title.setBounds(100, 30, 300, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		back.addActionListener(new ActionListener() {
			//返回教师课程界面。
			public void actionPerformed(ActionEvent e) {
				TeacherCourse.start(num);
				TeacherLAC.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new TeacherLAC(i).setVisible(true);
	}
}