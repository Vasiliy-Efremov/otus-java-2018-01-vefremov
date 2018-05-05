package ru.otus.l121.Cache;

public class MyElement<K, V> {
    private final K key;
    private final V value;

    public MyElement(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
