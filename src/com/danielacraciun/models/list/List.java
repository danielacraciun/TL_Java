package com.danielacraciun.models.list;

import java.io.Serializable;

public interface List<T> extends Serializable {
    int size();

    boolean isEmpty();

    boolean contains(T o);

    void add(T o);

    T get(int index);
}
