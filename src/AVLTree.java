/**
*	AVL tree class for the use in the PowerAVLApp
*	@author Patrick modified by Niceta Nduku
*/
public class AVLTree{

	private static BinaryTreeNode root;
	private static int insertOpCount=0;
	private static int findOpCount=0;

	/**
	* Costructor creates an new avl with root node equal to null
	*/
	public AVLTree () {
		this.root = null;
	}

	/**
	*	Gives the height of the binary tree
	*	@param node BinaryTreeNode
	*	@return int the height of the tree
	*/
	public int height ( BinaryTreeNode node ) {

		if (node != null)
			return node.height;
		return -1;
	}

	/**
	*	calculates the difference between the right sub-tree
	*	and the left sub tree
	*	@param node BinaryTreeNode
	*	@return int height difference
	*/
	public int balanceFactor ( BinaryTreeNode node ) {
		
		return height (node.right) - height (node.left);
	}

	/**
	*	calculates the height of the tree. 
	*	@param node BinaryTreeNode
	*/
	public void fixHeight ( BinaryTreeNode node ) {

		node.height = Math.max (height (node.left),	height (node.right)) + 1;
	}

	/**
	*	Rotate with left child
	*	@param p BinaryTreeNode
	*	@return BinaryTreeNode 
	*/
	public BinaryTreeNode rotateRight ( BinaryTreeNode p ) {
		
		BinaryTreeNode q = p.left;
		p.left = q.right;
		q.right = p;
		fixHeight (p);
		fixHeight (q);
		return q;
	}

	/**
	*	Rotate with right child
	*	@param q BinaryTreeNode
	*	@return BinaryTreeNode 
	*/
	public BinaryTreeNode rotateLeft ( BinaryTreeNode q ) {

		BinaryTreeNode p = q.right;
		q.right = p.left;
		p.left = q;
		fixHeight (q);
		fixHeight (p);
		return p;
	}

	/**
	*	This method balances the tree.
	*	@param p BinaryTreeNode
	*	@return BinaryTreeNode
	*/
	public BinaryTreeNode balance ( BinaryTreeNode p ) {

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

	/**
	*	This method adds data to a AVL tree. 
	*	If the root is null it will make the data the root and 
	*	add its children. 
	*	@param d This is the data of type PowerData 
	*/
	public void insert ( PowerData d ) {

		root = insert (d, root);
	}
	
	/**
	*	Adds a new node to the current node by comparing its dateTime Value
	*	If the dateTime of d is smaller that the node's data datetime, node containing d will be added to the left of node. 
	*	If the dateTime of d is greater that the node's data datetime, node containing d will be added to the right of node. 
	*	add its children. 
	*	@param d This is the data of type PowerData 
	*	@param node This is the node which the next data will be linked to.
	*/

	public BinaryTreeNode insert ( PowerData d, BinaryTreeNode node ) {

		if (node == null)
			return new BinaryTreeNode (d, null, null);

		insertOpCount++;

		if ((d.getDateTime()).compareTo (node.data.getDateTime()) <= 0) 
			node.left = insert (d, node.left);
		else
			node.right = insert (d, node.right);
		return balance (node);
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
		int height;

		/**
		*	New node object. 
		*	@param d PowerData item
		*	@param l left node
		*	@param r right node
		*/
		public BinaryTreeNode ( PowerData d, BinaryTreeNode l, BinaryTreeNode r ) {

			data = d;
			left = l;
			right = r;
		}
	}
}