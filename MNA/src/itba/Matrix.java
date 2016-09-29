package itba;

import javax.management.RuntimeErrorException;

public class Matrix {

	int fil = 0;
	int cols = 0;
	public double[][] matrix = null;

	public Matrix(int fil, int cols) {
		this.fil = fil;
		this.cols = cols;
		matrix = new double[fil][cols];
		fill();
	}
	
	public Matrix(Matrix other){
		this.fil = other.fil;
		this.cols = other.cols;
		this.matrix = new double[this.fil][this.cols];
		for(int i=0; i<fil; i++){
			for(int j=0; j<cols; j++){
				this.matrix[i][j] = other.matrix[i][j];
			}
		}
	}
	
	public Matrix(int fil, int cols, int n) {
		this.fil = fil;
		this.cols = cols;
		matrix = new double[fil][cols];
		fill(n);
	}
	
	public Matrix(int n){
		this.fil = n;
		this.cols = n;
		this.matrix = new double[n][n];
		for(int i=0; i<fil; i++){
			for(int j=0; j<cols; j++){
				if(i==j)
					this.matrix[i][j] = 1;
				else
					this.matrix[i][j] = 0;
			}
		}
	}


	public void print() {
		for (int i = 0; i < fil; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("\t" + Operations.truncateDouble(matrix[i][j]) + "\t");
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

	public double[] getVectorByCol(int col) {
		if (col <= 0 || col > cols)
			throw new RuntimeErrorException(null, "Invalid argument passed");

		double[] array = new double[fil];

		for (int i = 0; i < fil; i++) {
			array[i] = matrix[i][col - 1];
		}

		return array;

	}

	public double[] getVectorByFil(int fils) {
		if (fils <= 0 || fils > this.fil)
			throw new RuntimeErrorException(null, "Invalid argument passed");

		double[] array = new double[cols];

		for (int i = 0; i < cols; i++) {
			array[i] = matrix[fils-1][i];
		}
		
		return array;

	}
	
	public void setVector(double[] v, int position) {
		if (position <= 0 || position > cols)
			throw new IllegalArgumentException("Not valid position");

		if (v.length != fil)
			throw new RuntimeException("Not valid vector");

		for (int i = 0; i < fil; i++) {
			matrix[i][position - 1] = v[i];
		}
	}

	public void setInPosition(double number, int fil, int cols) {
		if (fil < 0 || fil > this.fil)
			throw new RuntimeException("Fil doesnt match");
		
		if (cols < 0 || cols > this.cols)
			throw new RuntimeException("Cols doesnt match");
		
		matrix[fil][cols]= number;

	}
	
	public double getInPosition(int fil, int cols) {
		if (fil < 0 || fil > this.fil)
			throw new RuntimeException("Fil doesnt match");
		
		if (cols < 0 || cols > this.cols)
			throw new RuntimeException("Cols doesnt match");
		
		return matrix[fil][cols];
	}
	
	public Matrix transpose(){
		if (fil != cols){
			throw new IllegalArgumentException("m != n");
		}
		for(int i=0; i<fil; i++){
			for(int j=i; j<cols; j++){
				double aux = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = aux;
			}
		}
		return this;
	}


}
