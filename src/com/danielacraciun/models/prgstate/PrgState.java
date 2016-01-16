package com.danielacraciun.models.prgstate;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.heap.IHeap;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.stack.IStack;
import com.danielacraciun.models.statement.IStmt;

import java.io.Serializable;

public class PrgState implements Serializable {

    private static int gen_id = 0;
    private int state_id;
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
        state_id = gen_id++;
    }

    public PrgState(IStack<IStmt> stack, Dictionary<String, Integer> symbol_table,
                    List<Integer> output, IHeap<Integer> prg_heap, int id) {
        exeStack = stack;
        symTable = symbol_table;
        out = output;
        heap = prg_heap;
        state_id = id;
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

        return state_id + ". " + exeStack.toString() + "\n" + symTable.toString() +
                "\n" + out.toString() + "\n" + heap.toString() + "\n";
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() {
        if (exeStack.isEmpty()) {
            System.out.println("Program has finished execution.");
        } else {
            IStmt crtStmt = exeStack.pop();
            try {
                return crtStmt.execute(this);
            } catch (DivisionByZeroException | UninitializedVarException e) {
                System.out.println("Error encountered.");
            }
        }
        return this;
    }

    public int getState_id() {
        return state_id;
    }
}