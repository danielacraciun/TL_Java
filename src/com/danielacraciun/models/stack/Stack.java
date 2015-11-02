package com.danielacraciun.models.stack;

/**
 * Created by dana on 06.10.2015.
 */

public interface Stack {
    void push(Object o);

    Object pop();

    boolean isEmpty();

    Object top();
}
