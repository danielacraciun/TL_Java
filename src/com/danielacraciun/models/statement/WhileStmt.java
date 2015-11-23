package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp e, IStmt stmt) {
        this.exp = e;
        this.stmt = stmt;
    }

    public Exp getExp() {
        return exp;
    }

    public IStmt getStmt() {
        return stmt;
    }

    @Override
    public String toString() {
        return "While( " + exp.toString() + ") { " + stmt.toString() + " }";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        if (exp.eval(state.getSymTable(), state.getHeap()) != 0) {
            state.getExeStack().push(this);
            state.getExeStack().push(stmt);
        }
        return state;
    }
}