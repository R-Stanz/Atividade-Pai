import java.util.LinkedList;
import java.util.Queue;

public class BT{

	// Because the root can't be a leaf
	private Node root = new Node();

	// Binary Tree Constructor
	BT(){}

	// Sets the necessary nodes and the leaf prognostic of a
	// branch of the tree of symptoms.
	// Guided only by when there was a symptom
	// (The affirmative answers and the tree height) 
	public void setABranch(Queue<Integer> symptomsQueue, Integer prognostic){
		Integer affirmativeSymptom = symptomsQueue.remove();

		// tmpNode => interact the nodes 
		// (to move throught throughtthe BT)
		Node tmpNode 		= root;
		Integer treeHeight 	= 1;
		Boolean isLeaf		= false;

		// This iteration runs until the leafs while 
		// achieving the goal of its method
		while(symptomsQueue.size() > 0){
			if(symptomsQueue.size() == 1) isLeaf = true;

			if(treeHeight == affirmativeSymptom){
				if(tmpNode.getRightSon() == null) 
					tmp.newRightSon(isLeaf, prognostic);
				tmpNode = tmpNode.getRightSon();
				affirmativeSympton = symptomQueue.remove();
			}
			else tmpNode = tmpNode.getLeftSon(isLeaf, prognostic);

			treeHeight += 1;
		}
	}
}
