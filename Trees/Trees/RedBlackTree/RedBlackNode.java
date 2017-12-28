package Trees.RedBlackTree;

import Trees.BinaryTree.Node;
import Trees.BinaryTree.INode;

/**
 * RedBlackNode extends {@link Node}.
 * It has an additional boolean isBlack property.
 * This property can be modified through colorBlack and colorRed method.
 * @author Jieyang
 */
public class RedBlackNode extends Node {

    private boolean isBlack;

    /**
     * Initialize a {@link RedBlackNode}.
     * It is initalized as a red node.
     * @param data node value
     */
    public RedBlackNode(int data) {
        super(data);
        isBlack = false;
    }

    /**
     * Set left child.
     * @param n left child node
     */
    public void setLeft(INode n) {
        if (n instanceof RedBlackNode || n == null) {
            left = n;
        }
    }

    /**
     * Set right child.
     * @param n right child node
     */
    public void setRight(INode n) {
        if (n instanceof RedBlackNode || n == null) {
            right = n;
        }
    }

    /**
     * Set parent.
     * @param n parent node
     */
    public void setParent(INode n) {
        if (n instanceof RedBlackNode || n == null) {
            parent = n;
        }
    }

    /**
     * Check if node is black.
     * @return boolean indicating if node is black
     */
    public boolean isBlack() {
        return isBlack;
    }


    /**
     * Color node red.
     */
    public void colorRed() {
        isBlack = false;
    }

    /**
     * Color node black.
     */
    public void colorBlack() {
        isBlack = true;
    }

    /**
     * Check if uncle is black.
     * @return boolean indicating if uncle is black
     */
    public boolean uncleIsBlack() {
        RedBlackNode uncle = (RedBlackNode) getUncle();
        return uncle == null || uncle.isBlack();
    }

    /**
     * Check if a node and its child form consecutive black black pattern.
     * A black node need to have at least one red child to be not considered black black.
     * @return boolean indicating if node is double black
     */
    public boolean isDoubleBlack() {
        if (this.isBlack) {
            boolean isBlackBlack = true;
            if (left != null) {
                if (!((RedBlackNode)left).isBlack) {
                    isBlackBlack = false;
                }
            }
            if (right != null) {
                if (!((RedBlackNode)right).isBlack) {
                    isBlackBlack = false;
                }
            }
            return isBlackBlack;
        }
        return false;
    }

    /**
     * Get node configuration for insertion (called by node inserted).
     * @return NodeConfiguration node configuration
     */
    public NodeConfiguration getConfigurationInsert() {
        if (parent != null) {
            INode grandParent = parent.getParent();
            if (grandParent != null) {
                if (this == parent.getLeft() && parent == grandParent.getLeft()) {
                    return NodeConfiguration.LEFTLEFT;
                }
                else if (this == parent.getRight() && parent == grandParent.getRight()) {
                    return NodeConfiguration.RIGHTRIGHT;
                }
                else if (this == parent.getLeft() && parent == grandParent.getRight()) {
                    return NodeConfiguration.RIGHTLEFT;
                }
                else {
                    return NodeConfiguration.LEFTRIGHT;
                }
            }
            else {
                return NodeConfiguration.INVALID;
            }
        }
        else {
            return NodeConfiguration.INVALID;
        }
    }

    /**
     * Get node configuration for deletion (called by sibling of deleted node).
     * This checks nodes configuration with regards to its red children.
     * If node has no red children, INVALID will be returned.
     * @return NodeConfiguration node configuration
     */
    public NodeConfiguration getConfigurationDelete(){
        if (parent != null) {
            if (parent.getLeft() == this) {
                RedBlackNode leftChild = (RedBlackNode)left;
                RedBlackNode rightChild = (RedBlackNode)right;
                if (leftChild != null) {
                    if (!leftChild.isBlack()) {
                        return NodeConfiguration.LEFTLEFT;
                    }
                }
                else if (rightChild != null) {
                    if (!rightChild.isBlack()) {
                        return NodeConfiguration.LEFTRIGHT;
                    }
                }
                return NodeConfiguration.INVALID;
            }
            else {
                RedBlackNode leftChild = (RedBlackNode)left;
                RedBlackNode rightChild = (RedBlackNode)right;
                if (rightChild != null) {
                    if (!rightChild.isBlack()) {
                        return NodeConfiguration.RIGHTRIGHT;
                    }
                }
                else if (leftChild != null) {
                    if (!leftChild.isBlack()) {
                        return NodeConfiguration.RIGHTLEFT;
                    }
                }

                return NodeConfiguration.INVALID;
            }
        }
        else {
            return NodeConfiguration.INVALID;
        }
    }

}
