package com.danielacraciun.models.statement;


import com.danielacraciun.models.expression.Exp;

/**
 * Created by dana on 11.10.2015.
 */
public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenStmt;
    private IStmt elseStmt;

    public IfStmt(Exp e, IStmt then, IStmt otherwise) {
        exp = e;
        thenStmt = then;
        elseStmt = otherwise;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenStmt.toString()
                + ")ELSE(" + elseStmt.toString() + ")";
    }

    public Exp getExp() {
        return exp;
    }

    public IStmt getThenStmt() {
        return thenStmt;
    }

    public IStmt getElseStmt() {
        return elseStmt;
    }
}
