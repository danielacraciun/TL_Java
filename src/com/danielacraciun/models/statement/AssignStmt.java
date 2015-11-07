package com.danielacraciun.models.statement;


import com.danielacraciun.models.expression.Exp;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {
        return id + "=" + exp.toString();
    }

    public String getId() {
        return id;
    }

    public Exp getExp() {
        return exp;
    }
}
