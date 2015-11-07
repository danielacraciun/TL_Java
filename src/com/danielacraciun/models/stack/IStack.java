package com.danielacraciun.models.stack;

public interface IStack<T> {

    void push(T elem);

    T pop();

    boolean isEmpty();

    T top();

}
