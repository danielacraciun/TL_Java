package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

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

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        Integer value = exp.eval(state.getSymTable(), state.getHeap());
        state.getSymTable().put(id, state.getHeap().add(value));
        return state;
    }
}
