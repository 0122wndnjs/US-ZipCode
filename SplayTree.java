
/**
 * SplayTree Implementation  
 *
 * @author Joowon Kim
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SplayTree<E extends Comparable<E>> {

	private SplayNode<E> root;
	private int size;
	private int height;
	private int compCount;

	/**
	 * Constructor
	 * 
	 */
	public SplayTree() {

		root = null;
		size = 0;
		compCount = 0;
	}

	/**
	 * Constructor
	 * 
	 * @param node
	 *            node inserted
	 */
	public SplayTree(SplayNode<E> node) {

		root = node;
		root.setParent(null);
		size = 1;
	}

	/**
	 * @return root node
	 */
	public SplayNode<E> getRoot() {

		return root;
	}

	/**
	 * clear
	 */
	public void clear() {

		root = null;
		size = 0;
	}

	/**
	 * @return size
	 */
	public int getSize() {

		return size;
	}

	/**
	 * @return compCount
	 */
	public int getcompCount() {
		return compCount;
	}

	/**
	 * Search
	 * 
	 * @param it
	 *            element searched
	 * @return node
	 */
	public SplayNode<E> search(E it) {

		compCount = 0;
		SplayNode<E> node = root;

		while (node != null) {

			compCount++;
			int res = it.compareTo(node.getElement());

			if (res < 0) {
				node = node.getLeft();
			} else if (res > 0) {

				node = node.getRight();
			} else {

				return node;
			}
		}
		return null;

	}

	/**
	 * @return height
	 */
	public int getHeight() {

		int height = -1;
		Queue<SplayNode> q = new QueueArray<SplayNode>();
		if (root == null) {
			return height;
		}
		q.enqueue(root);
		while (!q.isEmpty()) {
			int nodeCount = q.size();
			height++;
			while (nodeCount > 0) {
				SplayNode<E> node = q.dequeue();
				if (node.hasLeft()) {
					q.enqueue(node.getLeft());
				}
				if (node.hasRight()) {
					q.enqueue(node.getRight());
				}
				nodeCount--;
			}
		}
		return height;
	}

	/**
	 * @param it
	 *            element searched
	 * @return true when found
	 */
	public boolean contains(E it) {
		SplayNode<E> node = root;
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
	 * @param it
	 *            element searched
	 * @return true when found
	 */
	public void insert(E it) {

		if (root == null) {
			root = new SplayNode<E>(it);
			root.setParent(null);
			size++;
		} else {
			SplayNode<E> point = root;

			while (true) {
				if (point.getElement().compareTo(it) == 0)
					return;
				else if (point.getElement().compareTo(it) > 0) {
					if (!point.hasLeft()) {
						point.setLeft(new SplayNode<E>(it));
						point.getLeft().setParent(point);
						size++;

						Splay(point.getLeft());
						break;
					} else {
						point = point.getLeft();
						continue;
					}
				} else {
					if (!point.hasRight()) {
						point.setRight(new SplayNode<E>(it));
						point.getRight().setParent(point);
						size++;

						Splay(point.getRight());
						break;
					} else {
						point = point.getRight();
						continue;
					}
				}
			}
		}
	}

	/**
	 * @param it
	 *            element removed
	 */
	public void remove(E it) {

		SplayNode<E> parent = null;
		SplayNode<E> child = null;
		SplayNode<E> node = root;

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

			System.out.println("Failed to find: " + it + "to remove");
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
	 * @param node1
	 * @param node2
	 */
	private void swapElements(SplayNode<E> node1, SplayNode<E> node2) {

		E element1 = node1.getElement();
		E element2 = node2.getElement();

		node1.setElement(element2);
		node2.setElement(element1);
	}

	/**
	 * printLevelOrder
	 */
	public void printLevelOrder() {

		Queue<SplayNode> Q = new QueueArray<SplayNode>();

		int count = 0;

		if (root == null) {

			return;
		}

		Q.enqueue(root);

		while (!Q.isEmpty()) {

			count = Q.size();

			while (count > 0) {

				SplayNode n = Q.dequeue();

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
	 * printByDepth
	 */
	public void printByDepth() {

		Stack<SplayNode> S = new Stack<SplayNode>();
		S.push(root);

		while (!S.isEmpty()) {
			SplayNode<E> n = S.pop();
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
	 * printInOrder
	 */
	public void printInOrder() {
		Stack<SplayNode> S = new Stack<SplayNode>();

		SplayNode node = root;
		pushLeftNodesToStack(S, node);
		while (!S.isEmpty()) {
			SplayNode<E> n = S.pop();
			System.out.println(n.getElement());
			n = n.getRight();
			pushLeftNodesToStack(S, n);
		}
	}

	/**
	 * @param s
	 * @param b
	 */
	public void pushLeftNodesToStack(Stack<SplayNode> s, SplayNode<E> b) {
		while (b != null) {
			s.push(b);
			b = b.getLeft();

		}

	}

	/**
	 * @param s
	 *            node root
	 */
	private void Splay(SplayNode<E> s) {
		while (s.getParent() != null) {
			SplayNode<E> Parent = s.getParent();
			SplayNode<E> GrandParent = Parent.getParent();

			if (GrandParent == null) {
				if (s == Parent.getLeft())
					makeLeftChildParent(s, Parent);
				else
					makeRightChildParent(s, Parent);
			} else {
				if (s == Parent.getLeft()) {

					if (Parent == GrandParent.getLeft()) {
						makeLeftChildParent(Parent, GrandParent);
						makeLeftChildParent(s, Parent);

					} else {
						makeLeftChildParent(s, Parent);
						makeRightChildParent(s, s.getParent());
					}
				} else {
					if (Parent == GrandParent.getLeft()) {
						makeRightChildParent(s, Parent);
						makeLeftChildParent(s, s.getParent());
					} else {
						makeRightChildParent(Parent, GrandParent);
						makeRightChildParent(s, Parent);
					}
				}
			}
		}
		root = s;
	}

	/**
	 * @param child
	 * @param parent
	 */
	public void makeLeftChildParent(SplayNode<E> child, SplayNode<E> parent) {
		if (parent.getParent() != null) {
			if (parent == parent.getParent().getLeft()) {
				parent.getParent().setLeft(child);
			} else {
				parent.getParent().setRight(child);
			}
		}
		if (child.getRight() != null) {
			child.getRight().setParent(parent);
		}
		child.setParent(parent.getParent());
		parent.setParent(child);
		parent.setLeft(child.getRight());
		child.setRight(parent);
	}

	/**
	 * @param child
	 * @param parent
	 */
	public void makeRightChildParent(SplayNode<E> child, SplayNode<E> parent) {
		if (parent.getParent() != null) {
			if (parent == parent.getParent().getRight()) {
				parent.getParent().setRight(child);
			} else {
				parent.getParent().setLeft(child);
			}
		}
		if (child.getLeft() != null) {
			child.getLeft().setParent(parent);
		}
		child.setParent(parent.getParent());
		parent.setParent(child);
		parent.setRight(child.getLeft());
		child.setLeft(parent);
	}

}
