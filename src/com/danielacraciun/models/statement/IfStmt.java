package com.danielacraciun.models.statement;


import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenStmt;
    private IStmt elseStmt;

    public IfStmt(Exp e, IStmt then, IStmt otherwise) {
        exp = e;
        thenStmt = then;
        elseStmt = otherwise;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenStmt.toString()
                + ")ELSE(" + elseStmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        int result = exp.eval(state.getSymTable(), state.getHeap());
        if (result != 0)
            state.getExeStack().push(thenStmt);
        else
            state.getExeStack().push(elseStmt);
        return state;
    }

    public Exp getExp() {
        return exp;
    }

    public IStmt getThenStmt() {
        return thenStmt;
    }

    public IStmt getElseStmt() {
        return elseStmt;
    }
}
