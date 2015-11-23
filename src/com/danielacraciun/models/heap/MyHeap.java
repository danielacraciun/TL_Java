package com.danielacraciun.models.heap;

import java.util.HashMap;

public class MyHeap<T> implements IHeap<T> {

    private HashMap<Integer, T> elements;
    private int crtPos;

    public MyHeap() {
        elements = new HashMap<>();
        crtPos = 1;
    }

    @Override
    public int add(T e) {
        elements.put(crtPos, e);
        return crtPos++;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException{
        if (elements.get(index) == null) {
            throw new IndexOutOfBoundsException();
        }
        return elements.get(index);
    }

    @Override
    public void update(int index, T value) throws IndexOutOfBoundsException {
        if (elements.get(index) == null) {
            throw new IndexOutOfBoundsException();
        }
        elements.put(index, value);
    }

    @Override
    public String toString() {
        String s = "Heap: ";
        for (Integer pos : elements.keySet()) {
            s = pos.toString() + " -> " + elements.get(pos)  + ", " + s;
        }
        return s;
    }
}
