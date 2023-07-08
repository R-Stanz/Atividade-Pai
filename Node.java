import java.util.HashSet;

public class Node{

	// Answer is equals to 0
	private Node 	leftSon;

	// Answer is equals to 1
	private Node 	rightSon;

	private HashSet<Integer> prognosticSet = new HashSet<Integer>();

	// Root Node constructor
	Node(){}

	// Getter and Setter for Left Sons
	public Node getLeftSon(){
		return this.leftSon;
	}

	public void newLeftSon(){
		Node node = new Node();
		this.leftSon= node;
	}

	// Getter and Setter for Right Sons
	public Node getRightSon(){
		return this.rightSon;
	}

	public void newRightSon(){
		Node node = new Node();
		this.rightSon = node;
	}

	// Getter and Setter for Prognostics
	
	public HashSet<Integer> getPrognostic(){
		return prognosticSet;
	}

	public void setPrognostic(Integer prognostic){
		prognosticSet.add(prognostic);
	}
		
}
