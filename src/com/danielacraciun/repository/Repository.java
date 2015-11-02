package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;

/**
 * Created by dana on 12.10.2015.
 */

public class Repository implements IRepository {
    private PrgState[] prgStates;
    private int nrPrg;

    public Repository(PrgState[] states) {
        prgStates = states;
        nrPrg = states.length;
    }

    public Repository() {
        prgStates = new PrgState[20];
        nrPrg = 0;
    }

    public PrgState getCrtPrg() {
        if (nrPrg > 0)
            return prgStates[0];
        return null;
    }

    public void add(PrgState ps) {
        prgStates[nrPrg++] = ps;
    }
}
