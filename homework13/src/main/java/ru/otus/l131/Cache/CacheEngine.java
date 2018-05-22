package ru.otus.l131.Cache;

public interface CacheEngine<K, V> {
    void put(MyElement<K, V> element);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void setCacheHit(int hit);

    int getValueMaxSize();
}
