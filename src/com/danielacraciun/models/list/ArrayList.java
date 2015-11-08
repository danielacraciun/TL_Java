package com.danielacraciun.models.list;

public class ArrayList<T> implements List<T> {
    private java.util.ArrayList<T> elements;

    public ArrayList() {
        elements = new java.util.ArrayList<>(20);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.size() == 0;
    }

    @Override
    public boolean contains(T elem) {
        return elements.contains(elem);
    }

    @Override
    public void add(T elem) {
        elements.add(elem);
    }

    @Override
    public T get(int index) {
        return elements.get(index);
    }

    public String toString() {
        String s = "Output:  ";
        for (T element : elements) s += element.toString() + "  ";
        return s;
    }
}
