package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;
import java.util.Stack;

public class Repository implements IRepository {
    private Stack<PrgState> prgStates;

    public Repository() {
        prgStates = new Stack<>();
    }

    public PrgState getCrtPrg() {
        if (! prgStates.isEmpty())
            return prgStates.peek();
        return null;
    }

    public void add(PrgState ps) {
        prgStates.push(ps);
    }
}
