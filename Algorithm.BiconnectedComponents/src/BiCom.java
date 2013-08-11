import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class BiCom {
	static Graph graph;

	public static void main(String[] args) throws IOException {
		File outfile=new File("doc/result3_1.txt");
		BufferedWriter bw=new BufferedWriter(new FileWriter(outfile));
		graph=new Graph("doc/data3_1.txt");			
		
		graph.BC(graph.verlist[0]);
		String a=Integer.toString(graph.c1);
		bw.write(a);
		bw.newLine();
		for(int i=0;i<graph.c1;i++){
			String a1=graph.writestring[i].substring(0,graph.writestring[i].length()-1)+")";
			bw.write(a1);
			bw.newLine();
		}
		bw.close();
	}	
	
}
