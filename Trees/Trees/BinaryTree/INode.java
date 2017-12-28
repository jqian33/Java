package Trees.BinaryTree;

/**
 * Interface for a binary search tree node.
 * It can hold an integer value.
 * It contains left child, right child and parent references.
 * @author Jieyang
 *
 */
public interface INode {

    /**
     * Reference to left child.
     */
    INode getLeft();

    /**
     * Reference to right child.
     */
    INode getRight();

    /**
     * Reference to right child.
     */
    INode getParent();

    /**
     * Set left child.
     * @param n left child node
     */
    void setLeft(INode n);

    /**
     * Set right child.
     * @param n right child node
     */
    void setRight(INode n);

    /**
     * Set parent.
     * @param n parent node
     */
    void setParent(INode n);

    /**
     * Set node data.
     * @param data Node value
     */
    void setData(int data);

    /**
     * Get node data.
     * @return Integer value of node.
     */
    int getData();

    /**
     * Get uncle node
     * @return INode uncle node
     */
    INode getUncle();

    /**
     * Get sibling node
     * @return INode sibling node
     */
    INode getSibling();
}
