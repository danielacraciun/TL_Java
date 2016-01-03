package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;

import java.io.Serializable;
import java.util.List;

public interface IRepository extends Serializable {
    void add(PrgState o);

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> prgs);
    void writeToFile(String filename);

    void serialize() throws RepositoryException;

    PrgState deserialize();
}