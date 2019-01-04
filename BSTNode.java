/**
 * BSTNode class
 * 
 * @author Joowon Kim
 */

public class BSTNode<E> {
	private E element;
	private BSTNode<E> left;
	private BSTNode<E> right;
	private BSTNode<E> parent;

	/**
	 * Constructor
	 * 
	 * @param it
	 *            element inserted
	 * @param left
	 * @param right
	 */
	public BSTNode(E it, BSTNode<E> left, BSTNode<E> right) {
		element = it;
		this.left = left;
		this.right = right;
	}

	/**
	 * Constructor
	 * 
	 * @param it
	 *            element inserted
	 */
	public BSTNode(E it) {
		this(it, null, null);
	}

	/**
	 * @return left left node
	 */
	public BSTNode<E> getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            setting left node
	 */
	public void setLeft(BSTNode<E> left) {
		this.left = left;
	}

	/**
	 * @return right right node
	 */
	public BSTNode<E> getRight() {
		return right;
	}

	/**
	 * @param right
	 *            setting right node
	 */
	public void setRight(BSTNode<E> right) {
		this.right = right;
	}

	/**
	 * @return element
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
	 * @return parent node
	 */
	public BSTNode<E> getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            setting parent
	 */
	public void setParent(BSTNode<E> parent) {
		this.parent = parent;
	}

	/**
	 * @return true when it's left node
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
		BSTNode<E> n = this;

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
