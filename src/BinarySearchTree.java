/** 
*	Binary search tree class for the use in the PowerBSTApp 
*	@author Patrick modified by Niceta Nduku
*
*/
public class BinarySearchTree{

	private static  BinaryTreeNode root;
	private static int insertOpCount=0;
	private static int findOpCount=0;

	public BinarySearchTree(){
		this.root = null;
	}

	public void insert ( PowerData d ) {
		/**
		*	This method adds data to a binary search tree. 
		*	If the root is null it will make the data the root and 
		*	add its children. 
		*	@param d This is the data of type PowerData 
		*/
		if (root == null)
			root = new BinaryTreeNode (d, null, null);
		else
		 	insert (d, root);
	}

	public void insert ( PowerData d, BinaryTreeNode node ) {
		/**
		*	Adds a new node to the current node by comparing its dateTime Value
		*	If the dateTime of d is smaller that the node's data datetime, node containing d will be added to the left of node. 
		*	If the dateTime of d is greater that the node's data datetime, node containing d will be added to the right of node. 
		*	add its children. 
		*	@param d This is the data of type PowerData 
		*	@param node This is the node which the next data will be linked to.
		*/

		// comparison incriment for comparison operator below
		insertOpCount++;
		// compare the date/time of the data to be added to the current node
		if ((d.getDateTime()).compareTo (node.data.getDateTime()) <= 0) {

	 		if (node.left == null)
	 			node.left = new BinaryTreeNode (d, null, null);
	 		else
	 			insert (d, node.left);
	 	}
		else {
	 		if (node.right == null)
	 			node.right = new BinaryTreeNode (d, null, null);
	 		else
	 			insert (d, node.right);
	 	}
	}

	public PowerData find ( String s) {
		/**
		*	This method looks for a string data in the AVL Tree 
		*	@param s String data
		*	@return PowerData item
		*/
		if (root == null)
			return null;
		else
			return find (s, root);
	}

	public PowerData find ( String s, BinaryTreeNode node) {
		/**
		*	This method looks for a string data in the AVL Tree. 
		*	It compares each node's data in the left and right sub trees 
		*	until it finds a match. 
		*	@param s String data
		*	@param node Binary tree node
		*	@return PowerData item
		*/
	
		findOpCount++;// comparison incriment for first comparison operator
		
		if ( s.compareTo (node.data.getDateTime()) == 0)
			return node.data ;
		else if ( s.compareTo (node.data.getDateTime()) < 0)
			return (node.left == null) ? null : find (s, node.left);
		else
			return (node.right == null) ? null : find (s, node.right);
	}

	public void display () {
		/**
		*	This method calls the display function that takes in the root as a parameter 
		*	@author Niceta Nduku
		*/
		display(root);
	}

	public void display (BinaryTreeNode root) {
		/**
		*	Prints out all the data in the tree
		*	@author SJ modified by Niceta Nduku
		*/
		if(root!=null){
			display(root.left);
			System.out.println(root.data);
			display(root.right);
		}
	}


	public void getOpcount(){
		/**
		*	@return int operation count
		*	@author Niceta Nduku
		*/
		
		int findCount=findOpCount;// create a copy of the operation count
		findOpCount=0;// sets operations to zero in order to reset 
		

		System.out.println("Insert Operations: "+insertOpCount+"\n"+"Find Operations: "+findCount);
	}

	public int getFindOpcount(){
		/**
		*	@return int find operation counts
		*	@author Niceta Nduku
		*/
		
		int findCount=findOpCount;// create a copy of the operation count
		findOpCount=0;// sets operations to zero in order to reset 
		
		return findCount;
	}

	public int getInsertOpcount(){
		/**
		*	@return int insert operation counts
		*	@author Niceta Nduku
		*/ 
		
		return insertOpCount;
	}

	private class BinaryTreeNode {
		/**
		*	Node object class
		*/
		PowerData data;
		BinaryTreeNode left;
		BinaryTreeNode right;

		public BinaryTreeNode ( PowerData d, BinaryTreeNode l, BinaryTreeNode r ) {
			/**
			*	New node object. 
			*	@param d PowerData item
			*	@param l left node
			*	@param r right node
			*/

			data = d;
			left = l;
			right = r;
		}
	}
}

