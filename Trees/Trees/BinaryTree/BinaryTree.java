package Trees.BinaryTree;

import java.util.function.Consumer;

/**
 * Implementation of a {@link IBinaryTree}.
 * It is a binary search that is made up of {@link Node}.
 * It Contains methods to insert and delete a node,
 * a method to search the Trees,
 * a method to print the Trees in ascending order,
 * and a method to print the Trees in level order.
 * @author Jieyang
 */
public class BinaryTree implements IBinaryTree {

	protected INode root;

	/**
	 * Initialize an empty {@link BinaryTree}.
	 */
	public BinaryTree() { root = null; }

	/**
	 * Initialize a {@link BinaryTree} by providing the root node.
	 * @param root root of the Trees
	 */
	public BinaryTree(INode root) {
		this.root = root;
	}

	/**
	 * Get root node.
	 * @return INode root node
	 */
	public INode getRoot() {
		return root;
	}

	/**
	 * Insert a node.
	 * @param n node to insert
	 */
	public void insert(INode n) {
		insert(n, root);
	}

	/**
	 * Delete a node.
	 * @param n node to delete
	 */
	public void delete(INode n) {
		// node has two children
		if (n.getLeft() != null && n.getRight() != null) {
			INode smallestRightTreeNode = getLeastValueNode(n.getRight());
			int nValue = n.getData();
			n.setData(smallestRightTreeNode.getData());
			smallestRightTreeNode.setData(nValue);
			delete(smallestRightTreeNode);
		}
		// node has one or no children
		else {
			INode child = null;
			if (n.getLeft() != null) {
				child = n.getLeft();
			}
			else if (n.getRight() != null){
				child = n.getRight();
			}
			removeNodeOneOrNoChild(n, child);
		}
	}

	/**
	 * Rotate a node right.
	 * @param n node to rotate
	 */
	public void rightRotate(INode n) {
		INode leftChild = n.getLeft();
		INode parent = n.getParent();
		INode leftRightGrandchild;
		if (leftChild != null) {
			leftRightGrandchild = leftChild.getRight();
			if (leftRightGrandchild != null) {
				leftRightGrandchild.setParent(n);
			}
			leftChild.setRight(n);
			n.setParent(leftChild);
			n.setLeft(leftRightGrandchild);

			// Node was root, need to reset root
			if (parent == null) {
				root = leftChild;
			}
			else {
				if (parent.getLeft() == n) {
					parent.setLeft(leftChild);
				}
				else {
					parent.setRight(leftChild);
				}
				leftChild.setParent(parent);
			}
		}
	}

	/**
	 * Rotate a node left.
	 * @param n node to rotate
	 */
	public void leftRotate(INode n) {
		INode rightChild = n.getRight();
		INode parent = n.getParent();
		INode rightLeftGrandchild;
		if (rightChild != null) {
			rightLeftGrandchild = rightChild.getLeft();
			if (rightLeftGrandchild != null) {
				rightLeftGrandchild.setParent(n);
			}
			rightChild.setLeft(n);
			n.setParent(rightChild);
			n.setRight(rightLeftGrandchild);

			// Node was root, need to reset root
			if (parent == null) {
				root = rightChild;
			}
			else {
				if (parent.getLeft() == n) {
					parent.setLeft(rightChild);
				}
				else {
					parent.setRight(rightChild);
				}
				rightChild.setParent(parent);
			}
		}
	}

	/**
	 * Return node by its data value.
	 * @param data node's data value
	 * @return INode node searched for, null if it does not exist
	 */
	public INode getNode(int data) {
		return searchTree(data, root);
	}

	/**
	 * Print the Trees in ascending order.
	 * @param printer function used to print the Trees
	 */
	public void inOrderTraversal(Consumer<INode> printer) {
		inOrderTraversal(root, printer);
	}

	/**
	 * Print the Trees by level.
	 * @param printer function used to print the Trees
	 */
	public void levelTraversal(Consumer<INode> printer) {
		int height = getHeight(root);
		for (int i = 0; i < height; i++) {
			printLevel(root, 0, i, printer);
			System.out.println();
		}
	}

	/**
	 * Get least value node of a Trees.
	 * @param root root of the Trees
	 * @return INode node with least value
	 */
	public INode getLeastValueNode(INode root) {
		if (root != null) {
			if (root.getLeft() != null) {
				return getLeastValueNode(root.getLeft());
			}
			else {
				return root;
			}
		}
		else {
			return null;
		}
	}

	protected void insert(INode n, INode root) {
		if(root == null) {
			if (this.root == null) {
				this.root = n;
			}
		}
		else if(n.getData() <= root.getData()) {
			if(root.getLeft() == null) {
				root.setLeft(n);
				n.setParent(root);
			}
			else {
				insert(n, root.getLeft());
			}
		}
		else {
			if(root.getRight() == null) {
				root.setRight(n);
				n.setParent(root);
			}
			else {
				insert(n, root.getRight());
			}
		}
	}

	protected void removeNodeOneOrNoChild(INode n, INode child) {
		if (n.getParent() != null) {
			if (n.getParent().getLeft() == n) {
				n.getParent().setLeft(child);
			}
			else {
				n.getParent().setRight(child);
			}
		}
		if (child != null) {
			child.setParent(n.getParent());
		}
		if (this.root == n) {
			this.root = child;
		}
		n.setParent(null);
		n.setLeft(null);
		n.setRight(null);
	}

	protected INode searchTree(int data, INode root) {
		if(root == null) {
			return null;
		}
		else if(root.getData() == data) {
			return root;
		}
		else if(root.getData() >= data) {
			return (searchTree(data, root.getLeft()));
		}
		else {
			return (searchTree(data, root.getRight()));
		}
	}

	protected void inOrderTraversal(INode n, Consumer<INode> printer) {
		if (n == null) {
			return;
		}
		inOrderTraversal(n.getLeft(), printer);
		printer.accept(n);
		inOrderTraversal(n.getRight(), printer);
	}

	protected void printLevel(INode n, int currentLevel, int level, Consumer<INode> printer) {
		if (n == null) {
			if (currentLevel == level) {
				printer.accept(null);
			}
			return;
		}
		if (level < 0) {
			return;
		}
		else if (currentLevel > level) {
			return;
		}
		else if (currentLevel == level) {
			printer.accept(n);
		}
		printLevel(n.getLeft(), currentLevel + 1, level, printer);
		printLevel(n.getRight(), currentLevel + 1, level, printer);
	}

	protected int getHeight(INode n) {
		if (n == null) {
			return 0;
		}
		int leftHeight = getHeight(n.getLeft()) + 1;
		int rightHeight = getHeight(n.getRight()) + 1;
		if (leftHeight > rightHeight) {
			return leftHeight;
		}
		else {
			return rightHeight;
		}
	}

}
