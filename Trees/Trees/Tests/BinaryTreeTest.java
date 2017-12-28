package Trees.Tests;

import Trees.BinaryTree.BinaryTree;
import Trees.BinaryTree.INode;
import Trees.BinaryTree.Node;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link BinaryTree}.
 * @author Jieyang
 */
public class BinaryTreeTest {

    private static List<Integer> list = new ArrayList<>();

    @Test
    // Test right rotate root node with two children
    public void RightRotateRootSimpleCase() {
        BinaryTree tree = new BinaryTree();
        INode parent = new Node(2);
        INode leftChild = new Node(1);
        INode rightChild = new Node(3);
        tree.insert(parent);
        tree.insert(leftChild);
        tree.insert(rightChild);
        tree.rightRotate(parent);
        assertEquals(parent, leftChild.getRight());
        assertEquals(leftChild, parent.getParent());
        assertEquals(rightChild, parent.getRight());
        assertEquals(parent, rightChild.getParent());
        assertEquals(leftChild, tree.getRoot());
        assertEquals(2, parent.getData());
        assertEquals(1, leftChild.getData());
        assertEquals(3, rightChild.getData());
        assertNull(leftChild.getLeft());
        assertNull(parent.getLeft());
        assertNull(leftChild.getLeft());
        assertNull(rightChild.getLeft());
        assertNull(rightChild.getLeft());
    }

    @Test
    // Test left rotate root node with two children
    public void LeftRotateRootSimpleCase() {
        BinaryTree tree = new BinaryTree();
        INode parent = new Node(2);
        INode leftChild = new Node(1);
        INode rightChild = new Node(3);
        tree.insert(parent);
        tree.insert(leftChild);
        tree.insert(rightChild);
        tree.leftRotate(parent);
        assertEquals(parent, rightChild.getLeft());
        assertEquals(rightChild, parent.getParent());
        assertEquals(leftChild, parent.getLeft());
        assertEquals(parent, leftChild.getParent());
        assertEquals(rightChild, tree.getRoot());
        assertEquals(2, parent.getData());
        assertEquals(1, leftChild.getData());
        assertEquals(3, rightChild.getData());
        assertNull(leftChild.getRight());
        assertNull(parent.getRight());
        assertNull(leftChild.getRight());
        assertNull(leftChild.getLeft());
        assertNull(leftChild.getRight());
    }

    @Test
    // Test right rotate node with only left child
    public void RightRotateNodeOnlyLeftChild() {
        BinaryTree tree = new BinaryTree();
        INode parent = new Node(2);
        INode leftChild = new Node(1);
        tree.insert(parent);
        tree.insert(leftChild);
        tree.rightRotate(parent);
        assertEquals(leftChild, tree.getRoot());
        assertEquals(parent, leftChild.getRight());
        assertEquals(leftChild, parent.getParent());
        assertEquals(2, parent.getData());
        assertEquals(1, leftChild.getData());
        assertNull(leftChild.getLeft());
        assertNull(parent.getLeft());
        assertNull(parent.getRight());
    }

    @Test
    // Test right rotate node with only right child
    public void RightRotateNodeOnlyRightChild() {
        BinaryTree tree = new BinaryTree();
        INode parent = new Node(2);
        INode rightChild = new Node(3);
        tree.insert(parent);
        tree.insert(rightChild);
        tree.rightRotate(parent);
        assertEquals(parent, tree.getRoot());
        assertEquals(rightChild, parent.getRight());
        assertEquals(parent, rightChild.getParent());
        assertEquals(2, parent.getData());
        assertEquals(3, rightChild.getData());
        assertNull(parent.getLeft());
        assertNull(rightChild.getLeft());
        assertNull(rightChild.getRight());
    }

    @Test
    // Test left rotate node with only right child
    public void LeftRotateNodeOnlyRightChild() {
        BinaryTree tree = new BinaryTree();
        INode parent = new Node(2);
        INode rightChild = new Node(3);
        tree.insert(parent);
        tree.insert(rightChild);
        tree.leftRotate(parent);
        assertEquals(rightChild, tree.getRoot());
        assertEquals(parent, rightChild.getLeft());
        assertEquals(rightChild, parent.getParent());
        assertNull(rightChild.getRight());
        assertNull(parent.getLeft());
        assertNull(parent.getRight());
    }

    @Test
    // Test left rotate node with only left child
    public void LeftRotateNodeOnlyLeftChild() {
        BinaryTree tree = new BinaryTree();
        INode parent = new Node(2);
        INode leftChild = new Node(1);
        tree.insert(parent);
        tree.insert(leftChild);
        tree.leftRotate(parent);
        assertEquals(parent, tree.getRoot());
        assertEquals(leftChild, parent.getLeft());
        assertEquals(parent, leftChild.getParent());
        assertEquals(2, parent.getData());
        assertEquals(1, leftChild.getData());
        assertNull(parent.getRight());
        assertNull(leftChild.getLeft());
        assertNull(leftChild.getRight());
    }

