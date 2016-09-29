package itba;

import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int n = 10;
		int x = 3;
		Matrix m = new GRCARMatrix(n,x);
		Matrix m2 = new Matrix(5,5);
		for(int i = 0; i<25;i++){
			m2.matrix[i/5][i%5] = i+1;
		}
		
		System.out.println("Matrix GRCAR " + m.fil + "x" + m.cols);
		m.print();
		System.out.println("Applying QR Method " + Operations.ITERATIONS + " times");
		
		int i;
		long start = System.currentTimeMillis();
		Matrix q = null;
		Matrix r = null;
		Matrix aux = null;
		boolean flag = true;
		for (i = 0; i < Operations.ITERATIONS && flag; i++) {
			aux = m;
			Map<String,Matrix> map = Operations.calculateQR2(m);
			q = map.get(Operations.QMATRIX);
			r = map.get(Operations.RMATRIX);
			m = Operations.crossProduct(r, q);
			if((i % n) == 0){
				System.out.println(i);
				if (Operations.getValues(m).equals(Operations.getValues(aux))){
					flag = false;
				}
			}
			
		}
		System.out.println("time: " + (System.currentTimeMillis()-start));
		/*System.out.println(i);
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
		}*/
		
		//Output.complexCollection(l);
		
	}
	
	

}
