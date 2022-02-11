package problem_2;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Receiver {
	private String name;
	private List<User> userList;
	Channel(String name){
		this.name=name;
		this.userList=new ArrayList<>();
	}
	@Override
	public void receive(Sender sender, String message) {
		message="[From "+this.name+"]"+message;
		for(int i=0;i<this.userList.size();i++) {
			if(this.userList.get(i)!=sender) {
				this.userList.get(i).receive(sender, message);
			}
		}
	}
	public void add(User user) {
		this.userList.add(user);
	}
	public void remove(User user) {
		this.userList.remove(user);
	}
}
