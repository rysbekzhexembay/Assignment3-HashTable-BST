public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11; // Размер по умолчанию
    private int size;

    public MyHashTable() {
        this.chainArray = (HashNode<K, V>[]) new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        this.chainArray = (HashNode<K, V>[]) new HashNode[M];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        // Проверка, существует ли ключ
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Вставка в начало цепочки
        size++;
        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) break;
            prev = head;
            head = head.next;
        }

        if (head == null) return null;

        size--;
        if (prev != null) prev.next = head.next;
        else chainArray[index] = head.next;

        return head.value;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) return true;
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) return node.key;
                node = node.next;
            }
        }
        return null;
    }

    // Для тестирования распределения в Part 1.2
    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> temp = chainArray[i];
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}