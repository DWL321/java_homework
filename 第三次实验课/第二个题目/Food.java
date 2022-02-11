package project2;

public abstract class Food {
	String name;
	int price;
	public Food(String name,int price) {
		this.name=name;
		this.price=price;
	}
	public String getName() {
		return this.name;
	}
	public int getPrice() {
		return this.price;
	}
	public void setPrice(int price) {
		this.price=price;
	}
	public abstract void take();
}

