package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;

public class VarExp extends Exp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    public int eval(Dictionary<String, Integer> tbl) {
        if (tbl.containsKey(id)) return tbl.get(id);
        return 0;
    }

    public String toString() {
        return id;
    }
}
