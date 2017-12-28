package Trees.Tests;

import Trees.RedBlackTree.RedBlackNode;
import Trees.RedBlackTree.RedBlackTree;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains utility methods for unit tests.
 * @author Jieyang
 */
public class Utility {

    /**
     * Method to verify if a Red Black Tree satisfies all its properties.
     * @param tree A Red Black tree
     * @return boolean indicating if the Red Black tree satisfies all its properties
     */
    public static boolean checkRedBlackProperties(RedBlackTree tree) {
        List<Integer> blackHeightList = new ArrayList<>();
        RedBlackNode root = (RedBlackNode)tree.getRoot();
        if (root != null) {
            // verify black height to all leaf nodes are equal
            getAllBlackHeight(root, root, 0, blackHeightList);
            boolean blackHeightEqual = true;
            int blackHeight = blackHeightList.get(0);
            for(int height : blackHeightList) {
                if (height != blackHeight) {
                    blackHeightEqual = false;
                    break;
                }
            }
            // verify there are no consecutive red nodes
            boolean hasConsecutiveRed = checkConsecutiveRed(root, root);
            return ((RedBlackNode) tree.getRoot()).isBlack() && !hasConsecutiveRed && blackHeightEqual;
        }
        else {
            return false;
        }
    }

    /**
     * Recursively traverse the tree to find black height to all leaf nodes.
     * @param root root of tree
     * @param node current node (start with root)
     * @param blackHeight current black height (start with 0)
     * @param blackHeightList list to keep track of all black heights
     */
    private static void getAllBlackHeight(RedBlackNode root, RedBlackNode node, int blackHeight, List<Integer> blackHeightList) {
        if (node == null) {
            blackHeightList.add(blackHeight+1);
            return;
        }
        if (node.isBlack()) {
            if(node != root) {
                blackHeight ++;
            }
        }
        getAllBlackHeight(root, (RedBlackNode)node.getLeft(), blackHeight, blackHeightList);
        getAllBlackHeight(root, (RedBlackNode)node.getRight(), blackHeight, blackHeightList);
    }

    /**
     * Recursively traverse the tree to check if tree contains two consecutive red nodes.
     * @param root root of tree
     * @param node current node (start with root)
     * @return boolean indicating if tree contains two consecutive red nodes
     */
    private static boolean checkConsecutiveRed(RedBlackNode root, RedBlackNode node) {
        if (node == null) {
            return false;
        }
        if (node != root) {
            if (!node.isBlack()) {
                RedBlackNode parent = (RedBlackNode)node.getParent();
                if (!parent.isBlack()) {
                    return true;
                }
            }
        }
        boolean leftTreeResult = checkConsecutiveRed(root, (RedBlackNode)node.getLeft());
        boolean rightTreeResult = checkConsecutiveRed(root, (RedBlackNode)node.getRight());
        return leftTreeResult || rightTreeResult;
    }
}