    @Test
    // Test right rotate root with grand children
    public void RightRotateRootFullTree() {
        BinaryTree tree = new BinaryTree();
        INode grandParent = new Node(5);
        INode rightChild = new Node(6);
        INode leftChild = new Node(3);
        INode leftGrandchild = new Node(1);
        INode rightGrandchild = new Node(4);
        tree.insert(grandParent);
        tree.insert(leftChild);
        tree.insert(rightChild);
        tree.insert(leftGrandchild);
        tree.insert(rightGrandchild);
        tree.rightRotate(grandParent);
        assertEquals(leftChild, tree.getRoot());
        assertEquals(grandParent, leftChild.getRight());
        assertEquals(leftChild, grandParent.getParent());
        assertEquals(leftGrandchild, leftChild.getLeft());
        assertEquals(leftChild, leftGrandchild.getParent());
        assertEquals(rightGrandchild, grandParent.getLeft());
        assertEquals(grandParent, rightGrandchild.getParent());
        assertEquals(rightChild, grandParent.getRight());
        assertEquals(grandParent, rightChild.getParent());
        assertEquals(5, grandParent.getData());
        assertEquals(6, rightChild.getData());
        assertEquals(3, leftChild.getData());
        assertEquals(1, leftGrandchild.getData());
        assertEquals(4, rightGrandchild.getData());
        assertNull(rightGrandchild.getLeft());
        assertNull(rightGrandchild.getRight());
        assertNull(leftGrandchild.getLeft());
        assertNull(leftGrandchild.getRight());
        assertNull(rightChild.getLeft());
        assertNull(rightChild.getRight());
    }

    @Test
    // Test left rotate root with grand children
    public void LeftRotateRootFullTree() {
        BinaryTree tree = new BinaryTree();
        INode grandParent = new Node(5);
        INode rightChild = new Node(8);
        INode leftChild = new Node(4);
        INode leftGrandchild = new Node(6);
        INode rightGrandchild = new Node(9);
        tree.insert(grandParent);
        tree.insert(leftChild);
        tree.insert(rightChild);
        tree.insert(leftGrandchild);
        tree.insert(rightGrandchild);
        tree.leftRotate(grandParent);
        assertEquals(rightChild, tree.getRoot());
        assertEquals(grandParent, rightChild.getLeft());
        assertEquals(rightChild, grandParent.getParent());
        assertEquals(rightGrandchild, rightChild.getRight());
        assertEquals(rightChild, rightGrandchild.getParent());
        assertEquals(leftGrandchild, grandParent.getRight());
        assertEquals(grandParent, leftGrandchild.getParent());
        assertEquals(leftChild, grandParent.getLeft());
        assertEquals(grandParent, leftChild.getParent());
        assertEquals(5, grandParent.getData());
        assertEquals(8, rightChild.getData());
        assertEquals(4, leftChild.getData());
        assertEquals(6, leftGrandchild.getData());
        assertEquals(9, rightGrandchild.getData());
        assertNull(rightGrandchild.getLeft());
        assertNull(rightGrandchild.getRight());
        assertNull(leftGrandchild.getLeft());
        assertNull(leftGrandchild.getRight());
        assertNull(leftChild.getLeft());
        assertNull(leftChild.getRight());
    }

    @Test
    // Test left rotate node that has parent
    public void LeftRotateNodeWithParent() {
        BinaryTree tree = new BinaryTree();
        INode grandParent = new Node(5);
        INode rightChild = new Node(8);
        INode leftChild = new Node(4);
        INode leftGrandchild = new Node(6);
        INode rightGrandchild = new Node(9);
        tree.insert(grandParent);
        tree.insert(leftChild);
        tree.insert(rightChild);
        tree.insert(leftGrandchild);
        tree.insert(rightGrandchild);
        tree.leftRotate(rightChild);
        assertEquals(grandParent, tree.getRoot());
        assertEquals(rightGrandchild, grandParent.getRight());
        assertEquals(grandParent, rightGrandchild.getParent());
        assertEquals(rightChild, rightGrandchild.getLeft());
        assertEquals(rightGrandchild, rightChild.getParent());
        assertEquals(leftGrandchild, rightChild.getLeft());
        assertEquals(rightChild, leftGrandchild.getParent());
        assertEquals(leftChild, grandParent.getLeft());
        assertEquals(grandParent, leftChild.getParent());
        assertEquals(5, grandParent.getData());
        assertEquals(8, rightChild.getData());
        assertEquals(4, leftChild.getData());
        assertEquals(6, leftGrandchild.getData());
        assertEquals(9, rightGrandchild.getData());
        assertNull(leftChild.getLeft());
        assertNull(leftChild.getRight());
        assertNull(leftGrandchild.getRight());
        assertNull(leftGrandchild.getLeft());
        assertNull(rightChild.getRight());
        assertNull(rightGrandchild.getRight());
    }

