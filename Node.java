import java.util.HashSet;

public class Node{

	// Answer is equals to 0
	private Node 	leftSon;

	// Answer is equals to 1
	private Node 	rightSon;

	// Nodes aren't classified as leaf by default
	private Boolean isLeaf = false;

	private HashSet<Integer> prognosticsSet = new HashSet<Integer>();

	// Root Node constructor
	Node(){}

	// Default Node constructor
	Node(Boolean isLeaf, Integer prognostic){
		if(isLeaf){
			prognosticsSet.add(prognostic);
			isLeaf = true;
		}
		;
	}

	// Getter and Setter for Left Sons
	public Node getLeftSon(){
		return this.leftSon;
	}

	public void newLeftSon(Boolean isLeaf, Integer prognostic){
		Node node = new Node(isLeaf, prognostic);
		this.leftNode = node;
	}

	// Getter and Setter for Right Sons
	public Node getRightSon(){
		return this.rightSon;
	}

	public void newRightSon(Boolean isLeaf, Integer prognostic){
		Node node = new Node(isLeaf, prognostic);
		this.rightNode = node;
	}

	// Prognostic of the Branch
	public ArrayList<Integer> getPrognostics(){
		if(isLeaf) return prognosticArray;
		else{
			System.out.println("It seems prognostic is been called on" + 
						" a node that isn't a leaf"); 
			return null;
		}
	}
		
}
