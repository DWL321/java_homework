package problem3;

public class Philosopher implements Runnable{
	private int philosopherNumber;
	public static fork forks=new fork();
	public Philosopher(int philosopherNumber) {
		this.philosopherNumber = philosopherNumber;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(this.philosopherNumber%2==1) {
				forks.takefork((this.philosopherNumber-1)%5, this.philosopherNumber, true);
				forks.takefork((this.philosopherNumber)%5, this.philosopherNumber, false);
				System.out.println("Philosopher "+this.philosopherNumber+" eats spaghetti");
				forks.putChopsticks((this.philosopherNumber-1)%5,this.philosopherNumber, true);
				forks.putChopsticks((this.philosopherNumber)%5,this.philosopherNumber, false);
			}
			else {
				forks.takefork((this.philosopherNumber)%5, this.philosopherNumber, false);
				forks.takefork((this.philosopherNumber-1)%5, this.philosopherNumber, true);
				System.out.println("Philosopher "+this.philosopherNumber+" eats spaghetti");
				forks.putChopsticks((this.philosopherNumber)%5,this.philosopherNumber, false);
				forks.putChopsticks((this.philosopherNumber-1)%5,this.philosopherNumber, true);
			}
			while((int)(1+Math.random()*10)%2==0)System.out.println("Philosopher "+this.philosopherNumber+" thinks");
		}
	}
}