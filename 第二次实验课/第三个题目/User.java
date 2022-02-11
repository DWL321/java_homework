package problem_3;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<User> followList;
	private List<User> fansList;
	private List<String> messageList;
	public User(String name) {
		this.name=name;
		this.fansList= new ArrayList<>();
		this.followList= new ArrayList<>();
		this.messageList= new ArrayList<>();
	}
	public String getName() {
		return this.name;
	}
	public List<User> getFollowList(){
		return this.followList;
	}
	public List<User> getFanList(){
		return this.fansList;
	}
	public void notify(String message) {
		this.messageList.add(message);
	}
	public void showFollowList() {
		System.out.println(this.name+"'s follow list:");
		for(int i=0;i<this.followList.size();i++) {
			System.out.println(this.followList.get(i).getName());
		}
	}
	public void showFansList() {
		System.out.println(this.name+"'s fans list:");
		for(int i=0;i<this.fansList.size();i++) {
			System.out.println(this.fansList.get(i).getName());
		}
	}
	public void ShowMessageList() {
		System.out.println(this.name+"'s message list:");
		for(int i=0;i<this.messageList.size();i++) {
			System.out.println(this.messageList.get(i));
		}
	}
}
