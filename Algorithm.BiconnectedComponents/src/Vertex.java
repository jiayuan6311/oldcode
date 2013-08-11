
public class Vertex {
	int index,dfsnum,high;
	int parent;
	String name;
	
	public Vertex(int in)   // constructor
	{
		index = in;
		dfsnum = 0;
		name=index+"";
    }
	public void setparent(int p){
		parent=p;
	}
}
