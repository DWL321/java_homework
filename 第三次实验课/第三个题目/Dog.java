package project3;

public class Dog implements Animal {
	private String type;
	private String name;
	public Dog(String name) {
		this.name=name;
		this.type="Dog";
	}
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type=type;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("The "+this.type+" \""+this.name+"\" is eating the food");
	}

	@Override
	public void bark() {
		// TODO Auto-generated method stub
		System.out.println("Woof Woof Woof");
	}

	@Override
	public void showSkills() {
		// TODO Auto-generated method stub
		System.out.println("The "+this.type+" \""+this.name+"\" don't have any skill.");
	}

}
