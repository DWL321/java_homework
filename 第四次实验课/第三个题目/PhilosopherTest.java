package problem3;

public class PhilosopherTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Philosopher p1=new Philosopher(1);
		Philosopher p2=new Philosopher(2);
		Philosopher p3=new Philosopher(3);
		Philosopher p4=new Philosopher(4);
		Philosopher p5=new Philosopher(5);
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);
		Thread t3 = new Thread(p3);
		Thread t4 = new Thread(p4);
		Thread t5 = new Thread(p5);
		try { 
			t1.start();
			t2.start();
			t3.start();	
			t4.start();
			t5.start();	
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
