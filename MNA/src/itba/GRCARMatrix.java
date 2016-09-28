package itba;


public class GRCARMatrix extends Matrix {

	public GRCARMatrix(int num, int l) {
		super(num,num);
		this.fill(l);
	}

	private void fill(int l) {
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
