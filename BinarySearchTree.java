/**
 * BinarySearchTree class 
 * 
 * @author Joowon Kim
 * 
 * */

import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
	private BSTNode<E> root;
	private int size;

	public BinarySearchTree() {
		
		root = null;
		size = 0;
	}

	public BinarySearchTree(BSTNode<E> node) {
		
		root = node;
		size = 1;
	}

	/**
	 * searches for a node that contains it. if it finds it, it returns that
	 * node else it returns null
	 * 
	 * @param it
	 *            - the element to look for
	 * @return the node that contains it
	 */
	public BSTNode<E> search(E it) {

		BSTNode<E> node = root;

		while (node != null) {

			int res = it.compareTo(node.getElement());

			if (res < 0) {
				node = node.getLeft();
			} else if (res > 0) {

				node = node.getRight();
			} else {

				return node;
			}
		}

		System.out.println(it + " is not in tree");
		return null;

	}

	/**
	 * determines is the tree contains the element
	 * 
	 * @return true if it is in the tree
	 */
	public boolean contains(E it) {
		BSTNode<E> node = root;
		if (root == null) {
			return false;
		}
		if (it.compareTo(node.getElement()) == 0) {
			return true;
		} else if (it.compareTo(node.getElement()) < 0) {
			node = node.getLeft();
		} else if (it.compareTo(node.getElement()) > 0) {
			node = node.getRight();
		}
		System.out.println(it + " is in tree");
		return true;
	}

	/**
	 * Add the element to the correct location all elements to the left are less
	 * than the parent all elements to the rights are greater than the parent Do
	 * not allow duplicates
	 * 
	 * @param it
	 *            the element to insert
	 */
	public void insert(E it) {

		BSTNode<E> newNode = new BSTNode<E>(it);
		if (root == null) {
			root = newNode;

			return;
		}
		BSTNode<E> parent = null;
		BSTNode<E> node = root;
		while (node != null) {
			parent = node;
			int compareResult = it.compareTo(node.getElement());
			if (compareResult < 0) {
				node = node.getLeft();
			} else if (compareResult > 0) {
				node = node.getRight();
			} else {
				// duplicate
				return;
			}
		}

		int res = it.compareTo(parent.getElement());
		if (res < 0) {
			parent.setLeft(newNode);

		} else {
			parent.setRight(newNode);
		}
		size++;
	}

	/**
	 * Removes the node that contains it. If the tree does not contain it, it
	 * prints that to the user and does nothing else. Otherwise it removes the
	 * node and maintains the BST properties if removing a node with two
	 * children, replace it with its inorder predecessor.
	 * 
	 * @param the
	 *            element of the node you want to remove.
	 */
	public void remove(E it) {

		BSTNode<E> parent = null;
		BSTNode<E> child = null;
		BSTNode<E> node = root;
		// find the node that contains it

		while (node != null && node.getElement() != it) {

			parent = node;
			int compareResult = it.compareTo(node.getElement());

			if (compareResult < 0) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}

		if (node == null) {

			System.out.println("Failed to find: " + it + "for removal");
			return;
		}

		if (node.isLeaf()) {

			if (parent == null) {

				root = null;
			} else if (it.compareTo(parent.getElement()) < 0) {

				parent.setLeft(null);
			} else {

				parent.setRight(null);
			}
		} else if (node.getLeft() == null) {

			child = node.getRight();
			swapElements(node, child);
			node.setLeft(child.getLeft());
			node.setRight(child.getRight());
		} else if (node.getRight() == null) {

			child = node.getLeft();
			swapElements(node, child);
			node.setLeft(child.getLeft());
			node.setRight(child.getRight());
		} else {

			child = node.getLeft();
			parent = null;

			while (child.hasRight()) {

				parent = child;
				child = child.getRight();
			}

			if (parent == null) {

				swapElements(node, child);
				node.setLeft(child.getLeft());
			} else {

				swapElements(node, child);
				parent.setRight(child.getLeft());
			}
		}

		size--;
	}

	/**
	 * Returns the height of the tree if tree is empty, height is -1 if tree
	 * only has one node, height is 0
	 * 
	 * @return the integer height of the tree
	 *
	 */
	public int getHeight() {

		int height = -1;
		Queue<BSTNode> q = new QueueArray<BSTNode>();
		if (root == null) {
			return height;
		}
		q.enqueue(root);
		while (!q.isEmpty()) {
			int nodeCount = q.size();
			height++;
			while (nodeCount > 0) {
				BSTNode<E> node = q.dequeue();
				if (node.hasLeft()) {
					q.enqueue(node.getLeft());
				}
				if (node.hasRight()) {
					q.enqueue(node.getRight());
				}
				nodeCount--;
			} // end while nodeCount > 0
		} // end while q is not empty
		return height;
	}

	/**
	 * Helper method For removal you need to swap elements of nodes
	 * 
	 * @param node1
	 *            , node2 the nodes whose contents you are swapping
	 */
	private void swapElements(BSTNode<E> node1, BSTNode<E> node2) {

		E element1 = node1.getElement();
		E element2 = node2.getElement();

		node1.setElement(element2);
		node2.setElement(element1);
	}

	/**
	 * prints each level of the tree on its own line use your Queue class
	 */
	public void printLevelOrder() { // change

		Queue<BSTNode> Q = new QueueArray<BSTNode>();

		int count = 0;

		if (root == null) {

			return;
		}

		Q.enqueue(root);

		while (!Q.isEmpty()) {

			count = Q.size();

			while (count > 0) {

				BSTNode n = Q.dequeue();

				System.out.print(n.getElement() + " ");

				if (n.hasLeft()) {
					Q.enqueue(n.getLeft());
				}

				if (n.hasRight()) {
					Q.enqueue(n.getRight());
				}

				count--;

			}

			System.out.println();

		}

	}

	/**
	 * prints the tree in a depth-first fashion use your Stack class
	 */
	public void printByDepth() {

		Stack<BSTNode> S = new Stack<BSTNode>();
		S.push(root);

		while (!S.isEmpty()) {
			BSTNode<E> n = S.pop();
			System.out.println(n.getElement());

			if (n.hasRight()) {
				S.push(n.getRight());
			}

			if (n.hasLeft()) {
				S.push(n.getLeft());
			}
		}
	}

	/**
	 * prints the tree in an inorder fashion. uses a stack to push left children
	 * onto the stack
	 */
	public void printInOrder() { // change

		Stack<BSTNode> S = new Stack<BSTNode>();

		BSTNode node = root;
		pushLeftNodesToStack(S, node);
		while (!S.isEmpty()) {
			BSTNode<E> n = S.pop();
			// visit(n);
			System.out.println(n.getElement());
			n = n.getRight();
			pushLeftNodesToStack(S, n);
		}
	}

	public void pushLeftNodesToStack(Stack<BSTNode> s, BSTNode<E> b) {
		while (b != null) {
			s.push(b);
			b = b.getLeft();

		}

	}

}
