package itba;

public class GRCARMatrix {

	int n = 0;
	int[][] matrix = null;

	public GRCARMatrix(int num) {
		this.n = num;
		matrix = new int[num][num];
		fill();
	}

	public void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j + 1) 
					System.out.print(" " + matrix[i][j] + "  ");
				else
					System.out.print("  " + matrix[i][j] + "  ");
			}
			System.out.println("");
		}
	}

	private void fill() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i <= j && i != j + 1)
					matrix[i][j] = 1;
				else if (i == j + 1) 
					matrix[i][j] = -1;
				else
					matrix[i][j] = 0;
			}
		}
	}

}
