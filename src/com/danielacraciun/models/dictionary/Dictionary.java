package com.danielacraciun.models.dictionary;

public interface Dictionary<K, V> {

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    V get(K key);

    void put(K key, V value);
}
