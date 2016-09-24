package itba;

public class Main {

	public static void main(String[] args) {

		Matrix m = new GRCARMatrix(5);
		System.out.println("Matrix GRCAR " + m.fil + "x" + m.cols);
		m.print();
		System.out.println("Applying QR Method " + Operations.ITERATIONS + " times");

		for (int i = 0; i < Operations.ITERATIONS; i++) {
			Matrix q = Operations.calculateQR(m).get(Operations.QMATRIX);
			Matrix r = Operations.calculateQR(m).get(Operations.RMATRIX);
			m = Operations.crossProduct(r, q);
		}
		
		m.print();

	}

}
