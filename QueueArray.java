/**
 * QueueArray class
 * 
 * @author Joowon Kim 
 */

public class QueueArray<E> implements Queue<E> {

	private int capacity = 10;
	private E[] queue = (E[]) new Object[capacity];
	private int front = 0;
	private int rear = capacity - 1;
	private int size = 0;

	public QueueArray(E it) {
		size++;
		rear = (rear + 1) % capacity;
		queue[rear] = it;
	}

	public QueueArray() {

	}

	public boolean isEmpty() {

		return size == 0;
	}

	public E front() {

		return queue[front];
	}

	public E dequeue() {

		if (size == 0) {
			return null;
		}

		E result = queue[front];
		front = (front + 1) % capacity;
		size--;
		return result;

	}

	public void enqueue(E it) {
		if (front == capacity - 1) {
			reallocate();
		}

		rear = (rear + 1) % capacity;
		queue[rear] = it;
		size++;
	}

	public void clear() {

		capacity = 10;
		queue = (E[]) new Object[capacity];
		front = 0;
		rear = capacity - 1;
		size = 0;
	}

	public int size() {

		return size;
	}

	private void reallocate() {

		int newCapacity = 2 * capacity;
		E[] newData = (E[]) new Object[newCapacity];
		int j = front;
		for (int i = 0; i < size; i++) {
			newData[i] = queue[j];
			j = (j + 1) % capacity;
		}
		front = 0;
		rear = size - 1;
		capacity = newCapacity;
		queue = newData;

	}
}
