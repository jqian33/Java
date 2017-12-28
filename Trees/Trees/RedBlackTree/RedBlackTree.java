package Trees.RedBlackTree;

import Trees.BinaryTree.BinaryTree;

/**
 * RedBlackTree extends {@link BinaryTree}.
 * It is a self balancing binary search tree that is made up of {@link RedBlackNode}.
 * It provides all the functionality of {@link BinaryTree},
 * but it overrides the insert and delete methods.
 * A Red Black Tree have the following properties:
 * 1) Every node has a color either red or black.
 * 2) Root of tree is always black.
 * 3) There are no two adjacent red nodes (A red node cannot have a red parent or red child).
 * 4) Every path from root to a NULL node has same number of black nodes.
 * @author Jieyang
 */
public class RedBlackTree extends BinaryTree{

    /**
     * Initialze an empty {@link RedBlackTree}.
     * The root node is always black.
     */
    public RedBlackTree() {
        super ();
    }

    /**
     * Initialze a {@link RedBlackTree}.
     * The root node is always black.
     * @param root root of the tree
     */
    public RedBlackTree(RedBlackNode root) {
        super (root);
        root.colorBlack();
    }

    /**
     * Insert a node, the tree will self balance.
     * @param node node to insert
     */
    public void insert(RedBlackNode node) {
        // perform regular binary insert
        insert(node, root);
        // rotate and recolor the tree recursively to main all red black tree properties
        rotateRecolor(node);
    }

    /**
     * Delete a node, the tree will self balance.
     * @param node node to delete
     */
    public void delete(RedBlackNode node) {
        // node has two children
        if (node.getLeft() != null && node.getRight() != null) {
            RedBlackNode smallestRightTreeNode = (RedBlackNode)getLeastValueNode(node.getRight());
            int nValue = node.getData();
            node.setData(smallestRightTreeNode.getData());
            smallestRightTreeNode.setData(nValue);
            delete(smallestRightTreeNode);
        }
        // node has one or no children
        else {
            boolean isDoubleBlack = node.isDoubleBlack();
            RedBlackNode sibling = (RedBlackNode)node.getSibling();
            RedBlackNode child = null;
            if (node.getLeft() != null) {
                child = (RedBlackNode)node.getLeft();
            }
            else if (node.getRight() != null) {
                child = (RedBlackNode)node.getRight();
            }

            // remove node
            removeNodeOneOrNoChild(node, child);

            // recolor for single black case
            if (!isDoubleBlack) {
                if (child != null) {
                    child.colorBlack();
                }
            }
            // rotate and recolor the tree to remove the double blackness
            else {
                removeDoubleBlack(true, sibling);
            }
        }
    }

    /**
     * Recursively rotate and recolor tree to remove double blackness after deletion.
     * @param isDoubleBlack boolean indicating if node is double black
     * @param node RedBlackNode sibling of node deleted
     */
    private void removeDoubleBlack(boolean isDoubleBlack, RedBlackNode node) {
        if (node != root && node != null) {
            RedBlackNode parent = (RedBlackNode) node.getParent();
            if (isDoubleBlack) {
                // handle case when sibling is black
                if (node.isBlack()) {
                    // get node configuration with its red children,
                    // INVALID cas will be return if node has no red children.
                    NodeConfiguration config = node.getConfigurationDelete();
                    switch (config) {
                        case LEFTLEFT:
                            applyLeftLeftDelete(node);
                            break;
                        case RIGHTRIGHT:
                            applyRightRightDelete(node);
                            break;
                        case LEFTRIGHT:
                            applyLeftRightDelete(node);
                            break;
                        case RIGHTLEFT:
                            applyRightLeftDelete(node);
                            break;
                        // sibling has no red children
                        case INVALID:
                            // if parent is already black, coloring it black will cause double blackness
                            isDoubleBlack = parent.isBlack();
                            parent.colorBlack();
                            node.colorRed();
                            removeDoubleBlack(isDoubleBlack, parent);
                    }
                }
                else {
                    // handle case when sibling is red
                    RedBlackNode newSibling;
                    if (node == parent.getLeft()) {
                        rightRotate(parent);
                        newSibling = (RedBlackNode) parent.getLeft();
                    } else {
                        leftRotate(parent);
                        newSibling = (RedBlackNode) parent.getRight();
                    }
                    node.colorBlack();
                    parent.colorRed();
                    removeDoubleBlack(true, newSibling);
                }
            }
        }
    }

