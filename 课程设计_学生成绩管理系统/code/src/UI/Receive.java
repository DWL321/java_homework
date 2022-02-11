package UI;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
	private DataInputStream dis;
	public String message;
	public Receive(Socket client) {
		this.message="";
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
			this.message=this.getMessage();
		}
	}

}
