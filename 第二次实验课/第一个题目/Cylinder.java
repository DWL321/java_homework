package problem_1;

import java.text.DecimalFormat;

public class Cylinder extends Shape {
	private double radius;
	private double height;
	
	public Cylinder(double radius,double height) {
		super("Cylinder");
		this.height=height;
		this.radius=radius;
	}

	@Override
	public double getArea() {
		return Math.PI*this.radius*(this.radius+this.height)*2;
	}

	@Override
	public void showDescription() {
		System.out.println("Shape: "+this.name);
		DecimalFormat df = new DecimalFormat("#.0000");
		System.out.println("radius: "+df.format(this.radius));
		System.out.println("height: "+df.format(this.height));
		System.out.println("Area: "+df.format(this.getArea()));
		System.out.println("Volume: "+df.format(this.getVolume()));
		System.out.println("");
		return ;
	}
	
	public double getHeight() {
		return this.height;
	}
	public double getRadius() {
		return this.radius;
	}
	public void setHeight(double height) {
		this.height=height;
	}
	public void setRadius(double radius) {
		this.radius=radius;
	}
	public double getVolume() {
		return Math.PI*this.radius*this.radius*this.height;
	}
}
