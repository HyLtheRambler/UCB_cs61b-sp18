public class ArrayDeque<T> {

    /* Invariants: head should be the position of the first element,
    * end be the next position to add the last element
    * when head == end, no space for next element to add and should resize the array,
    * copy elements from end to size of the original array to the end of new array,
    * and the head, depending on whether head
    * */
    private T[] array;
    private int size;
    private int eleSize;
    private int head;
    private int end;

    /* helper function for computing the next value of end after insertion */
    private int next(int ptr) {
        return (ptr + 1) % size;
    }

    /* helper function for computing the prev value of head after insertion */
    private int prev(int ptr) {
        return (ptr - 1) % size;
    }

    /* Resizing array if head == end */
    private void resizeArray() {
        int ptr = head;
        /* Shrink oversized array. eleSize remains the same */
        if (size >= 16 && eleSize < (size / 4)) {
            if (eleSize <= 15) {
                int newSize = 15;
                T[] newArray = (T[]) new Object[newSize];
                for (; ptr != end; ptr = next(ptr)) {
                    newArray[(ptr - head) % 15] = array[ptr];
                }
                array = newArray;
                head = 0;
                size = newSize;
                end = eleSize % 15;
            } else {
                int newSize = size / 2;
                T[] newArray = (T[]) new Object[size / 2];
                for (; ptr != end; ptr = next(ptr)) {
                    newArray[(ptr - head) % newSize] = array[ptr];
                }
                head = 0;
                end = eleSize;
                size = newSize;
                array = newArray;
            }
        } else { /* allocate more space for array */
            int newSize = size * 2;
            T[] newArray = (T[]) new Object[newSize];
            for (; ptr != end; ptr = next(ptr)) {
                newArray[(ptr - head) % newSize] = array[ptr];
            }
            head = 0;
            end = eleSize;
            array = newArray;
            size = newSize;
        }
    }

    /*  Creates an empty linked list deque. */
    public ArrayDeque() {
        size = 15;
        array = (T[]) new Object[size];
        eleSize = 0;
        head = 0;
        end = 1;
    }
    /*  Creates a deep copy of other. */
    /*
        public ArrayDeque(ArrayDeque<T> other) {
        size = other.size;
        eleSize = other.eleSize;
        head = other.head;
        end = other.end;
        int ptr = head;
        array = (T[]) new Object[size];
        for (; ptr != end; ptr = next(ptr)) {
            array[ptr] = other.array[ptr];
        }
    }
     */

    /*  Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (head == end) {
            resizeArray();
        }
        head = prev(head);
        array[head] = item;
        eleSize++;
    }
    /*  Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (head == end) {
            resizeArray();
        }
        array[end] = item;
        end = next(end);
        eleSize++;
    }
    /*  Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return eleSize == 0;
    }
    /*  Returns the number of items in the deque. */
    public int size() {
        return eleSize;
    }
    /*  Prints the items in the deque from first to last,
    separated by a space. Once all the items have been
    printed, print out a new line. */
    public void printDeque() {
        int ptr = head;
        for (; ptr != end; ptr = next(ptr)) {
            System.out.print(array[ptr]);
            System.out.print(' ');
        }
        System.out.print('\n');
    }
    /*  Removes and returns the item at the front of the deque.
     If no such item exists, returns null. */
    public T removeFirst() {
        T elem = array[head];
        head = next(head);
        eleSize--;
        if (size >= 16 && eleSize < (size / 4)) {
            resizeArray();
        }
        return elem;
    }
    /*  Removes and returns the item at the back of the deque.
     If no such item exists, returns null. */
    public T removeLast() {
        T elem = array[prev(end)];
        end = prev(end);
        eleSize--;
        if (size >= 16 && eleSize < (size / 4)) {
            resizeArray();
        }
        return elem;
    }
    /*  Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index < 0 || index > eleSize - 1) {
            return null;
        }
        return array[head + index];
    }

}
