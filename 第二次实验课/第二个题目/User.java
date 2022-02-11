package problem_2;

import java.util.ArrayList;
import java.util.List;

public class User implements Sender,Receiver {
	private String name;
	private List<String> messageList;
	public User(String name) {
		this.name=name;
		this.messageList=new ArrayList<>();
	}
	public void receive(Sender sender, String message) {
		this.messageList.add(message);
	}

	public void send(Receiver receiver, String message) {
		String s="[From "+this.name+"]"+message;
		receiver.receive(this,s);
	}
	public void showMessages() {
		for(int i=0;i<this.messageList.size();i++) {
			System.out.println(this.messageList.get(i));
		}
	}
}
