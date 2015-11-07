package com.danielacraciun.models.statement;

public class CmpStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CmpStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    public IStmt getFirst() {
        return first;
    }

    public IStmt getSecond() {
        return second;
    }
}
