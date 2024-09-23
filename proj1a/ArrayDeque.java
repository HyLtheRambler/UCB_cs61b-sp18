public class ArrayDeque<T> {

    /* Invariants: head should be the position of the first element,
    * end be the next position to add the last element
    * when head == end, no space for next element to add and should resize the array,
    * copy elements from end to size of the original array to the end of new array,
    * and the head, depending on whether head
    * */
    T[] array;
    private int size;
    private int ele_size;
    private int head;
    private int end;

    /* helper function for computing the next value of end after insertion */
    private int next(int end) {
        return (end + 1) % size;
    }

    /* helper function for computing the prev value of head after insertion */
    private int prev(int head) {
        return (head - 1) % size;
    }

    /* Resizing array if head == end */
    private void resizeArray() {
        int ptr = head;
        /* Shrink oversized array. ele_size remains the same */
        if (size >= 16 && ele_size < (size / 4)) {
            if (ele_size <= 15) {
                int new_size = 15;
                T[] new_array = (T[]) new Object[new_size];
                for (; ptr != end; ptr = next(ptr)) {
                    new_array[(ptr - head) % 15] = array[ptr];
                }
                array = new_array;
                head = 0;
                size = new_size;
                end = ele_size % 15;
            } else {
                int new_size = size / 2;
                T[] new_array = (T[]) new Object[size / 2];
                for (; ptr != end; ptr = next(ptr)) {
                    new_array[(ptr - head) % new_size] = array[ptr];
                }
                head = 0;
                end = ele_size;
                size = new_size;
                array = new_array;
            }
        } else { /* allocate more space for array */
            int new_size = size * 2;
            T[] new_array = (T[]) new Object[new_size];
            for (; ptr != end; ptr = next(ptr)) {
                new_array[(ptr - head) % new_size] = array[ptr];
            }
            head = 0;
            end = ele_size;
            array = new_array;
            size = new_size;
        }
    }

    /*  Creates an empty linked list deque. */
    public ArrayDeque() {
        size = 15;
        array = (T[]) new Object[size];
        ele_size = 0;
        head = 0;
        end = 1;
    }
    /*  Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque<T> other) {
        size = other.size;
        ele_size = other.ele_size;
        head = other.head;
        end = other.end;
        int ptr = head;
        array = (T[]) new Object[size];
        for (; ptr != end; ptr = next(ptr)) {
            array[ptr] = other.array[ptr];
        }
    }

    /*  Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (head == end) {
            resizeArray();
        }
        head = prev(head);
        array[head] = item;
        ele_size++;
    }
    /*  Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (head == end) {
            resizeArray();
        }
        array[end] = item;
        end = next(end);
        ele_size++;
    }
    /*  Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return ele_size == 0;
    }
    /*  Returns the number of items in the deque. */
    public int size() {
        return ele_size;
    }
    /*  Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line. */
    public void printDeque() {
        int ptr = head;
        for (; ptr != end; ptr = next(ptr)) {
            System.out.print(array[ptr]);
            System.out.print(' ');
        }
        System.out.print('\n');
    }
    /*  Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        T elem = array[head];
        head = next(head);
        ele_size--;
        if (size >= 16 && ele_size < (size / 4)) {
            resizeArray();
        }
        return elem;
    }
    /*  Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        T elem = array[prev(end)];
        end = prev(end);
        ele_size--;
        if (size >= 16 && ele_size < (size / 4)) {
            resizeArray();
        }
        return elem;
    }
    /*  Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index < 0 || index > ele_size - 1) {
            return null;
        }
        return array[head + index];
    }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index > ele_size - 1) {
            return null;
        }
        return array[head + index];
    }

}