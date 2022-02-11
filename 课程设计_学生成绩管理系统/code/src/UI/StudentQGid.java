package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class StudentQGid extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton back = new JButton("返回"), go = new JButton("查询");
	//标题
	private JLabel	title = new JLabel("按课程号查询成绩");
	//数据
	private String msg = "2;Math;89";//这只是测试样例，真正的数据要从后端传过来的。
	private int cid;//需要传入后端的课程号。
	
	private JLabel jlb = new JLabel("请输入要查询的课程号: ");
	private JTextField jtf = new JTextField();		
	private int num;
	public StudentQGid(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（学生端）");
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
		title.setBounds(100, 45, 300, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		back.addActionListener(new ActionListener() {
			//返回学生界面。
			public void actionPerformed(ActionEvent e) {
				StudentGrade.start(num);
				StudentQGid.this.setVisible(false);
			}
		});
		go.addActionListener(new ActionListener() {
			//向后端传cid，并获取该课程的信息（未做完）。
			public void actionPerformed(ActionEvent e) {
				String tmp = jtf.getText();
				if(!tmp.equals("")) {
					Pattern pattern = Pattern.compile("[0-9]*");//正则表达式做前端校验
					Matcher isNum = pattern.matcher(tmp);
					if(isNum.matches()) {
						cid = Integer.parseInt(tmp);
						System.out.println(cid);
						//数据传入后端操作
						Client.send.get(num).str="6:"+Client.id.get(num)+";"+cid;
						while(Client.receive.get(num).message=="") {
							msg=Client.receive.get(num).message;
						}
						msg=Client.receive.get(num).message;
						Client.receive.get(num).message="";
						//得到数据之后：
						//数据处理，添加表格
						System.out.println(msg);
						if(msg=="-2") {
							JTextField jtf = new JTextField();
							jtf.setText("您的课程列表中这门课不存在！");
							jtf.setEditable(false);
							add(jtf);
							jtf.setBounds(50, 175, 400, 75);
						}else {
							JTable jt = new JTable(2,3);
							jt.setEnabled(false);//整个表格不可编辑
							
							jt.setValueAt("课程号", 0, 0);
							jt.setValueAt("课程名", 0, 1);
							jt.setValueAt("成绩", 0, 2);
							
							String[] stmp = msg.split(";");
							if(Integer.parseInt(stmp[2])==-1) {
								stmp[2] = "未出成绩";
							}
							jt.setValueAt(stmp[0], 1, 0);
							jt.setValueAt(stmp[1], 1, 1);
							jt.setValueAt(stmp[2], 1, 2);

							add(jt);
							jt.setBounds(50, 175, 400, 75);
						}
					}
				}
			}
		});
	}
	
	public static void start(int i) {
		new StudentQGid(i).setVisible(true);
	}
}