package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;

/**
 * Created by dana on 11.10.2015.
 */
public class ConstExp extends Exp {
    private Integer nr;

    public ConstExp(Integer nr) {
        this.nr = nr;
    }

    public int eval(Dictionary tbl) {
        return nr;
    }

    public String toString() {
        return nr.toString();
    }
}
