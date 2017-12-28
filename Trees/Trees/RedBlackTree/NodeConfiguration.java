package Trees.RedBlackTree;

/**
 * Describes possible configurations for a node.
 * @author Jieyang
 */
public enum NodeConfiguration {
    /**
     * Uncle - Parent is left child, and node is left grandchild.
     */
    LEFTLEFT,

    /**
     * Uncle node Parent right child, and node is right grandchild.
     */
    RIGHTRIGHT,

    /**
     * Parent is left child, and node is right grandchild.
     */
    LEFTRIGHT,

    /**
     * Parent is right child, and node is left grandchild.
     */
    RIGHTLEFT,

    /**
     * Invalid configuration (e.g. when grandparent is null).
     */
    INVALID
}
