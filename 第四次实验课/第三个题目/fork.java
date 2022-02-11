package problem3;

public class fork{
	public boolean[] isfree=new boolean[5];
	public fork() {
		for(int i=0;i<5;i++) {
			this.isfree[i]=true;
		}
	}
	public synchronized void takefork(int i,int philosopherNumber,boolean isleft){
		while(!isfree[i]){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isfree[i]=false;
		if(isleft)System.out.println("Philosopher "+philosopherNumber+" takes left fork.");
		else System.out.println("Philosopher "+philosopherNumber+" takes right fork.");
	}
	public synchronized void putChopsticks(int i,int philosopherNumber,boolean isleft){
		isfree[i]=true;
		if(isleft)System.out.println("Philosopher "+philosopherNumber+" puts left fork down.");
		else System.out.println("Philosopher "+philosopherNumber+" puts right fork down.");
		notify();
	}
}
