
import java.io.*;
import java.util.*;

/**
 *
 * @author Kotah Chapman Date Written : 11/8/2015 Purpose : creation of a Binary
 * Search Tree
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    /**
     *
     */
    protected TreeNode<E> root;

    /**
     *
     */
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     *
     * @param objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    private int i = 0;

    /**
     * @requires : element of valid type and Integer array
     * @ensures : If element is found in tree and number of comparisons to find
     * or not find element
     * @param e : element
     * @return true if element is found, false if element is not found
     */
    public boolean search(E e, int[] count) {
        count[0] = i;
        return search(e);
    }

    /**
     * Returns true if the element is in the tree
     *
     * @param e
     * @return
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        i = 0;
        while (current != null) {
            i++;
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     *
     * @param e
     * @return
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    /**
     *
     * @param e
     * @return
     */
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     *
     * @param root
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     *
     * @param root
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     *
     * @param root
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Inner class tree node
     *
     * @param <E>
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        /**
         *
         * @param e
         */
        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     *
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     *
     * @requires : an element of valid type
     * @ensures : Finding all elements from nodes leading to specified element
     * @param e : an element
     * @return : an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     */
    public ArrayList<E> path(E e) {
        ArrayList<E> list = new ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        //implement the code here as in search method.
        while (current != null) {
            list.add(current.element);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return list;
            }
        }
        list.removeAll(list);
        return list;
    }


    /*
     * 
     * @ensures : calling of getNumberOfLeaves helper method
     * @return : the number of leaf nodes in this tree, returns 0 if tree is empty
     */
    protected int getNumberOfLeaves() {
        return getNumberOfLeaves(root);
    }

    /**
     * @requires : a valid node from a BinarySearchTree
     * @ensures : finding and adding of all leaf nodes recursively
     * @param currentRoot : a tree node of BinarySearchTree
     * @return : the number of leaf nodes in this tree, returns 0 if tree is
     * empty
     */
    public int getNumberOfLeaves(TreeNode<E> currentRoot) {
        if (currentRoot == null) {
            return 0;
        } else if (currentRoot.left == null && currentRoot.right == null) {
            return 1;
        } else {
            return getNumberOfLeaves(currentRoot.left) + getNumberOfLeaves(currentRoot.right);
        }
    }
    private ArrayList<E> list;

    /**
     * @requires : a valid node from a BinarySearchTree
     * @ensures : adding of elements to ArrayList in preorder.
     * @param currentRoot : a tree node of BinarySearchTree
     */
    protected void leftSubTree(TreeNode<E> currentRoot) {
        if (currentRoot == null) {
            return;
        }
        list.add(currentRoot.element);
        preorder(currentRoot.left);
        preorder(currentRoot.right);
    }

    /**
     * @requires : an element of valid type
     * @ensures : finding of specified element sent if it exists, and calling of
     * helper method for ArrayList of elements in preorder of left subtree of
     * specified element
     * @param e : an element
     * @return : Returns an ArrayList containing all elements in preorder of the
     * specified element’s left sub-tree, returns an empty ArrayList if no such
     * element exists.
     */
    public ArrayList<E> leftSubTree(E e) {
        list = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                if (current.left != null) {
                    current = current.left;
                } else {
                    leftSubTree(current);
                }
                leftSubTree(current);
                return list;
            }
        }
        return list;
    }

    /**
     * @requires : a valid node from a BinarySearchTree
     * @ensures : adding of elements to ArrayList in preorder.
     * @param currentRoot : a tree node of BinarySearchTree
     */
    protected void rightSubTree(TreeNode<E> currentRoot) {
        if (currentRoot == null) {
            return;
        }
        list.add(currentRoot.element);
        preorder(currentRoot.left);
        preorder(currentRoot.right);
    }

    /**
     * @requires : an element of valid type
     * @ensures : finding of specified element sent if it exists, and calling of
     * helper method for ArrayList of elements in preorder of right subtree of
     * specified element
     * @param e : an element
     * @return: an ArrayList containing all elements in preorder of the
     * specified element’s right sub-tree, returns an empty ArrayList if no such
     * element exists.
     */
    public ArrayList<E> rightSubTree(E e) {
        list = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                if (current.right != null) {
                    current = current.right;
                } else {
                    return list;
                }
                rightSubTree(current);
                return list;
            }
        }
        return list;
    }

    /**
     * @requires : an element of valid type
     * @ensures : finding of specified element and its predecessor in its right
     * most node of its left subtree
     * @param e : an element
     * @return : the inorder predecessor of the specified element, returns null
     * if tree is empty or element 'e' is not in the tree.
     */
    public E inorderPredecessor(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        if (current == null) {
            return null;
        }
        if (current.left == null) {
            return current.element;
        } else {
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }
            return rightMost.element;
        }
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     *
     * @param e
     * @return
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     *
     * @return
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     *
     * @return
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {

        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
