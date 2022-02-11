package problem1;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
//基于TCP协议的聊天室
public class Server {
	//创建集合对象，存储每一个连接进来的客户端
	public static List<Channel> list=new ArrayList<Channel>();
	
	public static void main(String args[]) throws IOException{
		System.out.println("Server->java Server");
		System.out.println("Initializing Port...");
		System.out.println("Listening...");
		//创建ServerSocket对象
		ServerSocket server=new ServerSocket(2333);
		do{
			//监听客户端是否有客户端链接
			Socket socket = server.accept();
			//获得IP地址
			socket.getInetAddress();
			byte[] ip=InetAddress.getLocalHost().getAddress();
			String host="";
			for(int i=0;i<ip.length;i++) {
				if(i>0) {
					host+=".";
				}
				host+=ip[i]&0xFF;
			}
			System.out.println("Connect to client: "+host+":"+socket.getPort());
			//创建线程类的对象
			Channel channel=new Channel(socket);
			list.add(channel);
			new Thread(channel).start();
		}while(list.size()!=0);
		server.close();
	}
}
