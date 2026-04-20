package binarySearchTree;

import exceptions.ValueException;

public class BinarySearchTree<T extends Comparable<T>> {

    protected class BinaryTreeNode {
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public T data;

        public BinaryTreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * save the minimal and maximal value in the tree
     */
    private T min;
    private T max;

    /**
     * Root node of the tree.
     **/
    protected BinaryTreeNode root;

    /**
     * Number of elements stored in the tree.
     */
    protected int size;

    /** Inserts the given element. Null-elements are not allowed. */
    public boolean insert(T elem) throws Exception {
        if (elem == null) throw new ValueException("Element is Null");
        BinaryTreeNode current;
        BinaryTreeNode n = new BinaryTreeNode(elem);
        if (root == null) {
            root = n;
            size++;
            min = root.data;
            max = root.data;
            return true;
        }
        current = getBinaryTreeNode(elem);  // returns null if root is null -> already checked above
        if (current.data.compareTo(elem) != 0) {         // current is parent of new BinaryTreeNode with data = elem
            if (isRight(current, elem)) {
                current.right = n;
                if (n.data.compareTo(max) > 0) max = n.data;
            } else {
                current.left = n;
                if (n.data.compareTo(min) < 0) min = n.data;
            }
            size++;
            return true;
        }
        return false;
    }


    /** Searches for the (first) element with the given key. Returns true
     if the element could be found, false otherwise. */

    public boolean find(T key) throws ValueException{
        if (key == null) throw new ValueException("Key is null");
        BinaryTreeNode p = getBinaryTreeNode(key);
        if (p == null) return false; // tree is empty
        else return p.data.compareTo(key) == 0;
    }


    // helper

    /**
     * returns the BinaryTreeNode with val = key or if it is not found it returns the last BinaryTreeNode before the location where
     * the BinaryTreeNode with requested key should be found
     */

    private BinaryTreeNode getBinaryTreeNode(T key) throws ValueException{
        if (key == null) throw new ValueException("Key is null");
        BinaryTreeNode prev = null;
        BinaryTreeNode current = root;
        if (root == null) return null;
        else if (root.data.compareTo(key) == 0) {
            return root;        // key found
        } else while (current != null && current.data.compareTo(key) != 0) {
            if (key.compareTo(current.data) < 0) {
                prev = current;
                current = current.left;
            } else {
                prev = current;
                current = current.right;
            }
        }
        if (current == null) {
            return prev; // key not found
        }
        return current; // key found
    }

    // helper

    /**
     * checks if the node with val = elem is on the left or right of parent.
     * Returns true if node is right child of parent and false if child is on the left
     * if parent is null -> throws exception
     */
    private boolean isRight(BinaryTreeNode parent, T elem) throws Exception {
        if (parent == null) throw new NullPointerException("Parent is null");
        if (elem == null) throw new ValueException("Element is null");

        return elem.compareTo(parent.data) > 0;

    }


    /**
     * Removes the element with the given key. Returns true if
     * the key could be found (and removed), false otherwise.
     */
    public boolean remove(T key) throws Exception { // left or right from parent check just one time

        if (key == null) throw new ValueException("Key is null");
        if (root == null) throw new NullPointerException("Root is null");

        BinaryTreeNode current = getBinaryTreeNode(key); // node we want to remove
        BinaryTreeNode parent = getParentNode(key);     // parent of node we want to remove

        if (current == null || current.data.compareTo(key) != 0) return false; // key not in tree

        boolean right = parent != null && isRight(parent, key); // check if the node we want to remove is left or right child of parent

        // case 1: leaf node
        if (current.left == null && current.right == null) {
            if (parent == null) root = null;             // removing root
            else if (right) parent.right = null;
            else parent.left = null;

            // case 2: node has just one child
        } else if (current.left == null || current.right == null) {
            BinaryTreeNode child = (current.left != null) ? current.left : current.right; // child that replaces current
            if (parent == null) root = child;            // removing root
            else if (right) parent.right = child;    // right child replaces current
            else parent.left = child;     // left child replaces current

            // case 3a: left child has no right successor → promote left child
        } else if (current.left.right == null) {
            current.left.right = current.right;           // attach current's right subtree to left child
            if (parent == null) root = current.left;     // removing root
            else if (right) parent.right = current.left; // connect parent (right) with current.left
            else parent.left = current.left;  // connect parent (left) with current.left

            // case 3a: right child has no left successor → promote right child
        } else if (current.right.left == null) {
            current.right.left = current.left;            // attach current's left subtree to right child
            if (parent == null) root = current.right;    // removing root
            else if (right) parent.right = current.right; // connect parent (right) with current.right
            else parent.left = current.right;  // connect parent (left) with current.right

            // case 3b: general case — find inorder successor = symmetrical next (right-left-left-...-null)
        } else {
            BinaryTreeNode parentSymmetricalNext = current;       // parent of the inorder successor
            BinaryTreeNode symmetricalNext = current.right;       // start at right subtree
            while (symmetricalNext.left != null) {                // walk as far left as possible
                parentSymmetricalNext = symmetricalNext;
                symmetricalNext = symmetricalNext.left;
            }

            // step 2: detach symmetricalNext — left pointer of parent references to right child of successor
            parentSymmetricalNext.left = symmetricalNext.right;  // successor was further down left spine

            // step 3: replace current with symmetricalNext
            symmetricalNext.left = current.left;
            symmetricalNext.right = current.right;

            // step 4: restore parent-child relation
            if (parent == null) root = symmetricalNext;          // removing root
            else if (right) parent.right = symmetricalNext;  // connect parent (right) with symmetricalNext
            else parent.left = symmetricalNext;   // connect parent (left) with symmetricalNext
        }
        size--;
        if (size > 0) {
            T[] sorted = toArray((T[]) new Comparable[size], true);
            min = sorted[0];
            max = sorted[size - 1];
        }
        return true;
    }


