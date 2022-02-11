package problem_1;

import java.text.DecimalFormat;;

public class Circle extends Shape {
	private double radius;

	public Circle(double radius) {
		super("Circle");
		this.radius=radius;
	}

	@Override
	public double getArea() {
		return this.radius*this.radius*Math.PI;
	}

	@Override
	public void showDescription() {
		System.out.println("Shape: "+this.name);
		DecimalFormat df = new DecimalFormat("#.0000");
		System.out.println("radius: "+df.format(this.radius));
		System.out.println("Area: "+df.format(this.getArea()));
		System.out.println("Perimeter: "+df.format(this.getPerimeter()));
		System.out.println("");
		return ;
	}

	public double getRadius() {
		return this.radius;
	}
	
	public void setRadius(double radius) {
		this.radius=radius;
	}
	
	public double getPerimeter() {
		return 2*this.radius*Math.PI;
	}
}
