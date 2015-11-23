package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

public class IfThenStmt implements IStmt {
    private Exp exp;
    private IStmt thenStmt;

    public IfThenStmt(Exp e, IStmt then) {
        exp = e;
        thenStmt = then;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenStmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        state.getExeStack().push(new IfStmt(exp, thenStmt, new SkipStmt()));
        return state;
    }

    public Exp getExp() {
        return exp;
    }

    public IStmt getThenStmt() {
        return thenStmt;
    }
}
