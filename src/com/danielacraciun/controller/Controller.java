package com.danielacraciun.controller;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.stack.Stack;
import com.danielacraciun.models.statement.*;
import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.repository.IRepository;

/**
 * Created by dana on 20.10.2015.
 */

public class Controller {
    private IRepository repo;

    public Controller(IRepository r) {
        repo = r;
    }

    public void addPrgState(PrgState p) {
        repo.add(p);
    }

    public PrgState getCrtPrgState() {
        return repo.getCrtPrg();
    }

    public void oneStepEval(Boolean printFlag) {
        PrgState state = repo.getCrtPrg();

        Stack stk = state.getExeStack();
        Dictionary symtbl = state.getSymTable();
        List out = state.getOut();

        IStmt crtStmt = (IStmt) stk.pop();

        if (crtStmt instanceof CmpStmt) {

            CmpStmt stmt1 = (CmpStmt) crtStmt;
            stk.push(stmt1.getSecond());
            stk.push(stmt1.getFirst());

        } else if (crtStmt instanceof AssignStmt) {

            AssignStmt stmt2 = (AssignStmt) crtStmt;
            symtbl.put(stmt2.getId(), stmt2.getExp().eval(symtbl));

        } else if (crtStmt instanceof PrintStmt) {

            PrintStmt stmt3 = (PrintStmt) crtStmt;
            out.add(stmt3.getExp().eval(symtbl));

        } else if (crtStmt instanceof IfStmt) {

            IfStmt stmt4 = (IfStmt) crtStmt;
            int result = stmt4.getExp().eval(symtbl);
            if (result != 0)
                stk.push(stmt4.getThenStmt());
            else
                stk.push(stmt4.getElseStmt());
        }

        if (stk.isEmpty()) System.out.println("Program has finished execution.");

        if (printFlag) {
            System.out.println(stk.toString());
            System.out.println(symtbl.toString());
            System.out.println(out.toString());
        }
    }

    public void fullStep(Boolean printFlag) {
        PrgState state = repo.getCrtPrg();

        Stack stk = state.getExeStack();

        while (!stk.isEmpty()) {
            oneStepEval(printFlag);
        }

    }
}
