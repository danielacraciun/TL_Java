package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        state.getOut().add(exp.eval(state.getSymTable(), state.getHeap()));
        return state;
    }

    public Exp getExp() {
        return exp;
    }
}

