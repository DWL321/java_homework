package problem2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TxtReader extends JFrame{
	private static final long serialVersionUID = 1L;

	TxtReader(){
		super("SimpleTXTReader");
		setSize(new Dimension(800,600));//设置界面大小
		setLayout(new BorderLayout());
		JTextArea text = new JTextArea();//文件的文本框
		text.setEditable(false);//设置只读
		
		JMenuBar mb = new JMenuBar();//菜单栏初始化
		setJMenuBar(mb);
		JMenu menu = new JMenu("文件");
		JMenuItem m1 = new JMenuItem("打开");
		JMenuItem m2 = new JMenuItem("关闭");
		JMenuItem m3 = new JMenuItem("退出");
		m2.setEnabled(false);//关闭按钮灭活
		menu.add(m1);
		menu.add(m2);
		menu.addSeparator();//加入分割线
		menu.add(m3);
		mb.add(menu);
		
		//加入按钮事件处理器
		m1.addActionListener(new ActionListener() {//打开文件
			public void actionPerformed(ActionEvent ae) {
				String path = System.getProperty("user.dir");
				File file = new File(path);
				JFileChooser jfc = new JFileChooser(file);
		
				FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文件(*.txt)", "txt");//仅能选txt文件
				jfc.setFileFilter(filter);
				jfc.showOpenDialog(text);
				
				File selectedfile = jfc.getSelectedFile();
				try {
					BufferedReader dis = new BufferedReader(new FileReader(selectedfile));
					
					String str = new String();
					String i;
					while ((i = dis.readLine()) != null) {
					    str += i;
					    str += '\n';
					}
					text.setLineWrap(true);
					text.setText(str);
					dis.close();
				} catch (FileNotFoundException e1) { 
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				m2.setEnabled(true);//关闭按钮激活
			}
		});
		
		m2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//关闭文件
				text.setText("");
				m2.setEnabled(false);
			}
		});
		
		m3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//退出程序
				System.exit(0);
			}
		});
		
		JScrollPane jsp = new JScrollPane(text);//设置垂直滚动条
		add(jsp);
	}
	
	public static void main(String[] args) {
		new TxtReader().setVisible(true);
	}
}
