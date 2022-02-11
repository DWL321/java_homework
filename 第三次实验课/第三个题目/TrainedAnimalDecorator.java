package project3;

public class TrainedAnimalDecorator extends AnimalDecorator{

	public TrainedAnimalDecorator(Animal animal) {
		super(animal);
		this.setType("trained "+this.getType());
		// TODO Auto-generated constructor stub
	}
	public void shakeHands() {
		System.out.println("    The "+this.getType()+" \""+this.getName()+"\" is shaking hands with you.");
	}
	public void sitDown() {
		System.out.println("    The "+this.getType()+" \""+this.getName()+"\" sit down on the floor.");
	}
	public void showSkills() {
		System.out.println("The "+this.getType()+" \""+this.getName()+"\" is showing its skills:");
		shakeHands();
		sitDown();
	}
}
