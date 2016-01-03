package com.danielacraciun.controller;

import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.repository.IRepository;
import com.danielacraciun.repository.RepositoryException;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    private IRepository repo;

    public Controller(IRepository r) {
        repo = r;
    }

    public void addPrgState(PrgState p) {
        repo.add(p);
    }

    public PrgState getCrtPrgState() throws ControllerException {
        return repo.getPrgList().get(0);
    }

    public void serialize() throws RepositoryException {
        repo.serialize();
    }

    public PrgState deserialize() {
        return repo.deserialize();
    }

    public java.util.List<PrgState> removeCompletedPrg(java.util.List<PrgState> inPrgList) {
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    public void oneStepForAllPrg(java.util.List<PrgState> prgList, Boolean printFlag, Boolean logFlag, String filename)
            throws InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        java.util.List<Callable<PrgState>> callList = prgList.stream()
                .map(p -> (Callable<PrgState>) p::oneStep)
                .collect(Collectors.toList());

        java.util.List<PrgState> newPrgs = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        return null;
                    }
                }).filter(p -> p != null).collect(Collectors.toList());

        prgList.forEach(p -> {
            if (!newPrgs.stream().anyMatch(s -> s.getState_id() == p.getState_id())) newPrgs.add(p);
        });

        repo.setPrgList(newPrgs);

        if (logFlag) {
            repo.writeToFile(filename);
        }

        if (printFlag) {
            newPrgs.forEach(p -> System.out.println(p.toString()));
        }
    }

//    public PrgState oneStepEval(PrgState state, Boolean printFlag, Boolean logFlag, String filename)
//           throws ControllerException, DivisionByZeroException, UninitializedVarException {
//        if(logFlag) {
//            repo.writeToFile(filename);
//        }
//
//        if (printFlag) {
//            System.out.println(state.toString());
//        }
//
//        if (state.getExeStack().isEmpty()) {
//            System.out.println("Program has finished execution.");
//        } else {
//            IStack<IStmt> stk = state.getExeStack();
//            IStmt crtStmt = stk.pop();
//            return crtStmt.execute(state);
//        }
//
//        return state;
//    }

//    public void fullStep(PrgState state, Boolean printFlag, Boolean logFlag, String filename)
//                throws ControllerException, DivisionByZeroException, UninitializedVarException {
//
//            IStack stk = state.getExeStack();
//
//            while (!stk.isEmpty()) {
//                state.oneStep();
//            }
//
//        if(logFlag) {
//            repo.writeToFile(filename);
//        }
//
//        if (printFlag) {
//            System.out.println(state.toString());
//        }
//    }

    public void fullStep(PrgState state, Boolean printFlag, Boolean logFlag, String filename) throws InterruptedException {
        while (true) {
            java.util.List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            if (prgList.size() == 0) {
                return;
            } else {
                oneStepForAllPrg(prgList, printFlag, logFlag, filename);
            }
        }
    }
}
