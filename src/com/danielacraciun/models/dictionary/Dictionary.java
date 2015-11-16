package com.danielacraciun.models.dictionary;

import java.io.Serializable;

public interface Dictionary<K, V> extends Serializable {

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    V get(K key);

    void put(K key, V value);
}
