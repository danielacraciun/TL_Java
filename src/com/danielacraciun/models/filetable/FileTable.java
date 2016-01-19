package com.danielacraciun.models.filetable;

import com.danielacraciun.models.dictionary.Dictionary;

import java.util.TreeMap;

public class FileTable<K, V> implements Dictionary<K, V> {

    private TreeMap<K, V> elements;

    public FileTable() {
        elements = new TreeMap<>();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return elements.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return elements.containsValue(value);
    }

    @Override
    public V get(K key) {
        return elements.get(key);
    }

    @Override
    public void put(K key, V value) {
        elements.put(key, value);
    }

    @Override
    public String toString() {
        String s = "Files:  ";
        for (K key : elements.keySet()) {
            V value = elements.get(key);
            s += key.toString() + " ";
            if (value != null) {
                s += "(" + value.toString() + "), ";
            } else {
                s += "(null)";
            }
        }
        return s;
    }
}
