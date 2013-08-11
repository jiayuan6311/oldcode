
public class Edge {
	Vertex ver1,ver2;
	String name;	
	
	public Edge(Vertex v1,Vertex v2){
		ver1=v1;
		ver2=v2;
		name="("+v1.index+","+v2.index+")";
	}
}
