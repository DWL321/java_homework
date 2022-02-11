package problem1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String args[]) throws IOException{
		System.out.print("Client->");
		//创建Socket对象
		Socket client=new Socket("localhost",2333);
		//获得IP地址
		client.getInetAddress();
		byte[] ip=InetAddress.getLocalHost().getAddress();
		String host="";
		for(int i=0;i<ip.length;i++) {
			if(i>0) {
				host+=".";
			}
			host+=ip[i]&0xFF;
		}
		System.out.println(host+":"+client.getLocalPort());
		
		//创建发送的线程类对象
		Send send=new Send(client);
		//创建接收的线程类对象
		Receive receive=new Receive(client);
		//创建Thread类对象并启动线程
		Thread t1=new Thread(send);
		Thread t2=new Thread(receive);
		t1.start();
		t2.start();
		if(!t1.isAlive()&&!t2.isAlive())client.close();
	}
}
