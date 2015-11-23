package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

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

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        state.getHeap().update(state.getSymTable().get(id), exp.eval(state.getSymTable(), state.getHeap()));
        return state;
    }
}
