package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentLAG extends JFrame {
	//整体布局
	private JPanel contentPanel = new JPanel();
	//按钮
	private JButton back = new JButton("返回");
	//标题
	private JLabel	title = new JLabel("查询所有成绩");
	//数据
	private String msg = "1,Math,34;2,English,78";//这只是测试样例，真正的数据要从后端传过来的。
	private int num;
	public StudentLAG(int i) {
		this.num=i;
		this.init();
		this.addListener();
	}

	private void init() {
		//整体布局
		this.setTitle("学生成绩管理系统（学生端）");
		this.setSize(500, 350);
		Client.send.get(num).str="7:"+Client.id.get(num);
		while(Client.receive.get(num).message=="") {
			msg=Client.receive.get(num).message;
		}
		msg=Client.receive.get(num).message;
		Client.receive.get(num).message="";
		//数据处理，添加表格
		if(msg == "-2") {
			JTextField jtf = new JTextField();
			jtf.setText("您没有任何课程！");
			jtf.setEditable(false);
			add(jtf);
			jtf.setBounds(10, 100, 400, 100);
		}else {
			String[] tmp = msg.split(";");
			JTable jt = new JTable(tmp.length+1,3);
			jt.setEnabled(false);//整个表格不可编辑
			
			jt.setValueAt("课程号", 0, 0);
			jt.setValueAt("课程名", 0, 1);
			jt.setValueAt("成绩", 0, 2);
			
			for(int i=0;i<tmp.length-1;i++) {
				for(int j=0;j<tmp.length-1-i;j++) {
					String[] stmp1 = tmp[j].split(",");
					String[] stmp2 = tmp[j+1].split(",");
					if(Integer.parseInt(stmp1[0])>Integer.parseInt(stmp2[0])) {
						String t = tmp[j];
						tmp[j] = tmp[j+1];
						tmp[j+1] = t;
					}
				}
			}
			
			for(int i=0;i<tmp.length;i++) {
				String[] stmp = tmp[i].split(",");
				if(Integer.parseInt(stmp[2])==-1) {
					stmp[2] = "未出成绩";
				}
				jt.setValueAt(stmp[0], i+1, 0);
				jt.setValueAt(stmp[1], i+1, 1);
				jt.setValueAt(stmp[2], i+1, 2);
				
			}
			add(jt);
			jt.setBounds(10, 100, 400, 100);
		}
		

		contentPanel.setLayout(null);//设置为自由布局。
		
		add(back);
		add(title);
		
		back.setBounds(95, 225, 90, 20);
		title.setBounds(160, 45, 200, 50);
		title.setFont(new Font("微软雅黑", Font.BOLD, 30));

		contentPanel.setOpaque(false);
		getContentPane().add(contentPanel);
	}

	private void addListener() {
		back.addActionListener(new ActionListener() {
			//返回学生界面。
			public void actionPerformed(ActionEvent e) {
				StudentGrade.start(num);
				StudentLAG.this.setVisible(false);
			}
		});
	}
	
	public static void start(int i) {
		new StudentLAG(i).setVisible(true);
	}
}