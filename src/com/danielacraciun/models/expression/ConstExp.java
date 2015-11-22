package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.heap.IHeap;

public class ConstExp extends Exp {
    private Integer nr;

    public ConstExp(Integer nr) {
        this.nr = nr;
    }

    public int eval(Dictionary<String, Integer> tbl, IHeap<Integer> heap) {
        return nr;
    }

    public String toString() {
        return nr.toString();
    }
}
