package itba;

import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int x = 100;
		Matrix m = new GRCARMatrix(x);
		Matrix m2 = new Matrix(5,5);
		for(int i = 0; i<25;i++){
			m2.matrix[i/5][i%5] = i+1;
		}
		
		System.out.println("Matrix GRCAR " + m.fil + "x" + m.cols);
		m.print();
		System.out.println("Applying QR Method " + Operations.ITERATIONS + " times");
		Matrix q = null;
		Matrix r = null;
		Matrix aux = null;
		boolean flag = true;
		int i;
		for (i = 0; i < Operations.ITERATIONS && flag; i++) {
			aux = m;
			q = Operations.calculateQR(m).get(Operations.QMATRIX);
			r = Operations.calculateQR(m).get(Operations.RMATRIX);
			m = Operations.crossProduct(r, q);
			if((i % x) == 0){
				System.out.println(i);
				if (Operations.getValues(m).equals(Operations.getValues(aux))){
					flag = false;
				}
			}
		}
		System.out.println(i);
		System.out.println("-----------------");
		m.print();
		System.out.println("-----------------");
		r.print();
		System.out.println("-----------------");
		q.print();
		System.out.println("-----------------");
		System.out.println("-----------------");
		Set<Complex> l = Operations.getValues(m);
		
		for(Complex c :l){
			System.out.println(c);
		}
		
	}
	
	

}
