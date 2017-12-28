package Trees.Tests;

import Trees.RedBlackTree.RedBlackNode;
import Trees.RedBlackTree.RedBlackTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link RedBlackTree}.
 * @author Jieyang
 */
public class RedBlackTreeTest {

    @Test
    // Test insert node to empty tree
    public void InsertEmptyTree() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test insert node to black parent
    public void InsertBlackParent() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test insert node to red parent with red uncle
    public void InsertRedUncleSimpleCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(68));
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test insert node to red parent with black uncle right right case
    public void InsertBlackUncleRightRightCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(68));
        tree.insert(new RedBlackNode(70));
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test insert node to red parent with black uncle right left case
    public void InsertBlackUncleRightLeftCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(68));
        tree.insert(new RedBlackNode(65));
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test insert node to red parent with black uncle left left case
    public void InsertBlackUncleLeftLeftCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(20));
        tree.insert(new RedBlackNode(10));
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test insert node to red parent with black uncle left right case
    public void InsertBlackUncleLeftRightCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(20));
        tree.insert(new RedBlackNode(25));
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test insert node to red parent with red uncle complex case
    public void InsertRedUncleComplexCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(20));
        tree.insert(new RedBlackNode(30));
        tree.insert(new RedBlackNode(10));
        tree.insert(new RedBlackNode(23));
        tree.insert(new RedBlackNode(5));
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete root node from tree with only root
    public void DeleteRootNodeSimpleCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        RedBlackNode node = (RedBlackNode)tree.getNode(55);
        tree.delete(node);
        assertNull(tree.getRoot());
    }

    @Test
    // Test delete node with one red child
    public void DeleteNodeOneRedChild() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        RedBlackNode node = (RedBlackNode)tree.getNode(55);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete node with two red child
    public void DeleteNodeTwoRedChild() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        RedBlackNode node = (RedBlackNode)tree.getNode(55);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete leaf node with black sibling and red nephew left left case
    public void DeleteLeafNodeBlackSiblingRedNephewLeftLeftCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(20));
        RedBlackNode node = (RedBlackNode)tree.getNode(60);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete leaf node with black sibling and red nephew left right case
    public void DeleteLeafNodeBlackSiblingRedNephewLeftRightCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(30));
        RedBlackNode node = (RedBlackNode)tree.getNode(60);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete leaf node with black sibling and red nephew right right case
    public void DeleteLeafNodeBlackSiblingRedNephewRightRightCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(65));
        RedBlackNode node = (RedBlackNode)tree.getNode(29);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete leaf node with black sibling and red nephew right left case
    public void DeleteLeafNodeBlackSiblingRedNephewRightLeftCase() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(57));
        RedBlackNode node = (RedBlackNode)tree.getNode(29);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete leaf node with black sibling and no red nephew
    public void DeleteLeafNodeBlackSiblingNoRedNephew() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(10));
        RedBlackNode node = (RedBlackNode)tree.getNode(10);
        tree.delete(node);
        node = (RedBlackNode)tree.getNode(29);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete leaf node with red sibling left case
    public void DeleteLeafNodeRedSiblingLeftCase(){
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(10));
        tree.insert(new RedBlackNode(5));
        tree.insert(new RedBlackNode(1));
        RedBlackNode node = (RedBlackNode)tree.getNode(60);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

    @Test
    // Test delete leaf node with red sibling right case
    public void DeleteLeafNodeRedSiblingRightCase(){
        RedBlackTree tree = new RedBlackTree();
        tree.insert(new RedBlackNode(55));
        tree.insert(new RedBlackNode(29));
        tree.insert(new RedBlackNode(60));
        tree.insert(new RedBlackNode(57));
        tree.insert(new RedBlackNode(68));
        tree.insert(new RedBlackNode(70));
        RedBlackNode node = (RedBlackNode)tree.getNode(29);
        tree.delete(node);
        assertTrue(Utility.checkRedBlackProperties(tree));
    }

}
