package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.heap.IHeap;

import java.io.Serializable;

public class Exp implements Serializable {

    public int eval(Dictionary<String, Integer> table, IHeap<Integer> heap)
            throws DivisionByZeroException, UninitializedVarException {
        return 0;
    }

    public String toString() {
        return " ";
    }
}
