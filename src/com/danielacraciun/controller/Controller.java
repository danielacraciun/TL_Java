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
    private boolean logFlag;
    private boolean printFlag;
    private boolean serFlag;
    private String filename;

    public Controller(IRepository r) {
        repo = r;
        printFlag = false;
        logFlag = false;
        serFlag = true;
        filename = "default.txt";
    }

    public void addPrgState(PrgState p) {
        repo.add(p);
        if (serFlag) try {
            this.serialize();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    public void serialize() throws RepositoryException {
        repo.serialize();
    }

    public void deserialize() {
        repo.deserialize();
    }

    public boolean allPrgsCompleted() {
        for (PrgState p : getPrgList()) {
            if (p.isNotCompleted())
                return false;
        }
        return true;
    }

    public java.util.List<PrgState> removeCompletedPrg(java.util.List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(java.util.List<PrgState> prgList)
            throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

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
            if (!newPrgs.stream()
                    .anyMatch(s -> s.getState_id() == p.getState_id()))

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

    public String fullStep()
            throws InterruptedException {
        String s = removeCompletedPrg(repo.getPrgList()) + "\n";
        while (true) {
            java.util.List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            if (prgList.size() == 0) {
                return s;
            } else {
                oneStepForAllPrg(prgList);
                s = s + prgList.toString() + "\n";
            }
        }
    }

    public List<PrgState> getPrgList() {
        return repo.getPrgList();
    }

    public boolean isLogFlag() {
        return logFlag;
    }

    public void setLogFlag(boolean logFlag) {
        this.logFlag = logFlag;
    }

    public boolean isPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(boolean printFlag) {
        this.printFlag = printFlag;
    }

    public boolean isSerFlag() {
        return serFlag;
    }

    public void setSerFlag(boolean serFlag) {
        this.serFlag = serFlag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
