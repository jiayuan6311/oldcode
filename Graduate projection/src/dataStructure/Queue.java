package dataStructure;

import java.util.LinkedList;
import java.util.List;

/*
 * @author Zhang Wenbo
 * implement class queue
 */

public class Queue<T> {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Queue<String> qu=new Queue<String>();
		qu.enQueue("1");
		qu.enQueue("2");
		qu.enQueue("3");
		for(int i=qu.size();i>0;i--){
			System.out.println(qu.deQueue());
		}
	}

	private LinkedList<T> queue=new LinkedList<T>();
	
	public void enQueue(T t){
		queue.addLast(t);
	}
	
	public void enQueue(List<T> list){
		for(int i=0; i<list.size(); i++)
			queue.addLast(list.get(i));
	}
	
	public T deQueue(){
		return  queue.removeFirst();
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public T get(int i){
		return queue.get(i);
	}
	
	public int size(){
		return queue.size();
	}
	
	public boolean contains(T t){
		return queue.contains(t);
	}
}
