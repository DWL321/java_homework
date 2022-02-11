package UI;

import java.io.IOException;
import java.util.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	static List<Integer> id = new ArrayList<Integer>();
	static List<String> name =new ArrayList<String>();
	static List<Socket> client=new ArrayList<Socket>();
	static List<Send> send=new ArrayList<Send>();
	static List<Receive> receive=new ArrayList<Receive>();
	static int count=0;
	
	public static void start(int id,String name) throws IOException{
		Client.id.add(id);
		Client.name.add(name);
		//创建Socket对象
		try {
			Client.client.add(new Socket("localhost",2333));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建发送的线程类对象
		Client.send.add(new Send(client.get(count)));
		//创建接收的线程类对象
		Client.receive.add(new Receive(client.get(count)));
		//创建Thread类对象并启动线程
		Thread t1=new Thread(Client.send.get(count));
		Thread t2=new Thread(Client.receive.get(count));
		int i=count;
		count++;
		t1.start();
		t2.start();
		if(!t1.isAlive()&&!t2.isAlive())Client.client.get(i).close();
	}
	
	public static void main(String[] args) {
		try {
			Client.start(0,"none");
			SystemOn.start(count-1);////调试时可以修改
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
