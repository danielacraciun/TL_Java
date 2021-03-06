package com.danielacraciun.models.dictionary;

import java.util.TreeMap;

public class ArrayDictionary<K, V> implements Dictionary<K, V> {

    private TreeMap<K, V> elements;

    public ArrayDictionary() {
        elements = new TreeMap<>();
    }

    public ArrayDictionary(ArrayDictionary<K, V> table) {
        elements = (TreeMap<K, V>) table.elements.clone();
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

    public String toString() {
        String s = "Variables:  ";
        for(K key : elements.keySet()) {
            V value = elements.get(key);
            s += key.toString() + ": ";
            s += value.toString() + ", ";
        }
        return s;
    }
}