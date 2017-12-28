package Trees.BinaryTree;

/**
 * Implementation of {@link INode}
 * It can hold an integer value.
 * It contains left child, right child and parent references.
 * @author Jieyang
 *
 */
public class Node implements INode {

	private int data;
	protected INode left;
	protected INode right;
	protected INode parent;

	/**
	 * Initialize a {@link Node} by providing the integer value.
	 * @param data Node value
	 */
	public Node(int data) {
		this.data = data;
	}

	/**
	 * Get node data.
	 * @return Integer value of node
	 */
	public int getData() {
		return data;
	}

	/**
	 * Set node data.
	 * @param data Node value
	 */
	public void setData(int data) {
		this.data = data;
	}

	/**
	 * Reference to left child.
	 */
	public INode getLeft() { return left; }

	/**
	 * Reference to right child.
	 */
	public INode getRight() {return right; }

	/**
	 * Reference to right child.
	 */
	public INode getParent() { return parent; }

	/**
	 * Set left child.
	 * @param n left child node
	 */
	public void setLeft(INode n) { left = n; }

	/**
	 * Set right child.
	 * @param n right child node
	 */
	public void setRight(INode n) { right = n; }

	/**
	 * Set parent.
	 * @param n parent node
	 */
	public void setParent(INode n) { parent = n; }

	/**
	 * Get uncle node
	 * @return INode uncle node
	 */
	public INode getUncle() {
		if (parent != null) {
			INode grandfather = parent.getParent();
			if (grandfather != null) {
				if (parent == grandfather.getLeft()) {
					return grandfather.getRight();
				}
				else {
					return grandfather.getLeft();
				}
			}
			else {
				return null;
			}
		}
		return null;
	}

	/**
	 * Get sibling node
	 * @return INode sibling node
	 */
	public INode getSibling() {
		if (parent != null) {
			INode sibling;
			if (this == parent.getLeft()) {
				sibling = parent.getRight();
			}
			else {
				sibling = parent.getLeft();
			}
			return sibling;
		}
		return null;
	}

}
