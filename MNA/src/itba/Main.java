package itba;

import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int n = 100; // Size of matrix (nxn)
		int x = 3; // Amount of 1's
		Matrix m = new GRCARMatrix(n,x);
		
		long start = System.currentTimeMillis();
		Matrix res = Operations.calculateQR2(m);
		System.out.println("time: " + ((double)(System.currentTimeMillis()-start))/1000);

		Set<Complex> l = Operations.getValues(res);
		
		for(Complex c :l){
			System.out.println(c);
		}
		
		Output.complexCollection(l);
		
	}
	
	

}
