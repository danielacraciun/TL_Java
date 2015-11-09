package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;
import java.util.Stack;

public class Repository implements IRepository {
    private Stack<PrgState> prgStates;

    public Repository() {
        prgStates = new Stack<>();
    }

    public PrgState getCrtPrg() throws RepositoryException {
        try {
            if (prgStates.size() > 0)
                return this.prgStates.peek();
        } catch (IndexOutOfBoundsException e) {
            throw new RepositoryException();
        }
        throw new RepositoryException();
    }

    public void add(PrgState ps) {
        prgStates.push(ps);
    }
}
