package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.Exp;

public class NewStmt implements IStmt {
    private String id;
    private Exp exp;

    public NewStmt(String id, Exp exp) {
        this.exp = exp;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new(" + id + ", " + exp.toString() + ")";
    }
}
