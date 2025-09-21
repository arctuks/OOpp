package myfirstpackage;

public class MySecondClass {
	private int firstValue; 
	private int secondValue;
	
	public MySecondClass(int firstValue, int secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}
	public MySecondClass() { this(0, 0); }
	
	public void setFirstValue(int value) { firstValue = value; }
	public void setSecondValue(int value) { secondValue = value; }

	public int getFirstValue() { return firstValue; }
	public int getSecondValue() { return secondValue; }

	public int bitwiseAND() { return firstValue & secondValue; }
}