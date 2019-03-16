/** 
*	Binary search tree class for the use in the PowerBSTApp 
*	@author Patrick modified by Niceta Nduku
*
*/
public class BinarySearchTree{

	private static  BinaryTreeNode root;
	private static int insertOpCount=0;
	private static int findOpCount=0;

	/**
	* Costructor creates an new avl with root node equal to null
	*/
	public BinarySearchTree(){
		this.root = null;
	}

	/**
	*	This method adds data to a binary search tree. 
	*	If the root is null it will make the data the root and 
	*	add its children. 
	*	@param d This is the data of type PowerData 
	*/
	public void insert ( PowerData d ) {

		if (root == null)
			root = new BinaryTreeNode (d, null, null);
		else
		 	insert (d, root);
	}

	/**
	*	Adds a new node to the current node by comparing its dateTime Value
	*	If the dateTime of d is smaller that the node's data datetime, node containing d will be added to the left of node. 
	*	If the dateTime of d is greater that the node's data datetime, node containing d will be added to the right of node. 
	*	add its children. 
	*	@param d This is the data of type PowerData 
	*	@param node This is the node which the next data will be linked to.
	*/

	public void insert ( PowerData d, BinaryTreeNode node ) {
		

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

	/**
	*	This method looks for a string data in the AVL Tree 
	*	@param s String data
	*	@return PowerData item
	*/
	public PowerData find ( String s) {
		
		if (root == null)
			return null;
		else
			return find (s, root);
	}

	/**
	*	This method looks for a string data in the AVL Tree. 
	*	It compares each node's data in the left and right sub trees 
	*	until it finds a match. 
	*	@param s String data
	*	@param node Binary tree node
	*	@return PowerData item
	*/
	public PowerData find ( String s, BinaryTreeNode node) {

	
		findOpCount++;// comparison incriment for first comparison operator
		
		if ( s.compareTo (node.data.getDateTime()) == 0)
			return node.data ;
		else if ( s.compareTo (node.data.getDateTime()) < 0)
			return (node.left == null) ? null : find (s, node.left);
		else
			return (node.right == null) ? null : find (s, node.right);
	}

	/**
	*	This method calls the display function that takes in the root as a parameter 
	*	@author Niceta Nduku
	*/
	public void display () {

		display(root);
	}

	/**
	*	Prints out all the data in the tree
	*	@author SJ modified by Niceta Nduku
	*/
	public void display (BinaryTreeNode root) {

		if(root!=null){
			display(root.left);
			System.out.println(root.data);
			display(root.right);
		}
	}

	/**
	*	@return int operation count
	*	@author Niceta Nduku
	*/
	public void getOpcount(){

		System.out.println("Insert Operations: "+insertOpCount+"\n"+"Find Operations: "+findOpCount);
	}

	/**
	*	@return int find operation counts
	*	@author Niceta Nduku
	*/
	public int getFindOpcount(){
		
		int findCount=findOpCount;// create a copy of the operation count
		findOpCount=0;// sets operations to zero in order to reset 
		
		return findCount;
	}

	/**
	*	@return int insert operation counts
	*	@author Niceta Nduku
	*/
	public int getInsertOpcount(){ 
		
		return insertOpCount;
	}

	/**
	*	Node object class
	*/
	private class BinaryTreeNode {
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

