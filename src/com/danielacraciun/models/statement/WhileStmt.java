package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.Exp;

public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp e, IStmt stmt) {
        this.exp = e;
        this.stmt = stmt;
    }

    public Exp getExp() {
        return exp;
    }

    public IStmt getStmt() {
        return stmt;
    }

    @Override
    public String toString() {
        return "While( " + exp.toString() + ") { " + stmt.toString() + " }";
    }
}