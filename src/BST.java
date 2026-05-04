import java.util.Stack;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node> {
    private Node root;
    private int size = 0;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() { return key; }
        public V getValue() { return val; }
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }

        Node curr = root;
        Node parent = null;
        while (curr != null) {
            parent = curr;
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else {
                curr.val = val; 
                return;
            }
        }

        if (key.compareTo(parent.key) < 0) parent.left = new Node(key, val);
        else parent.right = new Node(key, val);
        size++;
    }

    public V get(K key) {
        Node curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else return curr.val;
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node curr = root;

        while (curr != null && !curr.key.equals(key)) {
            parent = curr;
            if (key.compareTo(curr.key) < 0) curr = curr.left;
            else curr = curr.right;
        }

        if (curr == null) return;

        if (curr.left == null || curr.right == null) {
            Node newNode = (curr.left == null) ? curr.right : curr.left;
            if (parent == null) root = newNode;
            else if (curr == parent.left) parent.left = newNode;
            else parent.right = newNode;
            size--;
        }
        else {
            Node successorParent = curr;
            Node successor = curr.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            curr.key = successor.key;
            curr.val = successor.val;

            if (successorParent.left == successor) successorParent.left = successor.right;
            else successorParent.right = successor.right;
            size--;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Node> iterator() {
        return new InOrderIterator(root);
    }

    private class InOrderIterator implements Iterator<Node> {
        private Stack<Node> stack = new Stack<>();

        public InOrderIterator(Node root) {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            Node node = stack.pop();
            if (node.right != null) pushLeft(node.right);
            return node;
        }
    }
}
