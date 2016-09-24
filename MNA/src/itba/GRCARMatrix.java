package itba;


public class GRCARMatrix extends Matrix {

	public GRCARMatrix(int num) {
		super(num,num);
		this.fill();
	}

	private void fill() {
		int l = 3;
		for (int i = 0; i < super.fil; i++) {
			for (int j = 0; j < super.cols; j++) {
				// | -> alt + 124
				if (i == j || (j > i && j <= (i + l))) {
					matrix[i][j] = 1;
				} else if (i == (j + 1)) {
					matrix[i][j] = -1;
				} else {
					matrix[i][j] = 0;
				}
			}
		}
	}
	


}
