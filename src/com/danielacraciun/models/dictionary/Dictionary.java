package com.danielacraciun.models.dictionary;

/**
 * Created by dana on 10.10.2015.
 */

public interface Dictionary {

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    Object get(Object key);

    void put(Object key, Object value);
}
