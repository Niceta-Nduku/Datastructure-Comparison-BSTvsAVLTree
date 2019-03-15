/**
*	AVL tree class for the use in the PowerAVLApp
*	@author Patrick modified by Niceta Nduku
*/
public class AVLTree{

	private static BinaryTreeNode root;
	private static int insertOpCount=0;
	private static int findOpCount=0;

	public AVLTree () {
		this.root = null;
	}

	public int height ( BinaryTreeNode node ) {
		/**
		*	Gives the height of the binary tree
		*	@param node BinaryTreeNode
		*	@return int the height of the tree
		*/

		if (node != null)
			return node.height;
		return -1;
	}

	public int balanceFactor ( BinaryTreeNode node ) {
		/**
		*	calculates the difference between the right sub-tree
		*	and the left sub tree
		*	@param node BinaryTreeNode
		*	@return int height difference
		*/

		return height (node.right) - height (node.left);
	}

	public void fixHeight ( BinaryTreeNode node ) {
		/**
		*	calculates the height of the tree. 
		*	@param node BinaryTreeNode
		*/

		node.height = Math.max (height (node.left),	height (node.right)) + 1;
	}

	public BinaryTreeNode rotateRight ( BinaryTreeNode p ) {
		/**
		*	Rotate with left child
		*	@param p BinaryTreeNode
		*	@return BinaryTreeNode 
		*/

		BinaryTreeNode q = p.left;
		p.left = q.right;
		q.right = p;
		fixHeight (p);
		fixHeight (q);
		return q;
	}

	public BinaryTreeNode rotateLeft ( BinaryTreeNode q ) {
		/**
		*	Rotate with right child
		*	@param q BinaryTreeNode
		*	@return BinaryTreeNode 
		*/

		BinaryTreeNode p = q.right;
		q.right = p.left;
		p.left = q;
		fixHeight (q);
		fixHeight (p);
		return p;
	}

	public BinaryTreeNode balance ( BinaryTreeNode p ) {
		/**
		*	This method balances the tree.
		*	@param p BinaryTreeNode
		*	@return BinaryTreeNode
		*/

		fixHeight (p);
		if (balanceFactor (p) == 2) {
			if (balanceFactor (p.right) < 0)
			p.right = rotateRight (p.right);
			return rotateLeft (p);
		}	
		if (balanceFactor (p) == -2)
		{
			if (balanceFactor (p.left) > 0)
				p.left = rotateLeft (p.left);
			return rotateRight (p);
		}	
		return p;
	}

	public void insert ( PowerData d ) {
		/**
		*	This method adds data to a AVL tree. 
		*	If the root is null it will make the data the root and 
		*	add its children. 
		*	@param d This is the data of type PowerData 
		*/

		root = insert (d, root);
	}
	
	public BinaryTreeNode insert ( PowerData d, BinaryTreeNode node ) {
		/**
		*	Adds a new node to the current node by comparing its dateTime Value
		*	If the dateTime of d is smaller that the node's data datetime, node containing d will be added to the left of node. 
		*	If the dateTime of d is greater that the node's data datetime, node containing d will be added to the right of node. 
		*	add its children. 
		*	@param d This is the data of type PowerData 
		*	@param node This is the node which the next data will be linked to.
		*/

		if (node == null)
			return new BinaryTreeNode (d, null, null);

		insertOpCount++;

		if ((d.getDateTime()).compareTo (node.data.getDateTime()) <= 0) 
			node.left = insert (d, node.left);
		else
			node.right = insert (d, node.right);
		return balance (node);
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
		

		System.out.println("Insert Operations"+insertOpCount+" "+"Find Operations"+findCount);
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
		int height;

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