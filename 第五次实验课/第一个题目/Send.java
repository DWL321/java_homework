package problem1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {
	private BufferedReader br; 
	private DataOutputStream dos;
	public Send() {
		//从键盘获取数据
		br=new BufferedReader(new InputStreamReader(System.in)); 
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
	private String getMessage() {
		String str="";
		try {
			str = br.readLine();
			while(str.length()==0) {
				System.out.print("You can not send message with none characters.\n->");
				str = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	private void send(String str) {
		try {
			dos.writeUTF(str);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.print("->");
			this.send(getMessage());
		}
	}

}
