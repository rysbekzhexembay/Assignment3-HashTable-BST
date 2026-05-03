public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap = new MyArrayList<T>();

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void heapifyUp(int index) {
        while (index > 0 && heap.get(index).compareTo(heap.get(parent(index))) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int smallest = index;
            int leftChild = left(index);
            int rightChild = right(index);

            if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
                smallest = leftChild;
            }

            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    public void add(T item) {
        heap.addLast(item);
        heapifyUp(heap.size() - 1);
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        return heap.get(0);
    }

    public T removeMin() {
        if (isEmpty()) throw new RuntimeException("Heap is empty");

        T min = heap.get(0);

        if (heap.size() == 1) {
            heap.removeLast();
            return min;
        }

        heap.set(0, heap.getLast());
        heap.removeLast();
        heapifyDown(0);

        return min;
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

    public int size() {
        return heap.size();
    }
}