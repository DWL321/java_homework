package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegistOK extends JFrame{
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton ok = new JButton("确定");
	private JLabel	title = new JLabel("注册成功！");
	private int num;
	public RegistOK(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统");
		this.setSize(500, 350);

		contentPanel.setLayout(null);//设置为自由布局。
		add(ok);
		add(title);
		
		ok.setBounds(200, 225, 90, 20);
		
		title.setBounds(160, 45, 200, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		ok.addActionListener(new ActionListener() {
			//返回选择界面。
			public void actionPerformed(ActionEvent e) {
				SystemOn.start(num);
				RegistOK.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new RegistOK(i).setVisible(true);
	}
}
