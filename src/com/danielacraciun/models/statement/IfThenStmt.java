package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.Exp;

public class IfThenStmt implements IStmt {
    private Exp exp;
    private IStmt thenStmt;

    public IfThenStmt(Exp e, IStmt then) {
        exp = e;
        thenStmt = then;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenStmt.toString() + ")";
    }

    public Exp getExp() {
        return exp;
    }

    public IStmt getThenStmt() {
        return thenStmt;
    }
}
