package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.Exp;

/**
 * Created by dana on 11.10.2015.
 */
public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public Exp getExp() {
        return exp;
    }
}