    @Test
    // Test right rotate node that has parent
    public void RightRotateNodeWithParent() {
        BinaryTree tree = new BinaryTree();
        INode grandParent = new Node(5);
        INode rightChild = new Node(8);
        INode leftChild = new Node(4);
        INode leftGrandchild = new Node(6);
        INode rightGrandchild = new Node(9);
        tree.insert(grandParent);
        tree.insert(leftChild);
        tree.insert(rightChild);
        tree.insert(leftGrandchild);
        tree.insert(rightGrandchild);
        tree.rightRotate(rightChild);
        assertEquals(grandParent, tree.getRoot());
        assertEquals(leftGrandchild, grandParent.getRight());
        assertEquals(grandParent, leftGrandchild.getParent());
        assertEquals(rightChild, leftGrandchild.getRight());
        assertEquals(leftGrandchild, rightChild.getParent());
        assertEquals(rightGrandchild, rightChild.getRight());
        assertEquals(rightChild, rightGrandchild.getParent());
        assertEquals(leftChild, grandParent.getLeft());
        assertEquals(grandParent, leftChild.getParent());
        assertEquals(5, grandParent.getData());
        assertEquals(8, rightChild.getData());
        assertEquals(4, leftChild.getData());
        assertEquals(6, leftGrandchild.getData());
        assertEquals(9, rightGrandchild.getData());
        assertNull(leftChild.getLeft());
        assertNull(leftChild.getRight());
        assertNull(rightGrandchild.getRight());
        assertNull(rightGrandchild.getLeft());
        assertNull(rightChild.getLeft());
        assertNull(leftGrandchild.getLeft());
    }

    @Test
    // Test delete node with one child
    public void DeleteNodeWithOneChild() {
        BinaryTree tree = new BinaryTree();
        INode grandParent = new Node(5);
        INode leftChild = new Node(4);
        INode leftGrandchild = new Node(3);
        tree.insert(grandParent);
        tree.insert(leftChild);
        tree.insert(leftGrandchild);
        tree.delete(leftChild);
        assertEquals(grandParent, tree.getRoot());
        assertEquals(leftGrandchild, grandParent.getLeft());
        assertEquals(grandParent, leftGrandchild.getParent());
        assertNull(grandParent.getRight());
        assertNull(leftGrandchild.getLeft());
        assertNull(leftGrandchild.getRight());
    }

    @Test
    // Test delete node with two child
    public void DeleteNodeWithTwoChild() {
        BinaryTree tree = new BinaryTree();
        INode grandParent = new Node(5);
        INode leftChild = new Node(3);
        INode leftGrandchild = new Node(2);
        INode rightGrandchild = new Node(4);
        tree.insert(grandParent);
        tree.insert(leftChild);
        tree.insert(leftGrandchild);
        tree.insert(rightGrandchild);
        tree.delete(leftChild);
        assertEquals(grandParent, tree.getRoot());
        assertEquals(leftChild, grandParent.getLeft());
        assertEquals(grandParent, leftChild.getParent());
        assertEquals(leftGrandchild, leftChild.getLeft());
        assertEquals(leftChild, leftGrandchild.getParent());
        assertEquals(5, grandParent.getData());
        assertEquals(4, leftChild.getData());
        assertEquals(2, leftGrandchild.getData());
        assertNull(grandParent.getRight());
        assertNull(leftChild.getRight());
        assertNull(leftGrandchild.getLeft());
        assertNull(leftGrandchild.getRight());
    }

    @Test
    // Test search tree
    public void GetNode() {
        BinaryTree tree = new BinaryTree();
        tree.insert(new Node(55));
        tree.insert(new Node(30));
        tree.insert(new Node(69));
        tree.insert(new Node(90));
        tree.insert(new Node(15));
        assertEquals(55, tree.getNode(55).getData());
        assertEquals(30, tree.getNode(30).getData());
        assertEquals(69, tree.getNode(69).getData());
        assertEquals(90, tree.getNode(90).getData());
        assertEquals(15, tree.getNode(15).getData());
        assertNull(tree.getNode(-1));
    }

    @Test
    // Test inorder traversal
    public void InOrderTraversal(){
        Consumer<INode> appender = BinaryTreeTest::listAppender;
        BinaryTree tree = new BinaryTree();
        tree.insert(new Node(5));
        tree.insert(new Node(7));
        tree.insert(new Node(4));
        tree.insert(new Node(0));
        tree.insert(new Node(6));
        tree.insert(new Node(8));
        tree.insert(new Node(2));
        tree.insert(new Node(9));
        tree.insert(new Node(3));
        tree.insert(new Node(1));
        tree.inOrderTraversal(appender);
        for(int i = 0; i < list.size(); i++) {
            assertEquals(i, (int)list.get(i));
        }
    }

    @Test
    // Test get least value node
    public void GetLeastValueNode() {
        BinaryTree tree = new BinaryTree();
        tree.insert(new Node(5));
        tree.insert(new Node(7));
        tree.insert(new Node(4));
        tree.insert(new Node(0));
        tree.insert(new Node(6));
        tree.insert(new Node(8));
        tree.insert(new Node(2));
        tree.insert(new Node(9));
        tree.insert(new Node(3));
        tree.insert(new Node(1));
        assertEquals(0, tree.getLeastValueNode(tree.getRoot()).getData());
    }

    private static void listAppender(INode node) {
        list.add(node.getData());
    }
}