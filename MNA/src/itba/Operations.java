package itba;

import java.util.HashMap;
import java.util.Map;

public class Operations {
	
	public static final String QMATRIX = "Q";
	public static final String RMATRIX = "R";

	public static double dotProduct(double[] v1, double[] v2) {

		if (v1.length != v2.length)
			throw new RuntimeException("Length doesnt match");

		double count = 0;

		for (int i = 0; i < v1.length; i++) {
			count += v1[i] * v2[i];
		}

		return count;

	}

	public static double[] alphaProduct(double[] v1, double alpha) {
		double[] vaux = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			vaux[i] = alpha * v1[i];
		}
		return vaux;
	}

	public static double getNorm2(double[] v) {
		double sum = 0;
		for (double d : v) {
			sum += Math.pow(d, 2);
		}
		return Math.sqrt(sum);
	}
	
	public static double[] sum(double[] v1, double[] v2){
		if(v1.length!=v2.length)
			throw new RuntimeException("Sum not valid");
		
		double[] aux = new double[v1.length];
		for(int i = 0 ; i < v1.length ; i++){
			aux[i] = v1[i]+v2[i];
		}
		return aux;
	}

	public static Map<String,Matrix> calculateQR(Matrix m) {

		Map<String,Matrix> ret = new HashMap<>();
		Matrix Q = new Matrix(m.fil,m.cols);
		Matrix R = new Matrix(m.cols, m.cols);
				
		for (int i = 1; i <= m.cols; i++) {
			
			double[] v = m.getVector(i);
						
			double[] emonio = v;

			for (int j = 1; j < i; j++) {
				double prodInter = Operations.dotProduct(v, Q.getVector(j));
				double[] vaux = Operations.alphaProduct(Q.getVector(j), prodInter*-1);
				emonio = Operations.sum(emonio, vaux);
				R.setInPosition(prodInter, j-1, i-1);
			}
			double norm = Operations.getNorm2(emonio);
			R.setInPosition(norm, i-1, i-1);
			double[] a = Operations.alphaProduct(emonio, 1 / norm);
			Q.setVector(a, i);
		}
		ret.put("Q", Q);
		ret.put("R", R);
		return ret;
	}
	
	private static void printVector(double[] vector){
		System.out.println("IMPRIMO VECTOR: ");
		for(double v: vector){
			System.out.println(v);
		}
	}

}
