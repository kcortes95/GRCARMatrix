package itba;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		Matrix m = new GRCARMatrix(10);
		Matrix m2 = new Matrix(3,3);
		for(int i = 0; i<9;i++){
			m2.matrix[i/3][i%3] = i+1;
		}
		
		System.out.println("Matrix GRCAR " + m.fil + "x" + m.cols);
		m.print();
		System.out.println("Applying QR Method " + Operations.ITERATIONS + " times");
		Matrix q = null;
		Matrix r = null;
		for (int i = 0; i < 10000; i++) {
			q = Operations.calculateQR(m).get(Operations.QMATRIX);
			r = Operations.calculateQR(m).get(Operations.RMATRIX);
			m = Operations.crossProduct(r, q);
		}
		System.out.println("-----------------");
		m.print();
		System.out.println("-----------------");
		r.print();
		System.out.println("-----------------");
		q.print();
		System.out.println("-----------------");
		System.out.println("-----------------");
		List<Complex> l = Operations.getValues(m);
		
		for(int i = 0; i<l.size();i++){
			System.out.println(l.get(i));
		}
		
	}

}
