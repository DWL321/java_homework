package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class TeacherRemoveC extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton back = new JButton("返回"), go = new JButton("删除");
	//标题
	private JLabel	title = new JLabel("按课程号删除课程");
	//数据
	private int cid;//需要传入后端的课程号。
	
	private JLabel jlb = new JLabel("请输入要删除的课程号: ");
	private JTextField jtf = new JTextField();		
	private int num;
	public TeacherRemoveC(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（教师端）");
		this.setSize(500, 350);
		
		contentPanel.setLayout(null);//设置为自由布局。
		
		add(jlb);
		add(jtf);
		add(go);
		add(back);
		add(title);
		
		jlb.setBounds(50, 105, 180, 25);
		jtf.setBounds(250,105,50,25);
		go.setBounds(320, 105, 100, 25);
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
				TeacherRemoveC.this.setVisible(false);
			}
		});
		go.addActionListener(new ActionListener() {
			//向后端传cid，并获取该课程的信息（未做完）。
			public void actionPerformed(ActionEvent e) {
				String tmp = jtf.getText();
				if(!tmp.equals("")) {
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(tmp);
					if(isNum.matches()) {
						cid = Integer.parseInt(tmp);
						int status = 1;
						String errmsg = "";
						//cid传入后端操作
						Client.send.get(num).str="12:"+Client.id.get(num)+";"+cid;
						while(Client.receive.get(num).message=="") {
							errmsg=Client.receive.get(num).message;
						}
						errmsg=Client.receive.get(num).message;
						Client.receive.get(num).message="";
						if(!errmsg.equals("ok"))status=0;
						//删除成绩后，判断返回值。
						if(status==1) {
							ModifyOK.start(3,num);
							TeacherRemoveC.this.setVisible(false);
						}else {
							ModifyFalse.start(3,errmsg);
						}
						
					}
				}	
			}
		});
	}
	
	public static void start(int i) {
		new TeacherRemoveC(i).setVisible(true);
	}
}