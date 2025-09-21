import myfirstpackage.*;

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