import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    private class MyNode {
        T data;
        MyNode next;
        MyNode prev;

        MyNode(T data) {
            this.data = data;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private MyNode getNode(int index) {
        checkIndex(index);
        MyNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }
        return current;
    }

    public void add(T item) {
        addLast(item);
    }

    public void set(int index, T item) {
        getNode(index).data = item;
    }

    public void add(int index, T item) {
        checkIndexForAdd(index);

        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size) {
            addLast(item);
            return;
        }

        MyNode current = getNode(index);
        MyNode newNode = new MyNode(item);

        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;

        size++;
    }

    public void addFirst(T item) {
        MyNode newNode = new MyNode(item);
        if (size == 0) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T item) {
        MyNode newNode = new MyNode(item);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public T getFirst() {
        if (size == 0) throw new RuntimeException("List is empty");
        return head.data;
    }

    public T getLast() {
        if (size == 0) throw new RuntimeException("List is empty");
        return tail.data;
    }

    public void remove(int index) {
        checkIndex(index);

        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }

        MyNode current = getNode(index);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    public void removeFirst() {
        if (size == 0) throw new RuntimeException("List is empty");

        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public void removeLast() {
        if (size == 0) throw new RuntimeException("List is empty");

        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public void sort() {
        if (size < 2) return;

        for (int i = 0; i < size - 1; i++) {
            MyNode current = head;
            while (current != null && current.next != null) {
                if (current.data.compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
    }

    public int indexOf(Object object) {
        MyNode current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(object)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        MyNode current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.data.equals(object)) return index;
            current = current.prev;
            index--;
        }
        return -1;
    }

    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        MyNode current = head;
        int i = 0;
        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }
        return arr;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            MyNode current = head;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                T value = current.data;
                current = current.next;
                return value;
            }
        };
    }
}