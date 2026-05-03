import java.util.Random;

class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + id;
        if (name != null) {
            for (int i = 0; i < name.length(); i++) {
                hash = 31 * hash + name.charAt(i);
            }
        }
        return hash;
    }
}

// Пример запуска
// MyHashTable<MyTestingClass, String> table = new MyHashTable<>(100);
// Random rand = new Random();
// for(int i=0; i<10000; i++) table.put(new MyTestingClass(rand.nextInt(100000), "Name"+i), "Value"+i);
// table.printBucketSizes();