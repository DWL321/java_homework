package Polynamial;

public class Polynomial {
	private double a;
	private double b;
	private double c;
	public Polynomial(double a,double b ,double c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}
	public void setA(double a) {
		this.a=a;
	}
	public void setB(double b) {
		this.b=b;
	}
	public void setC(double c) {
		this.c=c;
	}
	public void showPolynomial() {
		System.out.println("F(X)="+a+"x^2+"+b+"x+"+c);
	}
	public double getY(double x) {
		return a*x*x+b*x+c;
	}
	public boolean hasSolution(double y) {
		if(b*b-4*a*(c-y)>=0)return true;
		return false;
	}
	public void showSolution(double y) {
		double t=b*b-4*a*(c-y);
		if(t>0) {
			double k=Math.sqrt(t);
			double m=(-b+k)/(2*a);
			double n=(-b-k)/(2*a);
			System.out.println("solution:"+m+","+n);
		}
		else if(t<0) {
			System.out.println("no solution");
		}
		else {
			double k=(-b)/(2*a);
			System.out.println("solution:"+k);
		}
	}
}
