package itba;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Operations {
	
	public static final String QMATRIX = "Q";
	public static final String RMATRIX = "R";
	public static final int ITERATIONS = 10000000;

	/**
	 *
	 * @param v1 vector 1
	 * @param v2 vector 2
	 * @return the dot product between 1 and 2
	 */
	public static double dotProduct(double[] v1, double[] v2) {

		if (v1.length != v2.length)
			throw new RuntimeException("Length doesnt match");

		double count = 0;

		for (int i = 0; i < v1.length; i++) {
			count += v1[i] * v2[i];
		}

		return count;

	}

	/**
	 *
	 * @param v1 vector
	 * @param alpha scalar
	 * @return the alpha product
	 */
	public static double[] alphaProduct(double[] v1, double alpha) {
		double[] vaux = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			vaux[i] = alpha * v1[i];
		}
		return vaux;
	}

	/**
	 *
	 * @param v vector
	 * @return the norm 2 of the vector
	 */
	public static double getNorm2(double[] v) {
		double sum = 0;
		for (double d : v) {
			sum += Math.pow(d, 2);
		}
		return Math.sqrt(sum);
	}

	/**
	 *
	 * @param v1 vector 1
	 * @param v2 vector 2
	 * @return the sum between the vectors
	 */
	public static double[] sum(double[] v1, double[] v2){
		if(v1.length!=v2.length)
			throw new RuntimeException("Sum not valid");
		
		double[] aux = new double[v1.length];
		for(int i = 0 ; i < v1.length ; i++){
			aux[i] = v1[i]+v2[i];
		}
		return aux;
	}

	/**
	 * This method calculates the matrix Q and R using Gram-Schmidt
	 * @param m the matrix to
	 * @return a map with the qr descomposition
	 */
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
	
	public static Matrix calculateQR2(final Matrix m){
		//return calculateQR2rec(null, m, ITERATIONS);
		return calculateQR2iter(m, ITERATIONS);
	}
	
	public static Matrix calculateQR2iter(Matrix m, int iter){
		Matrix prev = null;
		long start = System.currentTimeMillis();
		double[][] g = new double[m.fil-1][2];
		while(iter>0){
			for(int x=0; x<m.fil-1; x++){
				double x1 = m.getInPosition(x, x);
				double x3 = m.getInPosition(x+1, x);
				double r = Math.sqrt(Math.pow(x1, 2)+Math.pow(x3,2));
				double c = x1/r;
				double s = -x3/r;
				g[x][0] = c;
				g[x][1] = s;

				for(int j=0;j<m.cols;j++){
					double upper = m.getInPosition(x, j);
					double lower = m.getInPosition(x+1, j);
					m.setInPosition(c*upper-s*lower, x, j);
					m.setInPosition(s*upper+c*lower, x+1, j);
				}
			}
			for(int x=0; x<m.fil-1; x++){
				for(int i=0; i<m.fil; i++){
					double c = g[x][0];
					double s = g[x][1];
					double left = m.getInPosition(i, x);
					double right = m.getInPosition(i, x+1);
					m.setInPosition(left*c-right*s, i, x);
					m.setInPosition(left*s+right*c, i, x+1);
				}
			}
			if(iter%m.fil==0 && prev!=null){

				if(Operations.getValues(m).equals(Operations.getValues(prev))){
					Output.times(m.fil,System.currentTimeMillis() - start,ITERATIONS - iter);
					return m;
				}
			}
			prev = new Matrix(m);
			iter--;
		}
		Output.times(m.fil,System.currentTimeMillis() - start,ITERATIONS - iter);
		return m;
	}

	/**
	 * Calculate the eigenvalues from the given matrix
	 * @param m The matrix
	 * @return A list with the eigenvalues in complex form
	 */
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

	/**
	 * Gets the root from the polinomial
	 * @param a
	 * @param b
	 * @param c
	 * @return A list with the roots
	 */
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

	/**
	 *
	 * @param m the matrix 2x2
	 * @return the determinant
	 */
	private static double determinant(double[][] m) {
		return m[0][0]*m[1][1]-m[0][1]*m[1][0];
	}

	public static double truncateDouble(double n){
		return truncateDouble(n,4);
	}

	/**
	 * Rounds the double for showing purpose
	 * @param n the double
	 * @param digits the number of digits
	 * @return the new double with n decimals
	 */
	public static double truncateDouble(double n, int digits){
		
		if(digits<=0)
			throw new RuntimeException("Invalid digit");

		BigDecimal bd = new BigDecimal(n);
		bd = bd.setScale(digits, RoundingMode.HALF_UP);
		return bd.doubleValue();
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
