import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BT{

	// Because the root can't be a leaf
	private Node root = new Node();

	// Binary Tree Constructor
	BT(){}

	/*
	private void printRec(Node node){
		if(node.getLeftSon() != null){ 
			System.out.println("get Left");
			printRec(node.getLeftSon());
		}
		if(node.getRightSon() != null){
			System.out.println("get Right");
		       	printRec(node.getRightSon());
		}
		System.out.println("Node");
	}
	*/

	// Alters a hole branch base on list
	//   of symptoms from the dataset 
	public void setABranch(Queue<Integer> symptomQueue, Integer prognostic){
		
		// Queue with the indexes of
		//     positive symptoms
		Integer affirmativeSymptom = symptomQueue.remove();

		//    tmpNode => interation node 
		// (to move throught throughtthe BT)
		Node tmpNode 		= root;
		Integer treeHeight 	= 0;

		// This iteration runs until the reach the last 
		// 	  positive answer for symptoms
		// (ending by putting the current prognostic 
		// 	 on the last node of its path)
		while(true){
			if(treeHeight == affirmativeSymptom){

				if(tmpNode.getRightSon() == null) 
					tmpNode.newRightSon();
				tmpNode	= tmpNode.getRightSon();

				//   Break loop condition
				// (last node of this path)
				if(symptomQueue.size() == 0){
					tmpNode.setPrognostic(prognostic);
					break;
				}

				affirmativeSymptom = symptomQueue.remove();
			}
			else{
				if(tmpNode.getLeftSon() == null) 
					tmpNode.newLeftSon();
			       	tmpNode 		= tmpNode.getLeftSon();
			}

			treeHeight += 1;
		}
	}

	// Based on the affirmative answers gets
	// 	all possible prognostics
	//   (By checking checking the nodes on 
	// 	    its path on the tree)
	public HashSet<Integer> checkSymptomPath(Queue<Integer> symptomQueue){

		// A hashset to store all possible prognostic
		// 	      found along its way
		HashSet<Integer> prognosticAc = new HashSet<Integer>();
		
		// Queue with the indexes of
		//     positive symptoms
		Integer affirmativeSymptom = symptomQueue.remove();

		//    tmpNode => interation node 
		// (to move throught throughtthe BT)
		Node tmpNode 		= root;
		Integer treeHeight 	= 0;

		// Null nodes mean that the dataset
		// 	didn't track after
		// 	    that point
		while(true){

			// Puts the prognostic on the last node on its set
			HashSet<Integer> nodePrognostic = tmpNode.getPrognostic();
			prognosticAc.addAll(nodePrognostic);

			if(treeHeight >= affirmativeSymptom){

				if(tmpNode.getRightSon() == null) 
					return prognosticAc;
				tmpNode = tmpNode.getRightSon();
				
				// Can't remove on an empty Queue
				if(symptomQueue.size() > 0) 
					affirmativeSymptom = symptomQueue.remove();
			}
			else{
				if(tmpNode.getLeftSon() == null)
					return prognosticAc;
			       	tmpNode	= tmpNode.getLeftSon();
			}
			treeHeight += 1;
		}
	}

}
