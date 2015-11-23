package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

public class SkipStmt implements  IStmt{
    @Override
    public String toString() {
        return "skip";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        return state;
    }
}
