package itba;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;


public class Output {
	
	public static void complexCollection(Collection<Complex> list){
		try(PrintWriter out = new PrintWriter("output.txt")) {
			for(Complex c: list){
				out.write(Operations.truncateDouble(c.r,5) + "\t" + Operations.truncateDouble(c.i,5) + "\n");
			}
			out.close();
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
