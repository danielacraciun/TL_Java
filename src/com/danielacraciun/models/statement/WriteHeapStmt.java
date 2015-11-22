package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.Exp;

public class WriteHeapStmt implements IStmt {
    private String id;
    private Exp exp;

    public WriteHeapStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "writeHeap( " + id + ", " + exp.toString() + ")";
    }
}
