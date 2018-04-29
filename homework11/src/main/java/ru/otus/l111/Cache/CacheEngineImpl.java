package ru.otus.l111.Cache;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.function.Function;

public class CacheEngineImpl<K, V> implements CacheEngine<K, V> {

    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;

    private final Map<K, SoftReference<MyElement<K, V>>> elements = new LinkedHashMap<>();

    private int hit = 0;
    private int miss = 0;

    public CacheEngineImpl(int maxElements) {
        this.maxElements = maxElements;
    }

    @Override
    public void put(MyElement<K, V> element) {
        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
            System.out.println(String.format("Element with id %s was evicted", firstKey));
        }

        K key = element.getKey();
        elements.put(key, new SoftReference<>(element));
    }

    @Override
    public V get(K key) {
        if (elements.size() == 0) {
            miss++;
            System.out.println(String.format("The value with key: %s does not found from cache", key));
            return null;
        }
        SoftReference<MyElement<K, V>> element = elements.get(key);
        if (element != null) {
            MyElement<K, V> myElement = element.get();
            if (elements.containsKey(key)) {
                return myElement.getValue();
            }
        }
        miss++;
        System.out.println(String.format("The value with key: %s does not found from cache", key));
        return null;
    }

    @Override
    public int getHitCount() {
        return hit;
    }

    @Override
    public int getMissCount() {
        return miss;
    }

    private TimerTask getTimerTask(final K key, Function<SoftReference<MyElement<K, V>>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<MyElement<K, V>> element = elements.get(key);
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }

    public void setCacheHit(int hit) {
        this.hit = hit;
    }
}
