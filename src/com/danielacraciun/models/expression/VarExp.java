package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.heap.IHeap;

public class VarExp extends Exp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    public int eval(Dictionary<String, Integer> tbl, IHeap<Integer> heap) throws UninitializedVarException {
        if (tbl.containsKey(id)) {
            return tbl.get(id);
        } else {
            throw new UninitializedVarException();
        }
    }

    public String toString() {
        return id;
    }
}
