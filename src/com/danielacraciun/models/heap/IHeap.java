package com.danielacraciun.models.heap;

import java.io.Serializable;

public interface IHeap<T> extends Serializable {
    int add(T e);
    T get(int index) throws IndexOutOfBoundsException;
    void update(int index, T value) throws IndexOutOfBoundsException;
}
