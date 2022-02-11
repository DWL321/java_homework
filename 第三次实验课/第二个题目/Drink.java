package project2;

public class Drink extends Food{

	public Drink(String name, int price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void take() {
		System.out.println("The Drink "+this.name+" is taken");
	}

}
