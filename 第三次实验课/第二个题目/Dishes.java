package project2;

public class Dishes extends Food{

	public Dishes(String name, int price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void take() {
		System.out.println("The Dishes "+this.name+" is taken");
	}

}
