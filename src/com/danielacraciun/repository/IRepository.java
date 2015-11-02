package com.danielacraciun.repository;

import com.danielacraciun.models.prgstate.PrgState;

/**
 * Created by dana on 13.10.2015.
 */

public interface IRepository {
    PrgState getCrtPrg();

    void add(PrgState o);
}
