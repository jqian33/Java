package Trees;

import Trees.BinaryTree.BinaryTree;
import Trees.BinaryTree.INode;
import Trees.RedBlackTree.RedBlackNode;
import Trees.BinaryTree.Node;
import Trees.RedBlackTree.RedBlackTree;
import java.util.function.Consumer;

/**
 * Driver. Create trees, insert and delete nodes,
 * and use the print methods to view the tree in the console.
 * @author Jieyang
 */
public class Driver {

    public static final String ANSI_BLACK = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Consumer<INode> printer = Driver::print;
        Consumer<INode> printerRB = Driver::printRB;

        // initialize a Red Black tree
        RedBlackTree tree = new RedBlackTree(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(57));
        tree.insert(new RedBlackNode(68));

        // print Red Black tree
        tree.levelTraversal(printerRB);
        System.out.println(ANSI_BLACK);

        // initialize a binary tree
        BinaryTree bTree = new BinaryTree();
        bTree.insert(new Node(5));
        bTree.insert(new Node(7));
        bTree.insert(new Node(4));
        bTree.insert(new Node(0));
        bTree.insert(new Node(6));
        bTree.insert(new Node(8));
        bTree.insert(new Node(2));
        bTree.insert(new Node(9));
        bTree.insert(new Node(3));
        bTree.insert(new Node(1));

        // print binary tree
        bTree.levelTraversal(printer);
        System.out.println();
    }

    // Method to print value of red black tree node
    private static void printRB(INode node) {
        RedBlackNode redBlackNode = (RedBlackNode) node;
        if (node == null) {
            System.out.print(ANSI_BLACK + "nil ");
        }
        else {
            if (!redBlackNode.isBlack()) {
                System.out.print(ANSI_RED + redBlackNode.getData() + " ");
            }
            else {
                System.out.print(ANSI_BLACK + redBlackNode.getData() + " ");
            }
        }
    }

    // Method to print value of a binary tree node
    private static void print(INode node) {
        if (node == null) {
            System.out.print("nil ");
        }
        else {
            System.out.print(node.getData() + " ");
        }
    }
}

