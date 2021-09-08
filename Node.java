package SupplyChain;

import java.util.ArrayList;
import java.util.List;

public class Node implements Cloneable{

	String name;
	List connectedNodes = new ArrayList<Node>();
	int degree;
	double bc;
	
	
	
	
	public Node(String name, List connectedNodes, int degree, double bc) {
		super();
		this.name = name;
		this.connectedNodes = new ArrayList(connectedNodes);
		this.degree = degree;
		this.bc = bc;
	}


	
	public Node(Node temp) {
		
		this.name=temp.name;
		this.degree=temp.degree;
		this.bc=temp.bc;
		this.connectedNodes=new ArrayList(temp.connectedNodes);
	}


	public Node() {
		super();
		// TODO Auto-generated constructor stub
	}




	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
