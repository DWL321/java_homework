package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TeacherAddC extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton ok = new JButton("确定"), back = new JButton("返回");
	//输入项
	private JLabel jlb = new JLabel("课程名: "), jlbb= new JLabel("学生学号： ");
	private JLabel	title = new JLabel("增加课程页面");
	//文本框
	private JTextField jtf = new JTextField();
	//表格
	private JTable jt = new JTable(6,5);
	private int num;
	public TeacherAddC(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（教师端）");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(jtf);
		add(ok);
		add(back);
		add(jlb);
		add(jlbb);
		add(title);
		add(jt);
		
		ok.setBounds(315, 260, 90, 20);
		back.setBounds(95, 260, 90, 20);
		jlb.setBounds(40, 80, 100, 25);
		jtf.setBounds(95, 80, 300, 25);
		jlbb.setBounds(220, 120, 100, 25);
		jt.setBounds(50, 150, 400, 100);

		title.setBounds(160, 20, 200, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = 1;
				String errmsg = "";
				
				String passmsg = "";
				
				if(jtf.getText().isEmpty()) {
					status = 0;
					errmsg += "课程名不能为空！\n";
				}
				
				passmsg +="11:"+ Client.id.get(num) + ";"+ jtf.getText()  + ";";
				int wid = jt.getColumnCount();
				int len = jt.getRowCount();
				String[] tmp = new String[len*wid];
				int sum = 0;
				int isEmpty = 1;
				for(int j=0;j<wid;j++) {
					int tstatus = 1;
					for(int i=0;i<len;i++) {
						//前端校验
						if(jt.getValueAt(i, j)==null||jt.getValueAt(i, j).equals("")) {
							continue;
						}
						isEmpty=0;
						String str = jt.getValueAt(i, j).toString();
						if(!str.matches("[0-9]{8}")) {
							status = 0;
							tstatus=0;
							errmsg += "存在不合法的学号，必须是8位数字！\n";
							break;
						}

						tmp[sum] = str;
						sum++;
					}
					if(tstatus==0) {
						break;
					}
				}
				if(isEmpty==1) {
					status = 0;
					errmsg+="学生列表不可以为空!\n";
				}
				String[] tmp0 = new String[sum];
				for(int i=0;i<sum;i++) {
					tmp0[i] = tmp[i];
				}
				passmsg += String.join(",",tmp0);
				
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
					ModifyOK.start(4,num);
					TeacherAddC.this.setVisible(false);
				}else {
					ModifyFalse.start(4,errmsg);
				}
			}
		});
		back.addActionListener(new ActionListener() {
			//返回教师课程界面。
			public void actionPerformed(ActionEvent e) {
				TeacherCourse.start(num);
				TeacherAddC.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new TeacherAddC(i).setVisible(true);
	}
}
