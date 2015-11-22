package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.heap.IHeap;

import java.util.Scanner;

public class ReadExp extends Exp {

    public int eval(Dictionary<String, Integer> tbl, IHeap<Integer> heap) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Input a number: ");
        return reader.nextInt();
    }

    public String toString() {
        return "read()";
    }
}
