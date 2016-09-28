package itba;

import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int n;
		int x = 3; // x<n
		
		
		for(n=5; n<100; n+=(1+3*(n/10))){
			long start = System.currentTimeMillis();
			Matrix m = new GRCARMatrix(n,x);
			Matrix q = null;
			Matrix r = null;
			Matrix aux = null;
			boolean flag = true;
			int i;
			for (i = 0;flag; i++) {
				aux = m;
				q = Operations.calculateQR(m).get(Operations.QMATRIX);
				r = Operations.calculateQR(m).get(Operations.RMATRIX);
				m = Operations.crossProduct(r, q);
				if((i % n) == 0){
					if (Operations.getValues(m).equals(Operations.getValues(aux))){
						flag = false;
					}
				}
			}
			long end = System.currentTimeMillis();
			Output.times(n, end-start);
			System.out.println(n);
		}
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
		
		Output.complexCollection(l);*/
		
	}
	
	

}
