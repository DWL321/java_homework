package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegistStudent extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton ok = new JButton("确定"), back = new JButton("返回");
	//输入项
	private JLabel jlb1 = new JLabel("学号: "), jlb2 = new JLabel("用户名: "), jlb3 = new JLabel("密码: ");
	private JLabel	title = new JLabel("学生注册界面");
	//文本框
	private JTextField jtfid = new JTextField(), jtfname = new JTextField(), jtfpassword = new JTextField();
	private int num;
	public RegistStudent(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(jtfid);
		add(jtfname);
		add(jtfpassword);
		add(ok);
		add(back);
		add(jlb1);
		add(jlb2);
		add(jlb3);
		add(title);

		jlb1.setBounds(40, 130, 100, 25);
		jtfid.setBounds(95, 130, 300, 25);
		jlb2.setBounds(40, 154, 100, 25);
		jtfname.setBounds(95, 154, 300, 25);
		jlb3.setBounds(40, 178, 100, 25);
		jtfpassword.setBounds(95, 178, 300, 25);
		
		ok.setBounds(315, 225, 90, 20);
		back.setBounds(95, 225, 90, 20);

		title.setBounds(160, 45, 200, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		jtfid.setOpaque(true);
		jtfname.setOpaque(true);
		jtfpassword.setOpaque(true);
		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = 1;
				String errmsg = "";
				String answer="";
				//前端校验
				if(!jtfid.getText().matches("^[0-9]{8}$")) {
					status = 0;
					errmsg += "学号不合法，必须是8位数字\n";
				}
				if(jtfname.getText().isEmpty()) {
					status = 0;
					errmsg += "请输入姓名\n";
				}
				String t = jtfpassword.getText();
				if(!t.matches("[0-9A-Za-z]{6,18}")) {
					status = 0;
					errmsg += "密码不合法，必须是6-18位数字、大小写字母\n";
				}
				
				if(status==1) {//在通过了前端校验的情况下。
					String passmsg ="3:"+ jtfid.getText() + ";" + jtfname.getText() + ";" + t;
					Client.send.get(num).str=passmsg;
					while(Client.receive.get(num).message=="") {
						answer=Client.receive.get(num).message;
					}
					answer=Client.receive.get(num).message;
					Client.receive.get(num).message="";
					if(answer.equals("此用户已存在\n")) {
						status=0;
						errmsg=answer;
					}
					//传入后端，后端校验。
				}
				//校验无误，跳转到registok页面。
				//校验出错，弹窗显示registstatus窗口。
				if(status==1) {
					RegistOK.start(num);
					RegistStudent.this.setVisible(false);
				}else {
					RegistStatus.start(errmsg);
				}
			}
		});
		back.addActionListener(new ActionListener() {
			//返回选择界面。
			public void actionPerformed(ActionEvent e) {
				RegistChoose.start(num);
				RegistStudent.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new RegistStudent(i).setVisible(true);
	}
}