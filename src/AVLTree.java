
public class AVLTree{

	BinaryTreeNode root;
	int insertOpCount=0;
	int findOpCount=0;

	public AVLTree () {
		root = null;
	}

	public int height ( BinaryTreeNode node ) {
		if (node != null)
			return node.height;
		return -1;
	}

	public int balanceFactor ( BinaryTreeNode node ) {
		return height (node.right) - height (node.left);
	}

	public void fixHeight ( BinaryTreeNode node ) {
		node.height = Math.max (height (node.left),	height (node.right)) + 1;
	}

	public BinaryTreeNode rotateRight ( BinaryTreeNode p ) {
		BinaryTreeNode q = p.left;
		p.left = q.right;
		q.right = p;
		fixHeight (p);
		fixHeight (q);
		return q;
	}

	public BinaryTreeNode rotateLeft ( BinaryTreeNode q ) {
		BinaryTreeNode p = q.right;
		q.right = p.left;
		p.left = q;
		fixHeight (q);
		fixHeight (p);
		return p;
	}

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

	public void insert ( PowerData d ) {
		root = insert (d, root);
	}
	
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

	public PowerData find ( String s) {
		/**
		@author Patrick modified by Niceta Nduku
		*/
		if (root == null)
			return null;
		else
			return find (s, root);
	}

	public PowerData find ( String s, BinaryTreeNode node) {
		/**
		@author Patrick modified by Niceta Nduku
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
		This method calls the display function that takes in the root as a parameter 
		@author Niceta Nduku
		*/
		display(root);
	}

	public void display (BinaryTreeNode root) {
		/**
		Prints out all the data in the tree
		@author SJ modified by Niceta Nduku
		*/
		if(root!=null){
			display(root.left);
			System.out.println(root.data);
			display(root.right);
		}
	}

	public void getOpcount(){
		/**
		@return int operation count
		@author Niceta Nduku
		*/
		
		int findCount=findOpCount;// create a copy of the operation count
		findOpCount=0;// sets operations to zero in order to reset 
		

		System.out.println(insertOpCount+" "+findCount);
	}

	public int getFindOpcount(){
		/**
		@return int operation count
		@author Niceta Nduku
		*/
		
		int findCount=findOpCount;// create a copy of the operation count
		findOpCount=0;// sets operations to zero in order to reset 
		
		return findCount;
	}

	public int getInsertOpcount(){
		/**
		@return int operation count
		@author Niceta Nduku
		*/ 
		
		return insertOpCount;
	}

	private class BinaryTreeNode {
		/**
		Node object
		@author Patrick modified by Niceta Nduku
		*/
		PowerData data;
		BinaryTreeNode left;
		BinaryTreeNode right;
		int height;

		public BinaryTreeNode ( PowerData d, BinaryTreeNode l, BinaryTreeNode r ) {
			/**
			New node object. 
			@param d PowerData item
			@param l left node
			@param r right node
			*/

			data = d;
			left = l;
			right = r;
		}
	}
}