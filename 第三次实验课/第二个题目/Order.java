package project2;

import java.util.LinkedList;
import java.util.List;

public class Order {
	List<Food> foodList;
	int peopleAmount;
	public Order(int peopleAmount) {
		this.peopleAmount=peopleAmount;
		foodList=new LinkedList<>();
	}
	public void addFood(Food newFood) {
		foodList.add(newFood);
		System.out.println("The food \""+newFood.getName()+"\" is added to the order");
	}
	public void setPeopleAmount(int peopleAmount) {
		this.peopleAmount=peopleAmount;
	}
	public void showBill() {
		System.out.println("\nBill of the Order:");
		int allprice=0;
		for(int i=0;i<foodList.size();i++) {
			allprice+=foodList.get(i).getPrice();
			System.out.println("name: "+foodList.get(i).getName()+",price: "+foodList.get(i).getPrice()+" yuan");
		}
		try {
			if(this.peopleAmount<1)throw new ArithmeticException();
			System.out.println("Each Person should pay: "+allprice/this.peopleAmount+" yuan\n");
		}
		catch(ArithmeticException e) {
			System.out.println("Error : Should be at least one person pay for the bill\n");
		}
	}
	public void takeFood() {
		try {
			int i=0;
			if(i==foodList.size())throw new NumberFormatException();
			foodList.get(i).take();
			foodList.remove(i);
		}
		catch(NumberFormatException e) {
			System.out.println("Error : All the food have already taken\n");
		}
	}
}
