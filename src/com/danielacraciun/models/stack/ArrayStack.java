package com.danielacraciun.models.stack;

/**
 * Created by dana on 06.10.2015.
 */
public class ArrayStack implements Stack{
    private Object[] elements;
    private int nrElements;

    public ArrayStack() {
        nrElements = 0;
        elements = new Object[20];
    }

    private void resize() {
        Object[] tmp = new Object[nrElements * 2];
        System.arraycopy(elements, 0, tmp, 0, nrElements);
        elements = tmp;
    }

    @Override
    public void push(Object o) {
        if (elements.length == nrElements)
            resize();
        elements[nrElements++] = o;
    }

    @Override
    public Object pop() {
        if (nrElements > 0)
            return elements[--nrElements];
        return null;
    }

    @Override
    public boolean isEmpty() {
        return nrElements == 0;
    }

    @Override
    public Object top() {
        if (nrElements > 0)
            return elements[nrElements - 1];
        return null;
    }

    public String toString() {
        String s = "Execution Stack:  ";
        for (int i = nrElements - 1; i >= 0; i--)
            s += elements[i].toString() + ", ";
        return s.substring(0, s.length() - 2);
    }
}
