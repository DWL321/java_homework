package rock_paper_scissors;

public class Game {
	private Player firstPlayer=new Player("1");
	private Player secondPlayer=new Player("2");
	public void setFirstPlayer(Player a) {
		this.firstPlayer=a;
	}
	public void setSecondPlayer(Player a) {
		this.secondPlayer=a;
	}
	public String getFirstPlayer(){
		return firstPlayer.getName();
	}
	public String getSecondPlayer(){
		return secondPlayer.getName();
	}
	public void run(int n) {
		this.firstPlayer.reset();
		this.secondPlayer.reset();
		while(n>0) {
			n--;
			int a=firstPlayer.chooseShape();
			if(a==0) {
				System.out.print(firstPlayer.getName()+":rock ");
			}
			else if(a==1) {
				System.out.print(firstPlayer.getName()+":paper ");
			}
			else {
				System.out.print(firstPlayer.getName()+":scissors ");
			}
			int b=secondPlayer.chooseShape();
			if(b==0) {
				System.out.print(secondPlayer.getName()+":rock result:");
			}
			else if(b==1) {
				System.out.print(secondPlayer.getName()+":paper result:");
			}
			else {
				System.out.print(secondPlayer.getName()+":scissors result:");
			}
			if(a==b){
				System.out.println("Draw");
				firstPlayer.recordGame(false);
				secondPlayer.recordGame(false);
			}
			else if(a==1+b||(a==0&&b==2)) {
				System.out.println(firstPlayer.getName());
				firstPlayer.recordGame(true);
				secondPlayer.recordGame(false);
			}
			else {
				System.out.println(secondPlayer.getName());
				firstPlayer.recordGame(false);
				secondPlayer.recordGame(true);
			}
		}
		firstPlayer.showMetrics();
		secondPlayer.showMetrics();
	}
}