    /**
     * Recursively rotate and recolor tree to restore all Red Black tree properties after insertion.
     * @param node node inserted
     */
    private void rotateRecolor(RedBlackNode node) {
        if (node != null) {
            // always color root black
            if (node == root) {
                node.colorBlack();
            }
            else {
                RedBlackNode parent = (RedBlackNode)node.getParent();
                if (parent != null) {
                    // inserting a red child to a child node will cause violation,
                    // therefore recoloring and rotating need to take place to restore all the red-black tree properties
                    if (!parent.isBlack()) {
                        // handle case when uncle is black
                        if (node.uncleIsBlack()) {
                            NodeConfiguration config = node.getConfigurationInsert();
                            switch(config) {
                                case LEFTLEFT:
                                    applyLeftLeftInsert(node);
                                    break;
                                case RIGHTRIGHT:
                                    applyRightRightInsert(node);
                                    break;
                                case LEFTRIGHT:
                                    applyLeftRightInsert(node);
                                    break;
                                case RIGHTLEFT:
                                    applyRightLeftInsert(node);
                                    break;
                            }
                        }
                        // handle case when uncle is red
                        else {
                            pushDownBlack(node);
                            rotateRecolor((RedBlackNode)parent.getParent());
                        }
                    }
                }
            }
        }
    }

    private void applyLeftLeftInsert(RedBlackNode node) {
        RedBlackNode parent = (RedBlackNode)node.getParent();
        RedBlackNode grandParent = (RedBlackNode)parent.getParent();
        rightRotate(grandParent);
        grandParent.colorRed();
        parent.colorBlack();
    }

    private void applyRightRightInsert(RedBlackNode node) {
        RedBlackNode parent = (RedBlackNode)node.getParent();
        RedBlackNode grandParent = (RedBlackNode)parent.getParent();
        leftRotate(grandParent);
        grandParent.colorRed();
        parent.colorBlack();
    }

    private void applyLeftRightInsert(RedBlackNode node) {
        RedBlackNode parent = (RedBlackNode)node.getParent();
        leftRotate(parent);
        applyLeftLeftInsert(parent);
    }

    private void applyRightLeftInsert(RedBlackNode node) {
        RedBlackNode parent = (RedBlackNode)node.getParent();
        rightRotate(parent);
        applyRightRightInsert(parent);
    }

    private void pushDownBlack (RedBlackNode node) {
        RedBlackNode parent = (RedBlackNode)node.getParent();
        if (!parent.isBlack()) {
            RedBlackNode grandParent = (RedBlackNode)parent.getParent();
            RedBlackNode uncle = (RedBlackNode)node.getUncle();
            parent.colorBlack();
            if (uncle != null) {
                uncle.colorBlack();
            }
            grandParent.colorRed();
        }
    }

    private void applyLeftLeftDelete(RedBlackNode node) {
        rightRotate(node.getParent());
        ((RedBlackNode)node.getLeft()).colorBlack();
    }

    private void applyRightRightDelete(RedBlackNode node) {
        leftRotate(node.getParent());
        ((RedBlackNode)node.getRight()).colorBlack();
    }

    private void applyLeftRightDelete(RedBlackNode node) {
        leftRotate(node);
        RedBlackNode newNode = (RedBlackNode)node.getParent();
        newNode.colorBlack();
        node.colorRed();
        applyLeftLeftDelete(newNode);
    }

    private void applyRightLeftDelete(RedBlackNode node) {
        rightRotate(node);
        RedBlackNode newNode = (RedBlackNode)node.getParent();
        newNode.colorBlack();
        node.colorRed();
        applyRightRightDelete(newNode);
    }
}
