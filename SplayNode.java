/**
 * Splay Node Class
 * 
 * @author Joowon Kim
 */

public class SplayNode<E> {
	private E element;
	private SplayNode<E> left;
	private SplayNode<E> right;
	private SplayNode<E> parent;

	/**
	 * Constructor
	 * 
	 * @param it
	 *            element
	 * @param left
	 * @param right
	 * @param parent
	 */
	public SplayNode(E it, SplayNode<E> left, SplayNode<E> right, SplayNode<E> parent) {
		element = it;
		this.left = left;
		this.right = right;
		this.parent = null;
	}

	/**
	 * Constructor
	 * 
	 * @param it
	 *            element
	 */
	public SplayNode(E it) {
		this(it, null, null, null);
	}

	/**
	 * @return left node
	 */
	public SplayNode<E> getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            setting left node
	 */
	public void setLeft(SplayNode<E> left) {
		this.left = left;
	}

	/**
	 * @return right node
	 */
	public SplayNode<E> getRight() {
		return right;
	}

	/**
	 * @param right
	 *            setting right node
	 */
	public void setRight(SplayNode<E> right) {
		this.right = right;
	}

	/**
	 * @return get element
	 */
	public E getElement() {
		return element;
	}

	/**
	 * @param it
	 *            setting element
	 */
	public void setElement(E it) {
		element = it;
	}

	/**
	 * @return get parent node
	 */
	public SplayNode<E> getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            setting parent node
	 */
	public void setParent(SplayNode<E> parent) {
		this.parent = parent;
	}

	/**
	 * @return true when leaf node
	 */
	public boolean isLeaf() {
		return left == null && right == null;
	}

	/**
	 * @return true when has left node
	 */
	public boolean hasLeft() {
		return left != null;
	}

	/**
	 * @return true when has right node
	 */
	public boolean hasRight() {
		return right != null;
	}

	/**
	 * @return true when has parent
	 */
	public boolean hasParent() {
		return parent != null;
	}

	/**
	 * @return number of children
	 */
	public int numChildren() {
		if (isLeaf())
			return 0;
		else if ((hasLeft() && !hasRight()) || (!hasLeft() && hasRight()))
			return 1;
		else
			return 2;
	}

	/**
	 * @return number of parents
	 */
	public int numParents() {
		int num = 0;
		SplayNode<E> n = this;

		while (n.hasParent()) {
			n = n.getParent();
			num++;
		}
		return num;
	}

	/**
	 * Overrides toString method
	 * 
	 * @see java.lang.Object.toString()
	 * @return converts the element to string and returns
	 */
	public String toString() {
		return element.toString();
	}
}
