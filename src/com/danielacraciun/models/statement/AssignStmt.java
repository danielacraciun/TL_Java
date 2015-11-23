package com.danielacraciun.models.statement;


import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

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

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        state.getSymTable().put(id, exp.eval(state.getSymTable(), state.getHeap()));
        return state;
    }

    public String getId() {
        return id;
    }

    public Exp getExp() {
        return exp;
    }
}
