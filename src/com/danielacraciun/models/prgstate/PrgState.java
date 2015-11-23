package com.danielacraciun.models.prgstate;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.heap.IHeap;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.stack.IStack;
import com.danielacraciun.models.statement.IStmt;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PrgState implements Serializable {
    private static AtomicInteger count = new AtomicInteger(0);
    private Integer state_id;
    private IStack<IStmt> exeStack;
    private Dictionary<String, Integer> symTable;
    private List<Integer> out;
    private IHeap<Integer> heap;


    public PrgState(IStack<IStmt> stack, Dictionary<String, Integer> symbol_table,
                    List<Integer> output, IHeap<Integer> prg_heap) {
        exeStack = stack;
        symTable = symbol_table;
        out = output;
        heap = prg_heap;
        state_id = count.incrementAndGet();
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

    public IHeap<Integer> getHeap() { return heap; }

    public String toString() {

        return state_id.toString() + ". " + exeStack.toString() + "\n" + symTable.toString() +
                "\n" + out.toString() + "\n" + heap.toString();
    }
}