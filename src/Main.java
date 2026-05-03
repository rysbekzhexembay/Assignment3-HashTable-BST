public class Main {
  public static void main(String[] args) {
    MyArrayList<Integer> arr = new MyArrayList<Integer>();
    arr.add(5);
    arr.add(2);
    arr.add(8);
    arr.addFirst(1);
    arr.addLast(10);
    arr.add(2, 7);
    arr.set(0, 100);

    System.out.println("MyArrayList:");
    print(arr);
    System.out.println("First: " + arr.getFirst());
    System.out.println("Last: " + arr.getLast());
    System.out.println("Index of 7: " + arr.indexOf(7));
    System.out.println("Exists 8: " + arr.exists(8));
    arr.remove(2);
    arr.removeFirst();
    arr.removeLast();
    arr.sort();
    print(arr);

    MyLinkedList<Integer> list = new MyLinkedList<Integer>();
    list.add(9);
    list.add(3);
    list.add(6);
    list.addFirst(1);
    list.addLast(12);
    list.add(2, 7);
    list.set(0, 50);

    System.out.println("\nMyLinkedList:");
    print(list);
    System.out.println("First: " + list.getFirst());
    System.out.println("Last: " + list.getLast());
    System.out.println("Index of 7: " + list.indexOf(7));
    System.out.println("Exists 6: " + list.exists(6));
    list.remove(2);
    list.removeFirst();
    list.removeLast();
    list.sort();
    print(list);

    MyStack<Integer> stack = new MyStack<Integer>();
    stack.push(10);
    stack.push(20);
    stack.push(30);
    System.out.println("\nMyStack:");
    System.out.println(stack.peek());
    System.out.println(stack.pop());
    System.out.println(stack.pop());

    MyQueue<Integer> queue = new MyQueue<Integer>();
    queue.enqueue(11);
    queue.enqueue(22);
    queue.enqueue(33);
    System.out.println("\nMyQueue:");
    System.out.println(queue.peek());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());

    MyMinHeap<Integer> heap = new MyMinHeap<Integer>();
    heap.add(9);
    heap.add(4);
    heap.add(7);
    heap.add(1);
    heap.add(3);
    System.out.println("\nMyMinHeap:");
    System.out.println(heap.peek());
    System.out.println(heap.removeMin());
    System.out.println(heap.removeMin());
    System.out.println(heap.removeMin());
  }

  public static <T extends Comparable<T>> void print(MyList<T> list) {
    for (T item : list) {
      System.out.print(item + " ");
    }
    System.out.println();
  }
}