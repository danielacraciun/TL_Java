package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.heap.IHeap;

public class CompExp extends Exp {
    private Exp e1;
    private Exp e2;
    private String cmp;

    public CompExp(Exp e1, Exp e2, String cmp) {
        this.e1 = e1;
        this.e2 = e2;
        this.cmp = cmp;
    }

    @Override
    public int eval(Dictionary<String, Integer> tbl, IHeap<Integer> heap) throws DivisionByZeroException, UninitializedVarException {
        switch (cmp) {
            case "<":
                if (e1.eval(tbl, heap) < e2.eval(tbl, heap))
                    return 1;
                else
                    return 0;
            case "<=":
                if (e1.eval(tbl, heap) <= e2.eval(tbl, heap))
                    return 1;
                else
                    return 0;
            case "==":
                if (e1.eval(tbl, heap) == e2.eval(tbl, heap))
                    return 1;
                else
                    return 0;
            case "!=":
                if (e1.eval(tbl, heap) != e2.eval(tbl, heap))
                    return 1;
                else
                    return 0;
            case ">":
                if (e1.eval(tbl, heap) > e2.eval(tbl, heap))
                    return 1;
                else
                    return 0;
            case ">=":
                if (e1.eval(tbl, heap) <= e2.eval(tbl, heap))
                    return 1;
                else
                    return 0;
        }

        return 0;
    }

    public String toString() {
        return e1.toString() + " " + cmp + " " + e2.toString();
    }


}
