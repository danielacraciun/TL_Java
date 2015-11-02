package com.danielacraciun.models.dictionary;

/**
 * Created by dana on 10.10.2015.
 */
public class ArrayDictionary implements Dictionary {

    private Object[] keys;
    private Object[] values;
    private int nrElements;

    public ArrayDictionary() {
        keys = new Object[20];
        values = new Object[20];
        nrElements = 0;
    }

    private void resize() {
        Object[] tmp1 = new Object[nrElements * 2];
        Object[] tmp2 = new Object[nrElements * 2];
        System.arraycopy(keys, 0, tmp1, 0, nrElements);
        System.arraycopy(values, 0, tmp2, 0, nrElements);
        keys = tmp1;
        values = tmp2;
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
    public boolean containsKey(Object key) {
        for (int i = 0; i < nrElements; i++) {
            if (keys[i].equals(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < nrElements; i++) {
            if (values[i] == value)
                return true;
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        for (int i = 0; i < nrElements; i++) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        if (keys.length == nrElements && values.length == nrElements)
            resize();

        keys[nrElements] = key;
        values[nrElements++] = value;
    }

    public String toString() {
        String s = "Variables:  ";
        for (int i = 0; i < nrElements; i++) {
            s += keys[i].toString() + ": ";
            s += values[i].toString() + ", ";
        }
        return s;
    }
}