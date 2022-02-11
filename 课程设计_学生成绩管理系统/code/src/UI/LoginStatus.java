package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginStatus extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton ok = new JButton("确定");
	private JLabel title = new JLabel("登录失败！");
	private JLabel jlb = new JLabel("错误原因: ");
	private JTextArea jtf = new JTextArea();

	public LoginStatus(String errmsg) {
		this.init(errmsg);
		this.addListener();
	}

	private void init(String errmsg) {
		//整体布局
		this.setTitle("学生成绩管理系统");
		this.setSize(500, 350);

		jtf.setText(errmsg);
		
		contentPanel.setLayout(null);//设置为自由布局。
		add(ok);
		add(title);
		add(jlb);
		add(jtf);
		
		ok.setBounds(200, 225, 90, 20);
		
		title.setBounds(160, 20, 200, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));
		
		jlb.setBounds(40, 85, 100, 25);
		jtf.setBounds(95, 85, 300, 100);
		jtf.setEditable(false);

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginStatus.this.setVisible(false);
			}
		});
	}
	
	public static void start(String errmsg) {
		new LoginStatus(errmsg).setVisible(true);
	}
}
