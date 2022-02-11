package problem_3;

import java.util.ArrayList;
import java.util.List;

public class FollowButton {
	private User pageUser;
	private List<Observer> observerList;
	public FollowButton(User pageUser) {
		this.pageUser=pageUser;
		this.observerList=new ArrayList<>();
	}
	public void click(User clicker) {
		this.observerList.get(0).notify(this.pageUser,clicker);
		this.observerList.get(1).notify(this.pageUser,clicker);
		this.observerList.get(2).notify(this.pageUser,clicker);
	}
	public void addObserver(Observer observer) {
		this.observerList.add(observer);
	}
}
