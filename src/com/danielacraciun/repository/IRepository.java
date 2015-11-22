package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;

import java.io.Serializable;

public interface IRepository extends Serializable {
    PrgState getCrtPrg() throws RepositoryException;
    void serialize() throws RepositoryException;
    PrgState deserialize();
    void add(PrgState o);
    void writeToFile(String filename);
}