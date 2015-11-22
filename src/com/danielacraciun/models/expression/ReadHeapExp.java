package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.heap.IHeap;

public class ReadHeapExp extends Exp{
    private String id;

    public ReadHeapExp(String id) {
        this.id = id;
    }

    @Override
    public int eval(Dictionary<String, Integer> tbl, IHeap<Integer> heap)
            throws UninitializedVarException, DivisionByZeroException, IndexOutOfBoundsException {
        Integer address = tbl.get(id);
        return heap.get(address);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "readHeap( " + id + ")";
    }
}
