package com.danielacraciun.models.list;

/**
 * Created by dana on 09.10.2015.
 */
public interface List {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    void add(Object o);

    Object get(int index);
}
