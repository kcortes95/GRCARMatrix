package itba;

import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int n = 150; // Size of matrix (nxn)
		int x = 3; // Amount of 1's

		Matrix res = null;
		for(int i = 5; i<n; i++){
			long start = System.currentTimeMillis();
			Matrix m = new GRCARMatrix(i,x);
			res = Operations.calculateQR2(m);
			System.out.println("Size: " + i + " time: " + ((double)(System.currentTimeMillis()-start))/1000);
		}



		Set<Complex> l = Operations.getValues(res);
		
		for(Complex c :l){
			System.out.println(c);
		}
		
		Output.complexCollection(l);
		
	}
	
	

}
