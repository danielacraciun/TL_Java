package com.danielacraciun.models.list;

/**
 * Created by dana on 09.10.2015.
 */
public class ArrayList implements List {
    private Object[] elements;
    private int nrElements;

    public ArrayList() {
        elements = new Object[20];
        nrElements = 0;
    }

    private void resize() {
        Object[] tmp = new Object[nrElements * 2];
        System.arraycopy(elements, 0, tmp, 0, nrElements);
        elements = tmp;
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
    public boolean contains(Object o) {
        for (int i = 0; i < nrElements; i++)
            if (elements[i] == o)
                return true;
        return false;
    }

    @Override
    public void add(Object o) {
        if (elements.length == nrElements)
            resize();
        elements[nrElements++] = o;
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    public String toString() {
        String s = "Output:  ";
        for (int i = 0; i < nrElements; i++)
            s += elements[i].toString() + ", ";
        return s.substring(0, s.length() - 2);
    }
}
