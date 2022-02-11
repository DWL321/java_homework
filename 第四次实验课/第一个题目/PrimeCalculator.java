package problem1;

public class PrimeCalculator implements Runnable{
	private int lowerBound;
	private int upperBound;
	private int amount;
	private boolean hasBeenCalcualated;
	public PrimeCalculator(int lowerBound, int upperBound) {
		this.lowerBound=lowerBound;
		this.upperBound=upperBound;
		this.amount=0;
		this.hasBeenCalcualated=false;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=this.lowerBound;i<this.upperBound;i++) {
			int j;
			for(j=2;j*j<=i;j++)if(i/j*j==i)break;
			if(j*j>i&&i>1)this.amount++;
		}
		this.hasBeenCalcualated=true;
	}
	public int getAmount() {
		if(this.hasBeenCalcualated)return this.amount;
		else return -1;
	}
}
