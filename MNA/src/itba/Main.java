package itba;

public class Main {

	public static void main(String[] args) {
		
		
		GRCARMatrix m = new GRCARMatrix(3);
		m.print();
		Matrix q = Operations.calculateQR(m).get(Operations.QMATRIX);
		System.out.println("***");
		q.print();
		Matrix r = Operations.calculateQR(m).get(Operations.RMATRIX);
		System.out.println("***");
		r.print();
		

	}
	
	
}
