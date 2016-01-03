package com.danielacraciun.models.statement;

import com.danielacraciun.models.dictionary.ArrayDictionary;
import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.models.stack.ArrayStack;
import com.danielacraciun.models.stack.IStack;

public class ForkStmt implements IStmt {
    private IStmt forkStmt;

    public ForkStmt(IStmt forkStmt) {
        this.forkStmt = forkStmt;
    }

    public IStmt getForkStmt() {
        return forkStmt;
    }

    @Override
    public String toString() {
        return "fork(" + forkStmt.toString() + ')';
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        IStack<IStmt> newStk = new ArrayStack<>();
        newStk.push(forkStmt);
        Dictionary<String, Integer> newDict = new ArrayDictionary<>(
                (ArrayDictionary<String, Integer>) state.getSymTable()
        );
        return new PrgState(newStk, newDict, state.getOut(), state.getHeap(), (state.getState_id() + 1) * 10);
    }
}
