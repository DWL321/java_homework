package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class TeacherModifyC extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton back = new JButton("返回"), go = new JButton("查询"), ok = new JButton("确认");
	//标题
	private JLabel	title = new JLabel("按课程号修改课程");
	//数据
	private String msg = "2;Math;19038024,19308027,19802844";//这只是测试样例，真正的数据要从后端传过来的。
	private int cid;//需要传入后端的课程号。
	
	private JLabel jlb = new JLabel("请输入要修改的课程号: ");
	private JTextField jtf = new JTextField(),jtf0;		
	private JTable jt;
	private int num;
	public TeacherModifyC(int i) {
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
		add(ok);
		add(title);
		
		ok.setEnabled(false);
		
		jlb.setBounds(50, 105, 180, 25);
		jtf.setBounds(250,105,50,25);
		go.setBounds(320, 105, 100, 25);
		back.setBounds(95, 275, 90, 20);
		ok.setBounds(200, 275, 90, 20);
		title.setBounds(100, 30, 300, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		back.addActionListener(new ActionListener() {
			//返回学生界面。
			public void actionPerformed(ActionEvent e) {
				TeacherCourse.start(num);
				TeacherModifyC.this.setVisible(false);
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
						Client.send.get(num).str="14:"+Client.id.get(num)+";"+cid;
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
							
							JLabel jlb1 = new JLabel("课程号： ");
							JTextField jtf1 = new JTextField(sa[0]);
							jtf1.setEditable(false);
							add(jlb1);
							add(jtf1);
							JLabel jlb0 = new JLabel("课程名： ");
							jtf0 = new JTextField(sa[1]);
							jtf0.setEditable(true);
							add(jlb0);
							add(jtf0);
							jlb1.setBounds(50, 140, 75, 25);
							jlb0.setBounds(200, 140, 75, 25);
							jtf1.setBounds(125, 140, 75, 25);
							jtf0.setBounds(275, 140, 75, 25);
							
							jt = new JTable(sb.length+1,5);
							for(int i=0;i<5;i++) {
								jt.setValueAt("学号", 0, i);
							}
							
							for(int i=0;i<sb.length;i++) {
								jt.setValueAt(sb[i], i+1, 0);
							}
							
							add(jt);
							jt.setBounds(50, 175, 400, 100);
							ok.setEnabled(true);
						}
						
					}
				}
			}
		});
		ok.addActionListener(new ActionListener() {
			//提交成绩修改信息。
			public void actionPerformed(ActionEvent e) {
				int status = 1;
				String errmsg = "";
				
				String passmsg = "";
				
				if(jtf0.getText().isEmpty()) {
					status = 0;
					errmsg += "课程名不能为空！\n";
				}
				
				passmsg ="13:"+Client.id.get(num)+";"+cid+";"+jtf0.getText() + ";";
				
				int wid = jt.getColumnCount();
				int len = jt.getRowCount();
				String[] tmp = new String[len*wid];
				int c = 0;
				int isEmpty = 1;
				for(int j=0;j<wid;j++) {
					int tstatus = 1;
					for(int i=1;i<len;i++) {
						//前端校验
						if(jt.getValueAt(i, j)==null||jt.getValueAt(i, j).equals("")) {
							continue;
						}
						isEmpty=0;						
						String str = jt.getValueAt(i, j).toString();
						System.out.print(str);
						if(!str.matches("[0-9]{8}")) {
							status = 0;
							tstatus = 0;
							errmsg += "存在不合法的学号，必须是8位数字!\n";
							break;
						}
						tmp[c] = str;
						c++;	
					}
					if(tstatus==0) {
						break;
					}
				}
				if(isEmpty==1) {
					status=0;
					errmsg+="学生列表不可以为空!\n";
				}
				String[] tmp0 = new String[c];
				for(int i=0;i<c;i++) {
					tmp0[i] = tmp[i];
				}
				passmsg += String.join(",",tmp0);
				
				if(status==1) {//在通过了前端校验的情况下。
					System.out.println(passmsg);
					Client.send.get(num).str=passmsg;
					while(Client.receive.get(num).message=="") {
						errmsg=Client.receive.get(num).message;
					}
					errmsg=Client.receive.get(num).message;
					Client.receive.get(num).message="";
					if(!errmsg.equals("ok"))status=0;
					//传入后端，后端校验。
				}
				//校验无误，跳转到registok页面。
				//校验出错，弹窗显示registstatus窗口。
				if(status==1) {
					ModifyOK.start(5,num);
					TeacherModifyC.this.setVisible(false);
				}else {
					ModifyFalse.start(5,errmsg);
				}
			}
		});
	}
	
	public static void start(int i) {
		new TeacherModifyC(i).setVisible(true);
	}
}