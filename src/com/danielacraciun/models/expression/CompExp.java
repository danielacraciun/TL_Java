package com.danielacraciun.models.expression;

import com.danielacraciun.models.dictionary.Dictionary;

/**
 * Created by dana on 03.11.2015.
 */
public class CompExp extends Exp {
    private Exp e1;
    private Exp e2;
    private String cmp;

    public CompExp(Exp e1, Exp e2, String cmp) {
        this.e1 = e1;
        this.e2 = e2;
        this.cmp = cmp;
    }

    public int eval(Dictionary tbl) {
        switch (cmp) {
            case "<":
                if (e1.eval(tbl) < e2.eval(tbl))
                    return 1;
                else
                    return 0;
            case "<=":
                if (e1.eval(tbl) <= e2.eval(tbl))
                    return 1;
                else
                    return 0;
            case "==":
                if (e1.eval(tbl) == e2.eval(tbl))
                    return 1;
                else
                    return 0;
            case "!=":
                if (e1.eval(tbl) != e2.eval(tbl))
                    return 1;
                else
                    return 0;
            case ">":
                if (e1.eval(tbl) > e2.eval(tbl))
                    return 1;
                else
                    return 0;
            case ">=":
                if (e1.eval(tbl) <= e2.eval(tbl))
                    return 1;
                else
                    return 0;
        }

        return 0;
    }

    public String toString() {
        return e1.toString() + " " + cmp + " " + e2.toString();
    }

    public Exp getE1() {
        return e1;
    }

    public Exp getE2() {
        return e2;
    }

    public String getCmp() {
        return cmp;
    }
}