    /**
     * Returns the number of elements stored in the tree.
     */
    public int size() {
        return size;
    }

    //helper

    /**
     * Returns the parent node of element with the given key
     */

    private BinaryTreeNode getParentNode(T key) throws ValueException{
        if (key == null) throw new ValueException("Key is null");
        BinaryTreeNode prev = null;
        BinaryTreeNode current = root;
        if (root == null) return null;
        else if (root.data.compareTo(key) == 0) {
            return null; // check what to return if elem is in root
        } else while (current != null && current.data.compareTo(key) != 0) {
            if (key.compareTo(current.data) < 0) {
                prev = current;
                current = prev.left;
            } else {
                prev = current;
                current = prev.right;
            }
        }
        if (current == null) {
            return null;
        }
        return prev;
    }


    /**
     * Returns the parent element of the given key. Throws exception if
     * parent is null
     */
    public T getParent(T key) throws ValueException {
        BinaryTreeNode parent = getParentNode(key);
        if (parent == null) return null;
        return parent.data;
    }


    /**
     * Returns the elements of the tree in ascending (inorder traversal)
     * or descending (reverse inorder traversal) order.
     */
    public T[] toArray(T[] a, boolean ascending) {
        toArray(a, ascending, 0, root);
        return a;  // no more manual reversal needed
    }

    //helper

    /**
     * Recursive methode to get always the value of the lower visited node
     */
    private int toArray(T[] ret, boolean ascending, int offset, BinaryTreeNode n) {
        if (n == null) return offset;
        if (ascending) {
            offset = toArray(ret, ascending, offset, n.left);   // left first
            ret[offset++] = n.data;
            offset = toArray(ret, ascending, offset, n.right);
        } else {
            offset = toArray(ret, ascending, offset, n.right);  // right first
            ret[offset++] = n.data;
            offset = toArray(ret, ascending, offset, n.left);
        }
        return offset;
    }


    /**
     * Returns the elements of the tree (postorder traversal).
     */
    public T[] toArrayPostOrder(T[] ret) {
        postOrder(ret, 0, root);
        return ret;
    }

    // helper

    /**
     * Recursive methode to get always the value of the right visited node
     */
    private int postOrder(T[] ret, int offset, BinaryTreeNode n) {
        if (n == null) return offset;
        offset = postOrder(ret, offset, n.left);
        offset = postOrder(ret, offset, n.right);
        ret[offset++] = n.data;
        return offset;
    }


    /**
     * Returns the elements of the tree (preorder traversal).
     */
    public T[] toArrayPreOrder(T[] ret) {
        preOrder(ret, 0, root);
        return ret;
    }

    // helper

    /**
     * Recursive methode to get always the value of the left visited node
     */
    private int preOrder(T[] ret, int offset, BinaryTreeNode n) {
        if (n == null) return offset;
        ret[offset++] = n.data;
        offset = preOrder(ret, offset, n.left);
        offset = preOrder(ret, offset, n.right);
        return offset;
    }


    /**
     * Returns largest number stored in the tree. Integer.MIN_VALUE if
     * no largest element can be found
     */
    public T max() { // right right right till next is null
        if (root == null) return null;
        return this.max;
    }

    /**
     * Returns smallest number stored in the tree. Integer.MIN_VALUE if
     * no smallest element can be found
     */
    public T min() {
        if (root == null) return null;
        return this.min;
    }

    /**
     * Represents the tree in a human readable form.
     */

    public String toString() {
        return toStringHelper(root, 0);
    }

    // here I got help of AI (claude), the idea of the recursion I had on my own, but I used some inspiration also of the example the prof showed us
    private String toStringHelper(BinaryTreeNode node, int depth) {
        if (node == null) return "";
        return toStringHelper(node.right, depth + 1)
                + "   ".repeat(depth) + node.data + "\n"
                + toStringHelper(node.left, depth + 1);
    }


}