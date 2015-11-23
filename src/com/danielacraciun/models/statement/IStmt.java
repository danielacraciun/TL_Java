package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

import java.io.Serializable;

public interface IStmt extends Serializable{
    String toString();
    PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException;
}
