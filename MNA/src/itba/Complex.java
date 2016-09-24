package itba;

public class Complex {
		double r;
		double i;
		
		public Complex(double r, double i){
			this.r = r;
			this.i = i;
		}
		public Complex(Complex conjugate){
			r = conjugate.r;
			i = conjugate.i*-1;
		}
		public Complex conjugate(){
			this.i *= -1;
			return this;
		}
		public String toString(){
			if(i<0)
				return Operations.truncateDouble(r,5) + " - " + Operations.truncateDouble(Math.abs(i),5) + "i";
			return Operations.truncateDouble(r,5) + " + " + Operations.truncateDouble(Math.abs(i),5) + "i";
		}
		public int hashCode(){
			return (int)(i*31 + r*27);
		}
}
