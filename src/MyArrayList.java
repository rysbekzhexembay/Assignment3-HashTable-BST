import java.util.Iterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    private Object[] arr;
    private int size;

    public MyArrayList() {
        arr = new Object[10];
        size = 0;
    }

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

    private void ensureCapacity() {
        if (size == arr.length) {
            Object[] newArr = new Object[arr.length * 2];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }

    public void add(T item) {
        ensureCapacity();
        arr[size] = item;
        size++;
    }

    public void set(int index, T item) {
        checkIndex(index);
        arr[index] = item;
    }

    public void add(int index, T item) {
        checkIndexForAdd(index);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = item;
        size++;
    }

    public void addFirst(T item) {
        add(0, item);
    }

    public void addLast(T item) {
        add(item);
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) arr[index];
    }

    @SuppressWarnings("unchecked")
    public T getFirst() {
        if (size == 0) throw new RuntimeException("List is empty");
        return (T) arr[0];
    }

    @SuppressWarnings("unchecked")
    public T getLast() {
        if (size == 0) throw new RuntimeException("List is empty");
        return (T) arr[size - 1];
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--;
    }

    public void removeFirst() {
        if (size == 0) throw new RuntimeException("List is empty");
        remove(0);
    }

    public void removeLast() {
        if (size == 0) throw new RuntimeException("List is empty");
        arr[size - 1] = null;
        size--;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                T a = (T) arr[j];
                T b = (T) arr[j + 1];
                if (a.compareTo(b) > 0) {
                    Object temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(object)) return i;
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(object)) return i;
        }
        return -1;
    }

    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    public Object[] toArray() {
        Object[] res = new Object[size];
        for (int i = 0; i < size; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public void clear() {
        arr = new Object[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int current = 0;

            public boolean hasNext() {
                return current < size;
            }

            @SuppressWarnings("unchecked")
            public T next() {
                return (T) arr[current++];
            }
        };
    }
}