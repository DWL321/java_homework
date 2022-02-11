package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class LoginStudent extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton ok = new JButton("确定"), back = new JButton("返回");
	//输入项
	private JLabel jlb1 = new JLabel("学号: "), jlb2 = new JLabel("密码:  ");
	private JLabel	title = new JLabel("学生登录界面");
	//文本框
	private JTextField jtfid = new JTextField();
	private JPasswordField jtfpassword = new JPasswordField();
	private int num=0;
	public LoginStudent(int i) {
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
		add(jtfpassword);
		add(ok);
		add(back);
		add(jlb1);
		add(jlb2);
		add(title);

		jlb1.setBounds(40, 130, 100, 25);
		jtfid.setBounds(95, 130, 300, 25);
		jlb2.setBounds(40, 178, 100, 25);
		jtfpassword.setBounds(95, 178, 300, 25);
		
		ok.setBounds(315, 225, 90, 20);
		back.setBounds(95, 225, 90, 20);

		title.setBounds(160, 45, 200, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		jtfid.setOpaque(true);
		jtfpassword.setOpaque(true);
		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = 1;
				String errmsg = "";
				String answer = "";
				String id=jtfid.getText();
				//前端校验
				if(!id.matches("^[0-9]{8}$")) {
					status = 0;
					errmsg += "学号不合法，必须是8位数字\n";
				}
				String t = "";
				for(int i=0;i<jtfpassword.getPassword().length;i++) {
					t+=jtfpassword.getPassword()[i];
				}
				if(!t.matches("[0-9A-Za-z]{6,18}")) {
					status = 0;
					errmsg += "密码不合法，必须是6-18位数字、大小写字母\n";
				}
				
				if(status==1) {//在通过了前端校验的情况下。
					String passmsg ="1:"+id+ ";" + t;
					Client.send.get(num).str=passmsg;
					while(Client.receive.get(num).message=="") {
						answer="";
					}
					answer=Client.receive.get(num).message;
					Client.receive.get(num).message="";
					if(answer.equals("密码错误\n")||answer.equals("此用户不存在\n")) {
						status=0;
						errmsg=answer;
					}
					//传入后端，后端校验。
				}
				//校验无误，直接跳转到学生系统页面，并初始化Client类。
				//校验出错，弹窗显示loginstatus窗口。
				if(status==1) {
					
					int u_id=0;
					for(int i=0;i<8;i++)u_id=u_id*10+id.charAt(i)-'0';
					Client.id.set(num, u_id);
					Client.name.set(num, answer);
					StudentOn.start(num);
					LoginStudent.this.setVisible(false);
				}else {
					LoginStatus.start(errmsg);
				}
			}
		});
		back.addActionListener(new ActionListener() {
			//返回选择界面。
			public void actionPerformed(ActionEvent e) {
				LoginChoose.start(num);
				LoginStudent.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new LoginStudent(i).setVisible(true);
	}

}
