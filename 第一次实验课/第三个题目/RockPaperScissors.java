package rock_paper_scissors;

public class RockPaperScissors {
	public static void main(String[] args) {
		 Player zhangSan = new Player("ZhangSan");
		 Player liSi = new Player("LiSi");
		 Player wangWu = new Player("WangWu");
		 Game game = new Game();
		 System.out.println("Game.run Test");
		 game.setFirstPlayer(zhangSan);
		 game.setSecondPlayer(liSi);
		 game.run(5);
		 System.out.println("\nPlayer.reset test");
		 System.out.println("before reset:");
		 System.out.printf("victoryTimes : %d\n",
		liSi.getVictoryTimes());
		 System.out.printf("gameTimes : %d\n", liSi.getGameTimes());
		 liSi.reset();
		 System.out.println("after reset:");
		 System.out.printf("victoryTimes : %d\n",
		liSi.getVictoryTimes());
		 System.out.printf("gameTimes : %d\n", liSi.getGameTimes());
		 System.out.println("\nGame.setFirstPlayer test");
		 game.setFirstPlayer(wangWu); 
		 game.run(5);
	 } 
}
