package com.danielacraciun.models.list;

public interface List<T> {
    int size();

    boolean isEmpty();

    boolean contains(T o);

    void add(T o);

    T get(int index);
}
