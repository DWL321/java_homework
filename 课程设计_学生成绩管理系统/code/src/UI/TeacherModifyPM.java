package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TeacherModifyPM extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton ok = new JButton("确定"), back = new JButton("返回");
	//输入项
	private JLabel jlb1 = new JLabel("教师号: "), jlb2 = new JLabel("用户名: "), jlb3 = new JLabel("密码: ");
	private JLabel	title = new JLabel("修改个人信息");
	//文本框
	private JTextField jtfid,jtfname, jtfpassword = new JTextField();
	private int num;
	public TeacherModifyPM(int i) {
		this.num=i;
		jtfid = new JTextField(Client.id.get(num)+"");
		jtfname = new JTextField(Client.name.get(num)) ;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（教师端）");
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

		jtfid.setEditable(false);//学号不可修改
		
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
				
				//前端校验
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
					String passmsg ="8:"+Client.id.get(num)+";"+jtfname.getText() + ";" + t;
					//传入后端。
					Client.send.get(num).str=passmsg;
				}
				//校验无误，跳转到registok页面。
				//校验出错，弹窗显示registstatus窗口。
				if(status==1) {
					Client.name.set(num, jtfname.getText());
					ModifyOK.start(1,num);
					TeacherModifyPM.this.setVisible(false);
				}else {
					ModifyFalse.start(1,errmsg);
				}
			}
		});
		back.addActionListener(new ActionListener() {
			//返回教师界面。
			public void actionPerformed(ActionEvent e) {
				TeacherOn.start(num);
				TeacherModifyPM.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new TeacherModifyPM(i).setVisible(true);
	}
}