package itba;

import javax.management.RuntimeErrorException;

public class Matrix {

	int fil = 0;
	int cols = 0;
	double[][] matrix = null;

	public Matrix(int fil, int cols) {
		this.fil = fil;
		this.cols = cols;
		matrix = new double[fil][cols];
		fill();
	}
	
	public Matrix(int fil, int cols, int n) {
		this.fil = fil;
		this.cols = cols;
		matrix = new double[fil][cols];
		fill(n);
	}


	public void print() {
		for (int i = 0; i < fil; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("\t" + matrix[i][j] + "\t");
			}
			System.out.println("");
		}
	}

	private void fill() {
		fill(0);
	}
	
	private void fill(int n) {
		for (int i = 0; i < fil; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = n;
			}
		}
	}

	public double[] getVector(int col) {
		if (col <= 0 || col > cols)
			throw new RuntimeErrorException(null, "Invalid argument passed");

		double[] array = new double[fil];

		for (int i = 0; i < fil; i++) {
			array[i] = matrix[i][col - 1];
		}

		return array;

	}

	public void setVector(double[] v, int position) {
		if (position <= 0 || position > cols)
			throw new IllegalArgumentException("Not valid position");

		if (v.length != fil)
			throw new RuntimeException("Not valid vector");

		for (int i = 0; i < fil; i++) {
			matrix[i][position - 1] = Operations.truncateDouble(v[i]);
		}
	}

	public void setInPosition(double number, int fil, int cols) {
		if (fil < 0 || fil > this.fil)
			throw new RuntimeException("Fil doesnt match");
		
		if (cols < 0 || cols > this.cols)
			throw new RuntimeException("Cols doesnt match");
		
		matrix[fil][cols]= Operations.truncateDouble(number);

	}
	


}
