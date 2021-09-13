package SupplyChain;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Main {
	static List nodeName=new ArrayList();
	static List edgeName=new ArrayList();
	
	public static void sort(List<Node> nodeObjects) throws CloneNotSupportedException{
		
		boolean sorted = false;
		
		while (sorted == false) {
			sorted = true;
			for(int i=1;i<nodeObjects.size();i++) {
				
				if(nodeObjects.get(i).degree > nodeObjects.get(i-1).degree) {
					//System.out.println("Node i = "+nodeObjects.get(i).name);
					
				Node temp = new Node();
				temp = (Node)nodeObjects.get(i).clone();
				nodeObjects.set(i,(Node)nodeObjects.get(i-1).clone());
				nodeObjects.set(i-1,(Node) temp.clone());
				sorted = false;
				}
				
			}
		}
		//return nodeObjects;
		
	}
	
	//sorting nodeobjects based on betweeness centrality
public static void sortBasedOnBetweenessCentrality(List<Node> nodeObjects) throws CloneNotSupportedException{
		
		boolean sorted = false;
		
		while (sorted == false) {
			sorted = true;
			for(int i=1;i<nodeObjects.size();i++) {
				
				if(nodeObjects.get(i).bc > nodeObjects.get(i-1).bc) {
					//System.out.println("Node i = "+nodeObjects.get(i).name);
					
				Node temp = new Node();
				temp = (Node)nodeObjects.get(i).clone();
				nodeObjects.set(i,(Node)nodeObjects.get(i-1).clone());
				nodeObjects.set(i-1,(Node) temp.clone());
				sorted = false;
				}
				
			}
		}
		//return nodeObjects;
		
	}
	

public static void recalculateBC(List<Node> copyNodeObjects){
	
	//getting edges and nodes again
	
	//edges
		List edges= new ArrayList();
		for(int i=0;i<copyNodeObjects.size();i++) {
			
			//get connected nodes first
			for(int j=0;j<((Node)copyNodeObjects.get(i)).connectedNodes.size();j++) {
				
				edges.add(((Node)copyNodeObjects.get(i)).name.toString()+((Node)copyNodeObjects.get(i)).connectedNodes.get(j));
				System.out.println("Edeges"+edges);
			}
		}
		
		//System.out.println("size = "+ edges.size());
		//removing duplicates from edges
		
		for(int ia=0;ia<edges.size();ia++) {
			
			//System.out.println("Original string = "+edges.get(ia)+" "+edges.size());
	        StringBuffer sbr = new StringBuffer(edges.get(ia).toString());
	        String s1 = sbr.substring(0,2);
	        String s2 = sbr.substring(2,4);
	       // System.out.println("Reversed string"+ s2+s1);
	        
	        if(edges.contains(s2+s1)) {
	        	
	        	edges.remove(ia);
	        	ia = ia-1;
	        	
	        }

		}
		
		//Main.x=new ArrayList(copyNodeObjects);
		
		for(int i=0;i<copyNodeObjects.size();i++) {
			
			Main.nodeName.add(((Node)copyNodeObjects.get(i)).name);
		}
		
		Main.edgeName = edges;
		
		//BetweenessCentrality.test();	
		
		List betweenessOfNode = new ArrayList(BetweenessCentrality.betweenessCentralityValue);
		
		for(int i=0;i<betweenessOfNode.size();i++) {
			
			double x = (double)betweenessOfNode.get(i);
			betweenessOfNode.set(i,x/2);
			//((Node)copyNodeObjects.get(i)).bc = (double) betweenessOfNode.get(i);
			System.out.println("Betweeness centrality of each node"+betweenessOfNode.get(i));
		}
	
	
}


	//removing nodes randomly
public static void removeNodesRandom(List<Node> nodes){
		
		//removes node 1 at a time from the passed list
	//int randomNum = ThreadLocalRandom.current().nextInt(0, nodes.size()-1 + 1);
	Random rand = new Random();
   int randomNum = 0 + rand.nextInt((nodes.size()-1 - 0) + 1);	
	int index = randomNum;
		String n = ((Node)nodes.get(index)).name;
		nodes.remove(index);
		System.out.println("In remove n = "+n);

        for(int i =0; i<nodes.size();i++) {
        	
        	List m = nodes.get(i).connectedNodes;
        	
        	if(m.contains(n)){
        		
        		
        		int indexs = nodes.get(i).connectedNodes.indexOf(n);
        		//System.out.println("In if indexOf"+ indexs);
        		nodes.get(i).connectedNodes.remove(indexs);
        	}
        	
        }
        
	}
	

 	public static void removeNode(List<Node>nodes,String name) {
 		
 		
 		//print(nodes);
 		int index = -1;
 		for(int i=0;i<nodes.size();i++) {
 			
 			if(nodes.get(i).name.equals(name))
 				index = i;
 			//boolean flag = true;
 		}
 		nodes.remove(index);
 		System.out.println("Removing node based on given name"+name+"index = "+index);
 		
 		//removing node from connected nodes
 		for(int i =0; i<nodes.size();i++) {
        	
        	List m = nodes.get(i).connectedNodes;
        	
        	if(m.contains(name)){
        		
        		
        		int indexs = nodes.get(i).connectedNodes.indexOf(name);
        		//System.out.println("In if indexOf"+ indexs);
        		nodes.get(i).connectedNodes.remove(indexs);
        	}
        	
        }
 		
 	}
	

	//removing nodes based on sorted arraylist
	public static void removeNodes(List<Node> nodes){
		
		
		//List nodes = new ArrayList(nodeObjects);
		//removes node 1 at a time from the passed list
		int index = 0;
		String n = ((Node)nodes.get(index)).name;
		nodes.remove(index);
		System.out.println("In remove n = "+n);

        for(int i =0; i<nodes.size();i++) {
        	
        	List m = nodes.get(i).connectedNodes;
        	
        	if(m.contains(n)){
        		
        		
        		int indexs = nodes.get(i).connectedNodes.indexOf(n);
        		//System.out.println("In if indexOf"+ indexs);
        		nodes.get(i).connectedNodes.remove(indexs);
        	}
        	
        }
        
	}
	
	public static void recalculateDegree(List<Node> nodeObjects){
		
		//recalculate degree of the network
	
        for(int i =0; i<nodeObjects.size();i++) {
        	
        	nodeObjects.get(i).degree = nodeObjects.get(i).connectedNodes.size();
        }
        
	}
	
	
	//swapping strings
	
	public static String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
	
	
	//creating permutations
	
	 public static void permute(String str, int l, int r, List combinations)
	    {
	        if (l == r) {
	           // System.out.println(str);
	            combinations.add(str);
	            
	            }
	        else {
	            for (int i = l; i <= r; i++) {
	               
	            //	System.out.println("before swap 1 str: "+str);
	            	
	            	str = swap(str, l, i);
	              //  System.out.println("swap 1: "+str);
	                
	                permute(str, l + 1, r,combinations);
	                
	            	//System.out.println("before swap 2 str: "+str);

	                str = swap(str, l, i);
	                
	              //  System.out.println("swap 2: "+str);

	                
	            }
	        }
	    }
	  
	
	

	//printing the data
	public static void print(List<Node> copyNodeObjects) {
	
		for(int i=0;i<copyNodeObjects.size();i++) {
		
		System.out.println( "Printing the nodes"+((Node)copyNodeObjects.get(i)).name + "   "+ ((Node)copyNodeObjects.get(i)).degree);
		System.out.println("Node Objects : "+ ((Node)copyNodeObjects.get(i)).connectedNodes);

		//store the nodes in descending order of degree in a list
		
	}
	}
	
	public static int retruningNodeIndex(String node, List<Node> Nodeobjects) {
		
		for (int i=0;i<Nodeobjects.size();i++) {
			
			if(Nodeobjects.get(i).name.equals(node))
				return i;
		}
		return 0;
	}
	
	//printing the data
		public static int sizeOfLargestConnectedComponent(List<Node> NodeObjects) {
			
			List Megalist;
			//List Templist;
			int lcc =0;
			for(int i=0;i<NodeObjects.size();i++) {
				
				Megalist = new ArrayList (NodeObjects.get(i).connectedNodes);
				Megalist.add(NodeObjects.get(i).name);
				//Templist = NodeObjects.get(i).connectedNodes;
			//	System.out.println("Megalist = "+ Megalist + " i = "+i + " NodeObjects = "+ NodeObjects.get(i).name);
				for(int j=0;j<Megalist.size();j++) {
					
					int index = retruningNodeIndex(Megalist.get(j).toString(),NodeObjects);
					Node a = NodeObjects.get(index);
				//	System.out.println("a = "+a.name+ " "+a.connectedNodes.size());
					
					for(int k=0;k<a.connectedNodes.size();k++) {
						
						if(!(Megalist.contains(a.connectedNodes.get(k)))){
							
					//		System.out.println(" in if "+ a.connectedNodes.get(k));
							
					        Megalist.add(a.connectedNodes.get(k));
					        
					        //Templist.add( a.connectedNodes.get(k));


						}
					}
					
/*for(int x=0;x<Megalist.size();x++) {
						
						System.out.println(" Megalist element = "+ Megalist.get(x));
					}*/
					
				}
				
				if(Megalist.size() > lcc) {
					lcc = Megalist.size();
					for(int x=0;x<Megalist.size();x++) {
						
						//System.out.println(" Megalist element = "+ Megalist.get(x));
					}
					
					System.out.println("lcc = "+lcc+" megalist size = "+Megalist.size());;
				}
			}
			
			
			return lcc;
			
		}

	
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		//take total number of nodes from user
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter total number of nodes in the supply chain\n");
		int total_nodes = sc.nextInt();
		
		System.out.println("Total number of nodes in supply chain = "+total_nodes);
		List nodeObjects = new ArrayList<Node>();

		
		//creating the node objects
		for(int i=1; i<=total_nodes; i++) {
			
			Node n = new Node();
			n.name = "n"+i;
			
			System.out.println("Enter total number of connected nodes for the node "+ n.name);
			int connected_nodes = sc.nextInt();
		
			sc.nextLine();
			for(int j=0;j<connected_nodes;j++) {
				
				//creating connection list to give input to list
				System.out.println("Enter the name of connected nodes");
				Node m = new Node();
				m.name= sc.nextLine();
				
				n.connectedNodes.add(m.name);
					
			}
			
			n.degree = n.connectedNodes.size();
			nodeObjects.add(n);
			
			
			}
		
		for(int i =0;i<nodeObjects.size();i++) {
			
			System.out.println("Node Objects : "+ ((Node)nodeObjects.get(i)).name + " with degree " + ((Node)nodeObjects.get(i)).degree );
			System.out.println("Node Objects : "+ ((Node)nodeObjects.get(i)).connectedNodes);
		}
	

 //step 2 calculate robustness of the existing supply chain
	
	
	//function that calculates degree of every node
		//int highestDegree = -12345;
		List copyNodeObjects = new ArrayList();
		for(int i=0; i<nodeObjects.size();i++) {
			
			copyNodeObjects.add(new Node((Node)nodeObjects.get(i)));
			
		}
		//List<Node> copyNodeObjects.connectedNodes = new ArrayList<Node>(nodeObjects.connectedNodes);
		
		System.out.println("copyNodeObjects = "+copyNodeObjects.size());
		// copyNodeObjects = nodeObjects;
		 int originalSizeOfObject = nodeObjects.size();
				
	int lcc;
	double robustness = 0;
	
	//sort objects in a list based on descending order of degree
	
	
	
	
	//edges
	List edges= new ArrayList();
	for(int i=0;i<copyNodeObjects.size();i++) {
		
		//get connected nodes first
		for(int j=0;j<((Node)copyNodeObjects.get(i)).connectedNodes.size();j++) {
			
			edges.add(((Node)copyNodeObjects.get(i)).name.toString()+((Node)copyNodeObjects.get(i)).connectedNodes.get(j));
			System.out.println("Edeges"+edges);
		}
	}
	
	//System.out.println("size = "+ edges.size());
	//removing duplicates from edges
	
	for(int ia=0;ia<edges.size();ia++) {
		
		//System.out.println("Original string = "+edges.get(ia)+" "+edges.size());
        StringBuffer sbr = new StringBuffer(edges.get(ia).toString());
        String s1 = sbr.substring(0,2);
        String s2 = sbr.substring(2,4);
       // System.out.println("Reversed string"+ s2+s1);
        
        if(edges.contains(s2+s1)) {
        	
        	edges.remove(ia);
        	ia = ia-1;
        	
        }

	}
	
	//Main.x=new ArrayList(copyNodeObjects);
	
	for(int i=0;i<copyNodeObjects.size();i++) {
		
		Main.nodeName.add(((Node)copyNodeObjects.get(i)).name);
	}
	
	Main.edgeName = edges;
	
	
	//executing HDA degree
	for(int index=copyNodeObjects.size(); index>0;index--) {
		System.out.println("index = "+index+"size of network = "+ copyNodeObjects.size());
	
	print(copyNodeObjects);
	sort(copyNodeObjects);	
	System.out.println("*************After sorting***********");
	print(copyNodeObjects);
	removeNodes(copyNodeObjects);
	print(copyNodeObjects);
	System.out.println("*************After removing nodes***********");
	recalculateDegree(copyNodeObjects);
	System.out.println("*************After recalculating degrees***********");

	print(copyNodeObjects);
		
	//check size of largest connected components
	lcc = sizeOfLargestConnectedComponent(copyNodeObjects);
	System.out.println("Returned LCC = "+ lcc+ " "+ (double)lcc/(double)originalSizeOfObject);
	robustness = robustness + ((double)lcc/(double)originalSizeOfObject);
	System.out.println("robustness = "+ robustness);
	}
	robustness = robustness/(double)originalSizeOfObject;
	System.out.println("final robustness of network = "+ robustness);
	
	//*************Random attack**********
	System.out.println("Creating attack based on random node removal");
	
	print(nodeObjects);
	
	List copyNodes = new ArrayList();
	
	//List nodes = new ArrayList();

	for(int i=0; i<nodeObjects.size();i++) {
		
		copyNodes.add(new Node((Node)nodeObjects.get(i)));
		
	}
	
	for(int index=copyNodes.size(); index>0;index--) {
		System.out.println("index = "+index+"size of network = "+ copyNodes.size());
	
	print(copyNodes);
	removeNodesRandom(copyNodes);
	print(copyNodes);
	System.out.println("*************After removing nodes***********");
	print(copyNodes);
		
	//check size of largest connected components
	lcc = sizeOfLargestConnectedComponent(copyNodes);
	System.out.println("Returned LCC = "+ lcc+ " "+ (double)lcc/(double)originalSizeOfObject);
	robustness = robustness + ((double)lcc/(double)originalSizeOfObject);
	System.out.println("robustness = "+ robustness);
	}
	
	robustness = robustness/(double)originalSizeOfObject;
	System.out.println("final robustness of network = "+ robustness);

	//betweeness centrality attack
	System.out.println("Creting attack based on betweeness centrality ");	
	
	BetweenessCentrality.test();	
	
	List betweenessOfNode = new ArrayList(BetweenessCentrality.betweenessCentralityValue);
	
	for(int i=0;i<betweenessOfNode.size();i++) {
		
		double x = (double)betweenessOfNode.get(i);
		betweenessOfNode.set(i,x/2);
		System.out.println("Betweeness"+ betweenessOfNode.get(i));
	}
	
	//creating new list of node objects and adding value of betweeness centrality for each object
	
	List listNodes = new ArrayList();
	
	for(int i=0; i<nodeObjects.size();i++) {
		
		listNodes.add(new Node((Node)nodeObjects.get(i)));
		
	}
	
	for(int i =0;i<listNodes.size();i++) {
		
		((Node)listNodes.get(i)).bc = (double) betweenessOfNode.get(i);
		System.out.println("Betweeness centrality of each node"+((Node)listNodes.get(i)).bc);
	}
	
	/*for(int index=listNodes.size(); index>0;index--) {
		System.out.println("index = "+index+"size of network = "+ listNodes.size());
	
	print(listNodes);
	sortBasedOnBetweenessCentrality(listNodes);	
	System.out.println("*************After sorting***********");
	print(listNodes);
	removeNodes(listNodes);
	print(listNodes);
	System.out.println("*************After removing nodes***********");
	recalculateBC(listNodes);
	System.out.println("*************After recalculating degrees***********");

	print(copyNodeObjects);
		
	//check size of largest connected components
	lcc = sizeOfLargestConnectedComponent(copyNodeObjects);
	System.out.println("Returned LCC = "+ lcc+ " "+ (double)lcc/(double)originalSizeOfObject);
	robustness = robustness + ((double)lcc/(double)originalSizeOfObject);
	System.out.println("robustness = "+ robustness);
	}
	robustness = robustness/(double)originalSizeOfObject;
	System.out.println("final robustness of network = "+ robustness);
	*/
	
	
	
	
	//creating exhaustive combinations
	
	/* take the name of nodes as string  */
	List nodes = new ArrayList();
	List combinations = new ArrayList();
	//ArrayList<Double> list = new ArrayList<>();
	ArrayList<Double> finalRobustness = new ArrayList<>();

	for(int i=0; i<nodeObjects.size();i++) {
		
		nodes.add(new Node((Node)nodeObjects.get(i)));
		
	}
	
	//print(nodeObjects);
	
	print(nodes);
	
	String str = "";
	
	for(int i=0;i<nodes.size();i++) {
		
		String s= ((Node)nodes.get(i)).name;
		str = s.substring(1)+str;
	}
	
    int n = str.length();
    
    System.out.println("String ="+str+n);
    //CombinationGeeks permutation = new CombinationGeeks();
    permute(str, 0, n - 1,combinations);
	
    //printing the list of combinations
    
    for(int i=0;i<combinations.size();i++) {
    	
    	//System.out.println("Printing combinations");
    	System.out.println(combinations.get(i));
    }
    
    //calculating value of robustness for exhautive combinations
	//List tempNodes = new ArrayList();

    double robust =0;
    double finalRobust = 0;
    for(int i=0;i<combinations.size();i++) {
    	
    	robust =0;
    	
    	String s1 = (String) combinations.get(i);
    	System.out.println("s1 = "+ s1);
    	int lengthOfString = s1.length();
    	
    	//creating temporary list for removing nodes
		List tempNodes = new ArrayList();
		
		for(int k=0; k<nodes.size();k++) {
    		
			tempNodes.add(new Node((Node)nodes.get(k)));
    		
    	}
    	
    	
    	for(int j=0;j<s1.length()-1;j++) {
    		
    		String s2= String.valueOf(s1.charAt(j));
    		//print(tempNodes);
    		removeNode(tempNodes,"n"+s2);
    		print(tempNodes);
    		lcc = sizeOfLargestConnectedComponent(tempNodes);
    		System.out.println("Returned LCC = "+ lcc);
    		robust = robust + ((double)lcc/(double)lengthOfString);
    		System.out.println("robustness = "+ robust);	
    	}
    	
    	finalRobust = ((double)1/(lengthOfString)*(double)robust);
    	System.out.println("final robustness = "+finalRobust);
    	finalRobustness.add(finalRobust);
    }
    //taking mean for a single robustness value
    double meanRobustness = 0.0;
    for(int i=0;i<finalRobustness.size();i++) {
    	meanRobustness = meanRobustness + finalRobustness.get(i);
    			//(double)finalRobust/(double)nodes.size();
    }
    meanRobustness = meanRobustness/finalRobustness.size();
    System.out.println("final value of robustness = "+meanRobustness);

	}
}
