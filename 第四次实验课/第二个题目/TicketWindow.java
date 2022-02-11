package problem2;
import java.util.List;
import java.util.ArrayList;
public class TicketWindow implements Runnable{
	private int windowNumber;
	private static List<Integer> ticketList=new ArrayList<Integer>();
	public TicketWindow(int windowNumber, List<Integer> ticketList) {
		this.windowNumber=windowNumber;
		TicketWindow.ticketList=ticketList;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!TicketWindow.ticketList.isEmpty()) {
			try {
				synchronized(TicketWindow.ticketList) {
					if(!TicketWindow.ticketList.isEmpty()) {
						System.out.println("Window "+this.windowNumber+":Ticket "+TicketWindow.ticketList.get(0)+" is sold.");
						TicketWindow.ticketList.remove(0);
					}
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
