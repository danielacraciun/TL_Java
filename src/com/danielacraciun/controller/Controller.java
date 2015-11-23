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

    public PrgState getCrtPrgState() throws ControllerException {
        try {
            return repo.getCrtPrg();
        } catch (RepositoryException e) {
            throw new ControllerException();
        }
    }

    public void serialize() throws RepositoryException {
        repo.serialize();
    }

    public PrgState deserialize(){
        return repo.deserialize();
    }


    public PrgState oneStepEval(PrgState state, Boolean printFlag, Boolean logFlag, String filename)
           throws ControllerException, DivisionByZeroException, UninitializedVarException {
        if(logFlag) {
            repo.writeToFile(filename);
        }

        if (printFlag) {
            System.out.println(state.toString());
        }

        if (state.getExeStack().isEmpty()) {
            System.out.println("Program has finished execution.");
        } else {
            IStack<IStmt> stk = state.getExeStack();
            IStmt crtStmt = stk.pop();
            return crtStmt.execute(state);
        }

        return state;
    }

    public void fullStep(PrgState state, Boolean printFlag, Boolean logFlag, String filename)
                throws ControllerException, DivisionByZeroException, UninitializedVarException {

            IStack stk = state.getExeStack();

            while (!stk.isEmpty()) {
                oneStepEval(state, printFlag, logFlag, filename);
            }

        if(logFlag) {
            repo.writeToFile(filename);
        }

        if (printFlag) {
            System.out.println(state.toString());
        }
    }
}
