package com.danielacraciun.models.prgstate;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.stack.Stack;

/**
 * Created by dana on 11.10.2015.
 */
public class PrgState {
    private Stack exeStack;
    private Dictionary symTable;
    private List out;

    public PrgState(Stack stack, Dictionary symbol_table, List output) {
        exeStack = stack;
        symTable = symbol_table;
        out = output;
    }

    public Stack getExeStack() {
        return exeStack;
    }

    public Dictionary getSymTable() {
        return symTable;
    }

    public List getOut() {
        return out;
    }
}