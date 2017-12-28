package Trees.BinaryTree;

import java.util.function.Consumer;

/**
 * Interface of a binary search tree.
 * It Contains methods to insert a node,
 * a method to search the Trees,
 * a method to print the Trees in ascending order,
 * and a method to print the Trees in level order.
 * @author Jieyang
 *
 */
public interface IBinaryTree {

    /**
     * Get root node.
     * @return INode root node
     */
    INode getRoot();

    /**
     * Insert a node.
     * @param n node to insert
     */
    void insert(INode n);

    /**
     * Delete a node.
     * @param n node to delete
     */
    void delete(INode n);

    /**
     * Return node by its data value.
     * @param data node's data value
     * @return INode node searched for, null if it does not exist
     */
    INode getNode(int data);

    /**
     * Rotate a node right.
     * @param n node to rotate
     */
    void rightRotate(INode n);

    /**
     * Rotate a node left.
     * @param n node to rotate
     */
    void leftRotate(INode n);

    /**
     * Print the tree in ascending order.
     * @param printer Function used to print the tree
     */
    void inOrderTraversal(Consumer<INode> printer);

    /**
     * Print the tree level by level.
     * @param printer Function used to print the tree
     */
    void levelTraversal(Consumer<INode> printer);

    /**
     * Get node with the least value from the tree.
     * @param root Root of the tree
     * @return INode node with least value
     */
    INode getLeastValueNode(INode root);
}
