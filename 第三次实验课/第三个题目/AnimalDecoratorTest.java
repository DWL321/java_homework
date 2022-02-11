package project3;

public class AnimalDecoratorTest {
	public static void main(String args[]){
		Cat cat = new Cat("Tom");
		Dog dog = new Dog("Herry");
		TrainedAnimalDecorator trainedCat = new TrainedAnimalDecorator(new Cat("Tom"));
		TrainedAnimalDecorator trainedDog = new TrainedAnimalDecorator(new Dog("Herry"));
		ScientificAnimalDecorator scientificCat = new ScientificAnimalDecorator(new Cat("Tom"));
		ScientificAnimalDecorator scientificDog = new ScientificAnimalDecorator(new Dog("Herry")); 
		cat.eat();
		cat.bark();
		cat.showSkills();
		System.out.println(""); 
		dog.eat();
		dog.bark();
		dog.showSkills();
		System.out.println(""); 
		trainedCat.eat();
		trainedCat.bark();
		trainedCat.showSkills();
		System.out.println(""); 
		trainedDog.eat();
		trainedDog.bark();
		trainedDog.showSkills();
		System.out.println("");
		scientificCat.eat();
		scientificCat.bark();
		scientificCat.showSkills();
		System.out.println(""); 
		scientificDog.eat();
		scientificDog.bark();
		scientificDog.showSkills();
		System.out.println("");
	}
}
