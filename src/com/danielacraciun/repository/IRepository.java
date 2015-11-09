package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;

public interface IRepository {
    PrgState getCrtPrg() throws RepositoryException;

    void add(PrgState o);
}