package rock_paper_scissors;

import java.util.Random;

public class Player {
	private String name;
	private int victoryTimes;
	private int gameTimes;
	public Player(String name) {
		this.name=name;
	}
	public void setPlayer(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public int getVictoryTimes() {
		return victoryTimes;
	}
	public int getGameTimes() {
		return gameTimes;
	}
	public void reset() {
		this.gameTimes=0;
		this.victoryTimes=0;
	}
	public void recordGame(boolean isVictory) {
		this.gameTimes++;
		if(isVictory)this.victoryTimes++;
	}
	public int chooseShape() {
		Random r=new Random();
		int a=r.nextInt(3);
		return a;
	}
	public void showMetrics() {
		double a=((double)victoryTimes)/((double)gameTimes);
		System.out.print(name+":");
		System.out.printf("%f\n",a);
	}
}
