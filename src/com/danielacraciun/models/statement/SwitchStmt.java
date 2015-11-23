package com.danielacraciun.models.statement;

import com.danielacraciun.models.expression.ArithmExp;
import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.prgstate.PrgState;

public class SwitchStmt implements IStmt {
    private Exp op;
    private Exp opCase1;
    private IStmt case1;
    private Exp opCase2;
    private IStmt case2;
    private IStmt defaultCase;

    public SwitchStmt(Exp op, Exp opCase1, IStmt case1, Exp opCase2, IStmt case2, IStmt defaultCase) {
        this.op = op;
        this.opCase1 = opCase1;
        this.case1 = case1;
        this.case2 = case2;
        this.opCase2 = opCase2;
        this.defaultCase = defaultCase;
    }

    public Exp getOp() {
        return op;
    }

    public Exp getOpCase1() {
        return opCase1;
    }

    public IStmt getCase1() {
        return case1;
    }

    public IStmt getCase2() {
        return case2;
    }

    public Exp getOpCase2() {
        return opCase2;
    }

    public IStmt getDefaultCase() {
        return defaultCase;
    }

    public String toString() {
        return "SWITCH(" + op.toString() + ") " + " case " + opCase1.toString() + ": " + case1.toString()
                + " case " + opCase2.toString() + ": " + case2.toString() + " default: " + defaultCase.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, UninitializedVarException {
        Exp difSwitch = new ArithmExp(op, opCase2, '-');
        Exp difSwitch2 = new ArithmExp(op, opCase1, '-');
        IStmt ifSwitch = new IfStmt(difSwitch2, defaultCase, case1);
        IStmt switchStmt = new IfStmt(difSwitch, ifSwitch, case2);
        state.getExeStack().push(switchStmt);
        return state;
    }
}
