package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class TeacherModifyG extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton back = new JButton("返回"), go = new JButton("查询"), ok = new JButton("确认");
	//标题
	private JLabel	title = new JLabel("按课程号修改成绩");
	//数据
	private String msg = "2;Math;19038024=98,19308027=97,19802844=80";//这只是测试样例，真正的数据要从后端传过来的。
	private int cid;//需要传入后端的课程号。
	
	private JLabel jlb = new JLabel("请输入要修改的课程号: ");
	private JTextField jtf = new JTextField();	
	private JTable jt;
	private int num;
	public TeacherModifyG(int i) {
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
				TeacherGrade.start(num);
				TeacherModifyG.this.setVisible(false);
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
						if(msg.equals("-2")) {
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
							
							jt = new JTable(sb.length+1,2);
							
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
				
				String passmsg = "16:"+Client.id.get(num)+":"+cid+":";
				int len = jt.getRowCount();
				for(int i=1;i<len;i++) {
					String str1 = jt.getValueAt(i, 0).toString();
					String str2 = jt.getValueAt(i, 1).toString();
					//前端校验
					if(str2.isEmpty()||str2.equals("未出成绩")||str2.equals("-1")) {
						str2 = "-1";
					}
					else if(!(str2.matches("[0-9]*") && Integer.parseInt(str2)>=0 && Integer.parseInt(str2)<=100)) {
						status = 0;
						errmsg += "存在不合法的成绩！必须在0-100之间,\n没有成绩请输入-1或者空着\n";
						break;
					}
					
					if(i!=len-1) {
						passmsg += str1 + "," + str2 + ";";
					}
					else {
						passmsg += str1 + "," + str2;
					}
					
				}
				
				if(status==1) {//在通过了前端校验的情况下。
					System.out.println(passmsg);
					//传入后端，后端校验。
					Client.send.get(num).str=passmsg;
					while(Client.receive.get(num).message=="") {
						errmsg=Client.receive.get(num).message;
					}
					errmsg=Client.receive.get(num).message;
					Client.receive.get(num).message="";
					if(!errmsg.equals("ok"))status=0;
				}
				//校验无误，跳转到registok页面。
				//校验出错，弹窗显示registstatus窗口。
				if(status==1) {
					ModifyOK.start(2,num);
					TeacherModifyG.this.setVisible(false);
				}else {
					ModifyFalse.start(2,errmsg);
				}
			}
		});
	}
	
	public static void start(int i) {
		new TeacherModifyG(i).setVisible(true);
	}
}