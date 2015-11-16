package com.danielacraciun.models.stack;

import java.io.Serializable;

public interface IStack<T> extends Serializable{

    void push(T elem);

    T pop();

    boolean isEmpty();

    T top();

}
