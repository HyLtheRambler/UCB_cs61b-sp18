public class LinkedListDeque<T> {
	private static class ListNode<T> {
		public ListNode<T> prev;
		public T value;
		public ListNode<T> next;
		public ListNode(ListNode<T> p, T val, ListNode<T> n) {
			prev = p;
			next = n;
			value = val;
		}
	}
	private ListNode<T> sentinel;
	public int size;

	/*  Creates an empty linked list deque. */
    public LinkedListDeque() {
		sentinel = new ListNode<>(null, null, null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
		size = 0;
    }
	/*  Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque<T> other) {
		ListNode<T> other_ptr = other.sentinel;
		this.sentinel = new ListNode<>(null, null, null);
		ListNode<T> this_ptr = sentinel;
		this.sentinel.prev = sentinel;
		this.sentinel.next = sentinel;
		while (other_ptr.next != other.sentinel) {
			this_ptr.next = new ListNode<>(this_ptr, other_ptr.next.value, null);
			this_ptr = this_ptr.next;
			other_ptr = other_ptr.next;
		}
		this.size = other.size;
		this_ptr.next = sentinel;
    }

	/*  Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
		ListNode<T> to_add = new ListNode<>(this.sentinel, item, this.sentinel.next);
		this.sentinel.next.prev = to_add;
		this.sentinel.next = to_add;
		size++;
    }
	/*  Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
		ListNode<T> to_add = new ListNode<>(sentinel.prev, item, sentinel);
		sentinel.prev.next = to_add;
		sentinel.prev = to_add;
		size++;
    }
	/*  Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }
	/*  Returns the number of items in the deque. */
    public int size() {
	return size;
    }
	/*  Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line. */
    public void printDeque() {
	ListNode<T> ptr = this.sentinel;
	while (ptr.next != sentinel) {
	    System.out.print(ptr.next.value);
	    System.out.print(" ");
	    ptr = ptr.next;
	}
	System.out.print("\n");
    }
	/*  Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
		T elem = sentinel.next.value;
		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size--;
		return elem;
    }
	/*  Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
		T elem = sentinel.prev.value;
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size--;
		return elem;
    }
	/*  Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
		ListNode<T> ptr = this.sentinel.next;
		int cur_i = 0;
		if (index < 0 || index > size - 1) {
			return null;
		}
		while (ptr != sentinel && cur_i < index) {
			cur_i++;
			ptr = ptr.next;
		}
		if (cur_i != index) {
			return null;
		}
		return ptr.value;
    }
    
	/* Same as get, but uses recursion. */
    public T getRecursive(int index) {
	ListNode<T> cur_ptr = sentinel.next;
	return recursionHelper(index, cur_ptr);
    }

	/* Helper function for getRecursive() */
    private T recursionHelper(int index, ListNode<T> cur_ptr) {
	if (cur_ptr == sentinel) {
	    return null;
	}
	index--;
	return recursionHelper(index, cur_ptr.next);
    }
}

