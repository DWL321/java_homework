package problem_3;

public class FansIncrease implements Observer{
	public void notify(User pageUser,User follower) {
		pageUser.getFanList().add(follower);
	}
}
