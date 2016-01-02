package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		// Create sentinel nodes
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		
		this.head.next = this.tail;
		this.tail.prev = this.head;
		
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		// Instantiate new node
		LLNode<E> newNode = new LLNode<E>(element);
		
		LLNode<E> currentNode = this.tail.prev;
		
		// Re-assign pointers for currentNode and tail to newNode
		newNode.next = this.tail;
		newNode.prev = currentNode;
		this.tail.prev = newNode;
		currentNode.next = newNode;
		
		// Increment size
		this.size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index < 0 || index > size - 1) { throw new IndexOutOfBoundsException(); }
		
		// Start at index 0
		LLNode<E> key = this.head.next;
		
		while (index > 0) { 
			key = key.next; 
			index--;
		}
		
		return key.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		/*if (index < 0 || index > size - 1) { throw new IndexOutOfBoundsException(); }*/
		
		if (element == null) { throw new NullPointerException(); }
		
		LLNode<E> newNode = new LLNode<E>(element);
		
		LLNode<E> currentNode = this.head.next;
		
		while (index > 0 && currentNode.next != null) {
			currentNode = currentNode.next;
			index--;
		}
		
		// Re-assign pointers so currentNode -> newNode ->  newNode.next
		newNode.next = currentNode;
		currentNode.prev.next = newNode;
		newNode.prev = currentNode.prev;
		currentNode.prev = newNode;
		
		// Increment list size
		this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		// Check if out of bounds
		if (index < 0 || index > size - 1) { throw new IndexOutOfBoundsException(); }
		
		LLNode<E> deleteNode = this.head.next;
		
		// Search for index
		while (index > 0) {
			deleteNode = deleteNode.next;
			index--;
		}
		
		E oldData = deleteNode.data;
		
		// Remove index node
		deleteNode.prev.next = deleteNode.next;
		deleteNode.next.prev = deleteNode.prev;
		
		// Decrement list size
		this.size--;
		
		return oldData;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index > size - 1 || index < 0) { throw new IndexOutOfBoundsException(); }
		
		if (this.head.next == null) { throw new NullPointerException(); }

		LLNode<E> currentNode = this.head.next;
		
		while (index > 0 && currentNode.next != null) {
			currentNode = currentNode.next;
			index--;
		}
		
		E oldElement = currentNode.data;
		currentNode.data = element;
		
		return oldElement;

	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
