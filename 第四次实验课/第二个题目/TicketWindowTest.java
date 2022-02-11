package problem2;

import java.util.ArrayList;
import java.util.List;

public class TicketWindowTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> ticketList=new ArrayList<Integer>();
		for(int i=1;i<=20;i++) {
			ticketList.add(i);
		}
		TicketWindow w1=new TicketWindow(1,ticketList);
		TicketWindow w2=new TicketWindow(2,ticketList);
		TicketWindow w3=new TicketWindow(3,ticketList);
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);
		Thread t3 = new Thread(w3);
		try { 
			t1.start();
			t2.start();
			t3.start();	
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
