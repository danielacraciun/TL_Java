package com.danielacraciun.models.stack;
import java.util.Stack;

public class ArrayStack<T> implements IStack<T> {
    private Stack<T> elements;

    public ArrayStack() {
        elements = new Stack<>();
    }

    @Override
    public void push(T elem) {
        elements.push(elem);
    }

    @Override
    public T pop() {
        return elements.pop();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public T top() {
        return elements.peek();
    }

    public String toString() {
        String s = "Execution Stack:  ";
        Stack<?> copy = (Stack<?>) elements.clone();
        while(!copy.isEmpty()) {
            s += copy.pop().toString();
            s += ";";
        }
        return s;
    }


}