package problem_3;

public class ObserverPatternTest {
	public static void main(String[] args) {
		User zhangSan = new User("ZhangSan");
		User liSi = new User("LiSi");
		User wangWu = new User("WangWu");
		User zhaoLiu = new User("zhaoliu");
		FollowButton[] followButtons ={new FollowButton(zhangSan),new FollowButton(liSi),new FollowButton(wangWu),new FollowButton(zhaoLiu)};
		FollowIncrease followIncrease = new FollowIncrease();
		FansIncrease fansIncrease = new FansIncrease();
		BeFollowed beFollowed = new BeFollowed();
		for (FollowButton followButton : followButtons) {
			followButton.addObserver(followIncrease);
			followButton.addObserver(fansIncrease);
			followButton.addObserver(beFollowed);
		}
		followButtons[0].click(liSi);
		followButtons[0].click(wangWu);
		followButtons[0].click(zhaoLiu);
		followButtons[1].click(zhangSan);
		followButtons[1].click(wangWu);
		followButtons[2].click(zhangSan);
		followButtons[2].click(liSi);
		followButtons[2].click(zhaoLiu);
		followButtons[3].click(liSi);
		followButtons[3].click(wangWu);
		zhangSan.showFansList();
		zhangSan.showFollowList();
		zhangSan.ShowMessageList();
		liSi.showFansList();
		liSi.showFollowList();
		liSi.ShowMessageList();
		wangWu.showFansList();
		wangWu.showFollowList();
		wangWu.ShowMessageList();
		zhaoLiu.showFansList();
		zhaoLiu.showFollowList();
		zhaoLiu.ShowMessageList();
	}
}
