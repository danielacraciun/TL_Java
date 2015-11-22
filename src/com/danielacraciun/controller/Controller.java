package com.danielacraciun.controller;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.ArithmExp;
import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.Exp;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.heap.IHeap;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.stack.IStack;
import com.danielacraciun.models.statement.*;
import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.repository.IRepository;
import com.danielacraciun.repository.RepositoryException;


public class Controller {
    private IRepository repo;

    public Controller(IRepository r) {
        repo = r;
    }

    public void addPrgState(PrgState p) {
        repo.add(p);
    }

    public PrgState getCrtPrgState() throws RepositoryException {
        return repo.getCrtPrg();
    }

    public void serialize() throws RepositoryException {
        repo.serialize();
    }

    public PrgState deserialize(){
        return repo.deserialize();
    }

    public void oneStepEval(Boolean printFlag, Boolean logFlag, String filename)
                throws ControllerException, DivisionByZeroException, UninitializedVarException {
        try {
            PrgState state = repo.getCrtPrg();
            IStack<IStmt> stk = state.getExeStack();
            Dictionary<String, Integer> symtbl = state.getSymTable();
            List<Integer> out = state.getOut();
            IHeap<Integer> h = state.getHeap();

            IStmt crtStmt = stk.pop();

            if (crtStmt instanceof CmpStmt) {

                CmpStmt stmt1 = (CmpStmt) crtStmt;
                stk.push(stmt1.getSecond());
                stk.push(stmt1.getFirst());

            } else if (crtStmt instanceof AssignStmt) {

                AssignStmt stmt2 = (AssignStmt) crtStmt;
                symtbl.put(stmt2.getId(), stmt2.getExp().eval(symtbl, h));

            } else if (crtStmt instanceof PrintStmt) {

                PrintStmt stmt3 = (PrintStmt) crtStmt;
                out.add(stmt3.getExp().eval(symtbl, h));

            } else if (crtStmt instanceof IfStmt) {

                IfStmt stmt4 = (IfStmt) crtStmt;
                int result = stmt4.getExp().eval(symtbl, h);
                if (result != 0)
                    stk.push(stmt4.getThenStmt());
                else
                    stk.push(stmt4.getElseStmt());
            } else if (crtStmt instanceof WhileStmt) {

                WhileStmt stmt5 = (WhileStmt) crtStmt;
                if (stmt5.getExp().eval(symtbl, h) != 0) {
                    stk.push(stmt5);
                    stk.push(stmt5.getStmt());
                }

            } else if (crtStmt instanceof IfThenStmt) {

                IfThenStmt stmt6 = (IfThenStmt) crtStmt;
                stk.push(new IfStmt(stmt6.getExp(), stmt6.getThenStmt(), new SkipStmt()));

            } else if (crtStmt instanceof SwitchStmt) {

                SwitchStmt stmt7 = (SwitchStmt) crtStmt;
                Exp difSwitch = new ArithmExp(stmt7.getOp(), stmt7.getOpCase2(), '-');
                Exp difSwitch2 = new ArithmExp(stmt7.getOp(), stmt7.getOpCase1(), '-');
                IStmt ifSwitch = new IfStmt(difSwitch2, stmt7.getDefaultCase(), stmt7.getCase1());
                IStmt switchStmt = new IfStmt(difSwitch, ifSwitch, stmt7.getCase2());
                stk.push(switchStmt);

            } else if (crtStmt instanceof NewStmt) {
                NewStmt stmt8 = (NewStmt) crtStmt;
                Integer value = stmt8.getExp().eval(symtbl, h);
                symtbl.put(stmt8.getId(), h.add(value));
            } else if (crtStmt instanceof WriteHeapStmt) {
                WriteHeapStmt stmt9 = (WriteHeapStmt) crtStmt;
                h.update(symtbl.get(stmt9.getId()), stmt9.getExp().eval(symtbl, h));
            }

            if (stk.isEmpty()) System.out.println("Program has finished execution.");

            if(logFlag) {
                repo.writeToFile(filename);
            }

            if (printFlag) {
                System.out.println(stk.toString());
                System.out.println(symtbl.toString());
                System.out.println(out.toString());
                System.out.println(h.toString());
            }
        } catch (RepositoryException e) {
            throw new ControllerException();
        }
    }

    public void fullStep(Boolean printFlag, Boolean logFlag, String filename)
                throws ControllerException, DivisionByZeroException, UninitializedVarException {
        try {
            PrgState state = repo.getCrtPrg();

            IStack stk = state.getExeStack();

            while (!stk.isEmpty()) {
                oneStepEval(printFlag, logFlag, filename);
            }
        } catch (RepositoryException e) {
            throw new ControllerException();
        }
    }
}
