package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.Exp;

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

