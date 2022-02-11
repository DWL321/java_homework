package project3;

public class ScientificAnimalDecorator extends AnimalDecorator{

	public ScientificAnimalDecorator(Animal animal) {
		super(animal);
		this.setType("scientific "+this.getType());
		// TODO Auto-generated constructor stub
	}
	public void doExperiment() {
		System.out.println("    The "+this.getType()+" \""+this.getName()+"\" is doing experiment.");
	}
	public void writePaper() {
		System.out.println("    The "+this.getType()+" \""+this.getName()+"\" is writing the paper.");
	}
	public void showSkills() {
		System.out.println("The "+this.getType()+" \""+this.getName()+"\" is showing its skills:");
		doExperiment();
		writePaper();
	}
}
