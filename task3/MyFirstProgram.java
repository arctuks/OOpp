class MyFirstClass {
	public static void main(String[] s) {
		MySecondClass o = new MySecondClass();		
		System.out.println(o.bitwiseAND());

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				o.setFirstValue(i);
				o.setSecondValue(j);

				System.out.print(o.bitwiseAND());
				System.out.print(" ");
			}

			System.out.println();
		}
	}
}

class MySecondClass {
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