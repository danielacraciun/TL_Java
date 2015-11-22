package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.Exp;

public class NewStmt implements IStmt {
    private String id;
    private Exp exp;

    public NewStmt(Exp exp, String id) {
        this.exp = exp;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new(" + id + ", " + exp.toString() + ")";
    }
}
