package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;

/**
 * Created by dana on 11.10.2015.
 */
public class VarExp extends Exp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    public int eval(Dictionary tbl) {
        if (tbl.containsKey(id)) return (int) tbl.get(id);
        return 0;
    }

    public String toString() {
        return id;
    }
}
