package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class TeacherQGid extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton back = new JButton("返回"), go = new JButton("查询");
	//标题
	private JLabel	title = new JLabel("按课程号查询成绩");
	//数据
	private String msg = "2;Math;19038024=98,19308027=97,19802844=80";//这只是测试样例，真正的数据要从后端传过来的。
	private int cid;//需要传入后端的课程号。
	
	private JLabel jlb = new JLabel("请输入要查询的课程号: ");
	private JTextField jtf = new JTextField();		
	private int num;
	public TeacherQGid(int i) {
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
			//返回教师界面。
			public void actionPerformed(ActionEvent e) {
				TeacherGrade.start(num);
				TeacherQGid.this.setVisible(false);
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
						System.out.println(cid);
						//数据传入后端操作
						Client.send.get(num).str="15:"+Client.id.get(num)+";"+cid;
						while(Client.receive.get(num).message=="") {
							msg=Client.receive.get(num).message;
						}
						msg=Client.receive.get(num).message;
						Client.receive.get(num).message="";
						//得到数据之后：
						//数据处理，添加表格
						if(msg=="-2") {
							JTextField jtf = new JTextField();
							jtf.setText("您的课程列表中这门课不存在！");
							jtf.setEditable(false);
							add(jtf);
							jtf.setBounds(50, 175, 400, 75);
						}else {
							String[] sa = msg.split(";");
							String[] sb = sa[2].split(",");
							
							JTextField jtf = new JTextField();
							jtf.setText("课程号："+sa[0]+"    课程名："+sa[1]);
							jtf.setEditable(false);
							add(jtf);
							jtf.setBounds(50, 140, 400, 25);
							
							JTable jt = new JTable(sb.length+1,2);
							jt.setEnabled(false);//整个表格不可编辑
							
							jt.setValueAt("学号", 0, 0);
							jt.setValueAt("成绩", 0, 1);
							
							for(int i=0;i<sb.length;i++) {
								String[] stmp = sb[i].split("=");
								if(Integer.parseInt(stmp[1])==-1) {
									stmp[1] = "未出成绩";
								}
								jt.setValueAt(stmp[0], i+1, 0);
								jt.setValueAt(stmp[1], i+1, 1);
							}
							
							add(jt);
							jt.setBounds(50, 175, 400, 100);
						}
					}
				}	
			}
		});
	}
	
	public static void start(int i) {
		new TeacherQGid(i).setVisible(true);
	}
}