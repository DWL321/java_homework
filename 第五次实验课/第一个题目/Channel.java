package problem1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Channel implements Runnable{
	private DataInputStream dis;
	private DataOutputStream dos;
	private String host;
	private int port;
	private Boolean live=true;
	public Channel(Socket client) {
		//获得IP地址
		client.getInetAddress();
		byte[] ip = null;
		try {
			ip = InetAddress.getLocalHost().getAddress();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			this.live=false;
			Server.list.remove(this);
		}
		this.host="";
		for(int i=0;i<ip.length;i++) {
			if(i>0) {
				this.host+=".";
			}
			this.host+=ip[i]&0xFF;
		}
		this.port=client.getPort();
		try {
			//获取输入流接收数据
			dis=new DataInputStream(client.getInputStream());
			dos=new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.live=false;
			Server.list.remove(this);
		}
	}
	//接收数据的方法
	private String receive() {
		String str="";
		try {
			str=dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.live=false;
			Server.list.remove(this);
		}
		return str;
	}
	//发送数据的方法
	private void send(String str) {
		try {
			dos.writeUTF(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.live=false;
			Server.list.remove(this);
		}
	}
	private void sendOthers() {
		String str=this.receive();
		if(str.length()==0)return ;
		List<Channel> list =Server.list;
		for(Channel other : list) {
			if(other.port!=this.port) {
				other.send(this.host+":"+this.port+":"+str);
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.live) {
			this.sendOthers();
		}
	}
}
