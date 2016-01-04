package com.danielacraciun.controller;

import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.repository.IRepository;
import com.danielacraciun.repository.RepositoryException;

import java.util.List;
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

    public void serialize() throws RepositoryException {
        repo.serialize();
    }

    public PrgState deserialize() {
        return repo.deserialize();
    }

    public java.util.List<PrgState> removeCompletedPrg(java.util.List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
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
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.forEach(p -> {
            if (!newPrgs.stream().anyMatch(s -> s.getState_id() == p.getState_id()))
                newPrgs.add(p);
        });

        repo.setPrgList(newPrgs);

        executor.shutdown();

        if (logFlag) {
            repo.writeToFile(filename);
        }

        if (printFlag) {
            newPrgs.forEach(p -> System.out.println(p.toString()));
        }
    }

    public void fullStep(Boolean printFlag, Boolean logFlag, String filename)
            throws InterruptedException {

        while (true) {
            java.util.List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            if (prgList.size() == 0) {
                return;
            } else {
                oneStepForAllPrg(prgList, printFlag, logFlag, filename);
            }
        }
    }

    public List<PrgState> getPrgList() {
        return repo.getPrgList();
    }
}
