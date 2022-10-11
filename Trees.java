
public class Trees<E extends Comparable<E>> {
	// Node inner class
	private Node<E> root;
	private int currentSize;

	@SuppressWarnings("hiding")
	private class Node<E> {
		// instance variables
		E data;
		Node<E> left, right;

		// constructor
		public Node(E obj) {
			this.data = obj;
			left = right = null;
		}
	}

	// Computes the height of the tree, O(n)
	public int height() {
		return height(root);
	}

	// Recursive helper method to compute the height of the tree
	private int height(Node<E> node) {
		if (node == null)
			return 0;
		return Math.max(height(node.left), height(node.right)) + 1;
	}

	  // Add an element to this binary tree. Returns true
	  // if we successfully perform an insertion
	  public boolean add(E elem) {

	    // Check if the value already exists in this
	    // binary tree, if it does ignore adding it
	    if (contains(elem)) {
	      return false;

	      // Otherwise add this element to the binary tree
	    } else {
	      root = add(root, elem);
	      currentSize++;
	      return true;
	    }
	  }

	  // Private method to recursively add a value in the binary tree
	  private Node<E> add(Node<E> node, E elem) {

	    // Base case: found a leaf node
	    if (node == null) {
	      node = new Node<E>(elem);

	    } else {
	      // Pick a subtree to insert element
	      if (elem.compareTo(node.data) < 0) {
	        node.left = add(node.left, elem);
	      } else {
	        node.right = add(node.right, elem);
	      }
	    }

	    return node;
	  }

	public boolean contains(E obj) {
		return contains(obj, root);
	}

	private boolean contains(E obj, Node<E> node) {
		if (node == null) {
			return false;
		}
		int comparison = node.data.compareTo(obj);
		if (comparison == 0) {
			return true;
		}
		if (comparison < 0) {
			return contains(obj, node.right);
		}
		return contains(obj, node.left);
	}
	public boolean remove(E obj) {
		if (contains(obj)) {
			remove(obj, root);
			currentSize--;
			return true;
		}
		return false;
	}

	private Node<E> remove(E obj, Node<E> node) {

		if (node == null)
			return null;
		int compare = obj.compareTo(node.data);

		if (compare > 0) {
			node.right = remove(obj, node.right);
		} else if (compare < 0) {
			node.left = remove(obj, node.left);
		} else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {

				return node.left;
				// When removing a node from a binary tree with two links the
				// successor of the node being removed can either be the largest
				// value in the left subtree or the smallest value in the right
				// subtree. In this implementation I have decided to find the
				// smallest value in the right subtree which can be found by
				// traversing as far left as possible in the right subtree.
			} else {

				// Find the leftmost node in the right subtree
				Node<E> tmp = findMin(node.right);

				// Swap the data
				node.data = tmp.data;

				// Go into the right subtree and remove the leftmost node we
				// found and swapped data with. This prevents us from having
				// two nodes in our tree with the same value.
				node.right = remove(tmp.data, node.right);

				// If instead we wanted to find the largest node in the left
				// subtree as opposed to smallest node in the right subtree
				// here is what we would do:
				// Node tmp = findMax(node.left);
				// node.data = tmp.data;
				// node.left = remove(node.left, tmp.data);

			}
		}
		return node;

	}

	// Helper method to find the leftmost node (which has the smallest value)
	private Node<E> findMin(Node<E> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}
	public Node<E> leftRotation(Node<E> node) {
		Node<E> temp = node.right;
		node.right = temp.left;
		temp.left = node;
		return temp;
	}
	public Node<E> rightRotation(Node<E> node) {
		Node<E> temp = node.left;
		node.left = temp.right;
		temp.right = node;
		return temp;
	}
	public Node<E> rightLeftRotation(Node<E> node) {
		node.right = rightRotation(node.right);
		return leftRotation(node);
	}
	public Node<E> leftRightRotation(Node<E> node) {
		node.left = rightRotation(node.left);
		return leftRotation(node);
	}
	public void printLeaves(Node<E> root) {
		if (root == null) return;
		if (root.left == null && root.right == null) {
			System.out.println(root.data + ", ");
			return;
		}
		if(root.left != null)
			printLeaves(root.left);
		if(root.right != null)
			printLeaves(root.right);
	}
	public void inOrderTraversal(Node<E> head) {
		if (head == null)
			return;
		inOrderTraversal(head.left);
		System.out.println(head.data);
		inOrderTraversal(head.right);
	}
	public void preOrderTraversal(Node<E> head) {
		if (head == null)
			return;
		System.out.println(head.data);
		preOrderTraversal(head.left);
		preOrderTraversal(head.right);
	}
	public void postOrderTraversal(Node<E> head) {
		if (head == null)
			return;
		postOrderTraversal(head.left);
		postOrderTraversal(head.right);
		System.out.println(head.data);
	}
	public void levelOrderTraversal(Node<E> head) {
		ArrayList<Node<E>> ar = new ArrayList<>();
		if (head == null) return;
		ar.add(head);
		int curCount = 0;
		while(curCount < currentSize) {
			Node<E> curNode = ar.get(curCount);
			if(curNode != null)
				System.out.println(curNode.data);
			if(curNode.left != null)
				ar.add(curNode.left);
			if(curNode.right != null)
				ar.add(curNode.right);
			curCount++;
		}
	}
	
	public static void main(String[] args) {
		Trees<Integer> myTree = new Trees<Integer>();
		myTree.add(10);
		myTree.add(15);
		myTree.add(6);
		myTree.add(7);
		myTree.add(4);
		myTree.add(18);
		myTree.add(20);
		
		
		myTree.remove(4);
		myTree.remove(20);
		myTree.remove(10);
	}

}
