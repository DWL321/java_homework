package project3;

public class AnimalDecorator implements Animal{
	private Animal animal;
	public AnimalDecorator(Animal animal) {
		this.animal=animal;
	}
	@Override
	public String getType() {
		return this.animal.getType();
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.animal.setType(type);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.animal.getName();
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		this.animal.eat();
	}

	@Override
	public void bark() {
		// TODO Auto-generated method stub
		this.animal.bark();
	}

	@Override
	public void showSkills() {
		// TODO Auto-generated method stub
		this.animal.showSkills();
	}
}
