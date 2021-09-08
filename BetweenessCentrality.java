package SupplyChain;

import java.util.*;
import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
public class BetweenessCentrality {

	static List betweenessCentralityValue = new ArrayList();
public static void test() {
	
	//System.out.println(Main.x);
	
	Graph graph = new SingleGraph("Tutorial 1");
	
	
	List nameList=new ArrayList();
	 
	for(int i=0;i<Main.nodeName.size();i++) {
	 Node n = graph.addNode(Main.nodeName.get(i).toString());
	 nameList.add(n);
	 //System.out.println("Printinh node Objects in BC"+n);
	}
	
	for(int i=0;i<Main.edgeName.size();i++) {
		StringBuffer sbr = new StringBuffer(Main.edgeName.get(i).toString());
        String s1 = sbr.substring(0,2);
        String s2 = sbr.substring(2,4);
   	 System.out.println("Printinh edges in BC"+ s1 +" "+s2);

	graph.addEdge(Main.edgeName.get(i).toString(), s1, s2);
	}
	
	BetweennessCentrality bcb = new BetweennessCentrality();
    bcb.init(graph);
    bcb.compute();
    
    for(int i=0;i<nameList.size();i++) {
    	    	
    	System.out.println((Double) ((Node)nameList.get(i)).getAttribute("Cb") +" "+ nameList.get(i));
    	BetweenessCentrality.betweenessCentralityValue.add((Double) ((Node)nameList.get(i)).getAttribute("Cb"));
    	
    }
}

}
