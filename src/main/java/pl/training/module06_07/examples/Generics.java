package pl.training.module06_07.examples;


import java.util.ArrayList;
import java.util.List;

public class Generics {

    public static void main(String[] args) {
        /*List list = new ArrayList();
        list.add("Hello");
        list.add(10); // No type checking at compile time!

        String str = (String) list.get(0); // Works fine
        String num = (String) list.get(1); // ClassCastException at runtime!*/

        /*List<String> list = new ArrayList<>();
        list.add("Hello");
        // list.add(10); // Compile-time error!

        String str = list.get(0); // No need for explicit casting
        System.out.println(str);*/

        Wrapper<Integer> intBox = new Wrapper<>();
        intBox.set(100);
        System.out.println(intBox.get());

        var strBox = new Wrapper<String>();
        strBox.set("Hello Generics");
        System.out.println(strBox.get());

        print(30);
        print(true);
        print("text");

        var intResult = square(2);
        var result = square(3.2);

        var pair = new Pair<>("Age", 30);
        System.out.println(pair.getKey() + ": " + pair.getValue());
    }

    public static <T> void print(T item) {
        System.out.println(item);
    }

    public static <T extends Number> double square(T num) {
        return num.doubleValue() * num.doubleValue();
    }

}

class Wrapper<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
}
