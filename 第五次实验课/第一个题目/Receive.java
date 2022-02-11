package problem1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
	private DataInputStream dis;
	public Receive(Socket client) {
		try {
			//接收数据的数据流
			dis=new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getMessage() {
		String str="";
		try {
			str=dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			String str=this.getMessage();
			if(str.length()!=0)
				System.out.print('\r'+str+"\n->");
				//Eclipse运行\r和\n效果一样，即打印收到的消息时->不能被覆盖而是会换行，在命令行运行可以出现题目中示例的同样效果
		}
	}

}
