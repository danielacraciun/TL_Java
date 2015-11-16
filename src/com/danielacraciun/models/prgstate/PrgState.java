package com.danielacraciun.models.prgstate;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.stack.IStack;
import com.danielacraciun.models.statement.IStmt;
import com.danielacraciun.repository.RepositoryException;

import java.io.*;

public class PrgState implements Serializable {
    private IStack<IStmt> exeStack;
    private Dictionary<String, Integer> symTable;
    private List<Integer> out;

    public PrgState(IStack<IStmt> stack, Dictionary<String, Integer> symbol_table, List<Integer> output) {
        exeStack = stack;
        symTable = symbol_table;
        out = output;
    }

    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    public Dictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public List<Integer> getOut() {
        return out;
    }
    public String toString() {
        return exeStack.toString() + "\n" + symTable.toString() + "\n" + out.toString();
    }
}