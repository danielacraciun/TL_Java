package com.danielacraciun.models.list;

import java.util.Objects;

public class ArrayList implements List<Integer> {
    private Integer[] elements;
    private int nrElements;

    public ArrayList() {
        elements = new Integer[20];
        nrElements = 0;
    }

    @Override
    public int size() {
        return nrElements;
    }

    @Override
    public boolean isEmpty() {
        return nrElements == 0;
    }

    @Override
    public boolean contains(Integer elem) {
        for (int i = 0; i < nrElements; i++)
            if (Objects.equals(elements[i], elem))
                return true;
        return false;
    }

    @Override
    public void add(Integer elem) {
        elements[nrElements++] = elem;
    }

    @Override
    public Integer get(int index) {
        return elements[index];
    }

    public String toString() {
        String s = "Output:  ";
        for (int i = 0; i < nrElements; i++)
            s += elements[i].toString() + ", ";
        return s.substring(0, s.length() - 2);
    }
}
