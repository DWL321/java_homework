package UI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {
	public String str=""; 
	private DataOutputStream dos;
	public Send() {
		this.str="";
	}
	public Send(Socket client) {
		this();//调用本类无参构造方法
		try {
			//用于发送数据的数据流
			dos=new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private synchronized void send() {
		if(this.str=="") return ;
		try {
			dos.writeUTF(this.str);
			dos.flush();
			this.str="";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			this.send();
		}
	}

}
