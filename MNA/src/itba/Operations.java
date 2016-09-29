package itba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Operations {
	
	public static final String QMATRIX = "Q";
	public static final String RMATRIX = "R";
	public static final int ITERATIONS = 10000;
	
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
			
			double[] v = m.getVectorByCol(i);
						
			double[] emonio = v;

			for (int j = 1; j < i; j++) {
				double prodInter = Operations.dotProduct(v, Q.getVectorByCol(j));
				double[] vaux = Operations.alphaProduct(Q.getVectorByCol(j), prodInter*-1);
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
	
	public static Map<String,Matrix> calculateQR2(final Matrix m){
		Map<String,Matrix> ret = new HashMap<>();
		Matrix Q = new Matrix(m.fil);
		Matrix R = new Matrix(m);
		
		Matrix[] G = new Matrix[m.fil-1];
		
		for(int x=0; x<m.fil-1; x++){
			double x1 = R.getInPosition(x, x);
			double x3 = R.getInPosition(x+1, x);
			double r = Math.sqrt(Math.pow(x1, 2)+Math.pow(x3,2));
			double c = x1/r;
			double s = -x3/r;
			G[x] = new Matrix(m.fil, m.cols);
			for(int i=0; i<m.fil; i++){
				for(int j=0; j<m.cols; j++){
					if(i==j){
						if(i==x+1 || j==x){
							G[x].setInPosition(c, i, j);
						}else{
							G[x].setInPosition(1, i, j);
						}
					}else{
						if(i == x && j == x+1){
							G[x].setInPosition(-s, i, j);
						}else if(i == x+1 && j == x){
							G[x].setInPosition(s, i, j);
						}else{
							G[x].setInPosition(0, i, j);
						}
					}
				}
			}
			R = crossProduct(G[x], R);
		}
		for(int x=0; x<m.fil-1; x++){
			Q = crossProduct(Q,G[x].transpose());
		}
		
		ret.put("Q", Q);
		ret.put("R", R);
		return ret;
	}
	
	public static Set<Complex> getValues(Matrix m){
		Set<Complex> val = new HashSet<>();
		for (int i = 0; i < m.cols - (m.cols % 2); i += 2) {
			double[][] aux = new double[2][2];
			aux[0][0] = m.getInPosition(i, i);
			aux[0][1] = m.getInPosition(i, i+1);
			aux[1][0] = m.getInPosition(i+1, i);
			aux[1][1] = m.getInPosition(i+1, i+1);
			val.addAll(roots(1, -aux[0][0] - aux[1][1], determinant(aux)));
		}
		if (m.cols % 2 == 1) {
			val.add(new Complex(m.getInPosition(m.fil-1, m.cols -1), 0));
		} 
		return val;
	}
	
	private static List<Complex> roots(double a, double b, double c) {
		List<Complex> res = new ArrayList<Complex>();
		double x = (b * b) - (4 * a * c);
		if (x > 0) {
			res.add(0, new Complex(((-b + Math.sqrt(x)) / 2 * a), 0));
			res.add(1, new Complex(((-b - Math.sqrt(x)) / 2 * a), 0));
		} else {
			res.add(0, new Complex(-b / (2 * a), Math.sqrt(Math.abs(x))
					/ (2 * a)));
			res.add(1, new Complex(res.get(0)));
		}
		return res;
	}

	private static double determinant(double[][] m) {
		return m[0][0]*m[1][1]-m[0][1]*m[1][0];
	}

	public static double truncateDouble(double n){
		return truncateDouble(n,4);
	}
	
	public static double truncateDouble(double n, int digits){
		
		if(digits<=0)
			throw new RuntimeException("Invalid digit");
		
		double digit = Math.pow(10, digits);
		double number = n;
		int aux = (int) (number * digit);// 1243
		double result = aux / digit;// 12.43
		return result;
	}
	
	public static Matrix crossProduct(Matrix a, Matrix b){
		if(a.cols != b.fil)
			throw new RuntimeException("cols and fil doesnt match");
		
		Matrix ret = new Matrix(a.fil, b.cols);
		
		for(int i = 0 ; i < ret.fil ; i++){
			for(int j = 0 ; j < ret.cols ; j++){
				double[] v1 = a.getVectorByFil(i+1);
				double[] v2 = b.getVectorByCol(j+1);
				double number = Operations.dotProduct(v1, v2);
				ret.setInPosition(number, i, j);
			}
		}
		return ret;
	}

}
