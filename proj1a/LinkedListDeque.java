public class LinkedListDeque<T> {
    private static class ListNode<T> {
        private ListNode<T> prev;
        private T value;
        private ListNode<T> next;
        public ListNode(ListNode<T> p, T val, ListNode<T> n) {
            prev = p;
            next = n;
            value = val;
        }
    }
    private ListNode<T> sentinel;
    private int size;

    /*  Creates ae empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new ListNode<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /*  Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        ListNode<T> toAdd = new ListNode<>(this.sentinel, item, this.sentinel.next);
        this.sentinel.next.prev = toAdd;
        this.sentinel.next = toAdd;
        size++;
    }
    /*  Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        ListNode<T> toAdd = new ListNode<>(sentinel.prev, item, sentinel);
        sentinel.prev.next = toAdd;
        sentinel.prev = toAdd;
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
    /*  Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line. */
    public void printDeque() {
        ListNode<T> ptr = this.sentinel;
        while (ptr.next != sentinel) {
            System.out.print(ptr.next.value);
            System.out.print(" ");
            ptr = ptr.next;
        }
        System.out.print("\n");
    }
    /*  Removes and returns the item at the front of the deque.
    If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T elem = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return elem;
    }
    /*  Removes and returns the item at the back of the deque.
     If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T elem = sentinel.prev.value;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return elem;
    }
    /*  Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        ListNode<T> ptr = this.sentinel.next;
        int curI = 0;
        if (index < 0 || index > size - 1) {
            return null;
        }
        while (ptr != sentinel && curI < index) {
            curI++;
            ptr = ptr.next;
        }
        if (curI != index) {
            return null;
        }
        return ptr.value;
    }
    
    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        ListNode<T> curPtr = sentinel.next;
        return recursionHelper(index, curPtr);
    }

    /* Helper function for getRecursive() */
    private T recursionHelper(int index, ListNode<T> curPtr) {
        if (curPtr == sentinel) {
            return null;
        }
        if (index == 0) {
            return curPtr.value;
        }
        index--;
        return recursionHelper(index, curPtr.next);
    }
}

