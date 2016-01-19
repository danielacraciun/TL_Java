package com.danielacraciun.models.prgstate;

import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.DivisionByZeroException;
import com.danielacraciun.models.expression.UninitializedVarException;
import com.danielacraciun.models.filetable.Buffer;
import com.danielacraciun.models.filetable.FileTable;
import com.danielacraciun.models.heap.IHeap;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.stack.IStack;
import com.danielacraciun.models.statement.FileException;
import com.danielacraciun.models.statement.IStmt;

import java.io.Serializable;

public class PrgState implements Serializable {

    private static int gen_id = 0;
    private int state_id;
    private IStack<IStmt> exeStack;
    private Dictionary<String, Integer> symTable;
    private List<Integer> out;
    private IHeap<Integer> heap;
    private Dictionary<String, Buffer> fileTable;


    public PrgState(IStack<IStmt> stack, Dictionary<String, Integer> symbol_table,
                    List<Integer> output, IHeap<Integer> prg_heap, Dictionary<String, Buffer> fTbl) {
        exeStack = stack;
        symTable = symbol_table;
        out = output;
        heap = prg_heap;
        fileTable = fTbl;
        state_id = gen_id++;
    }

    public PrgState(IStack<IStmt> stack, Dictionary<String, Integer> symbol_table,
                    List<Integer> output, IHeap<Integer> prg_heap, Dictionary<String, Buffer> fTbl, int id) {
        exeStack = stack;
        symTable = symbol_table;
        out = output;
        heap = prg_heap;
        fileTable = fTbl;
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

    public Dictionary<String, Buffer> getFileTable() {
        return fileTable;
    }

    public void setFileTable(FileTable<String, Buffer> fileTable) {
        this.fileTable = fileTable;
    }

    public String toString() {

        return state_id + ". " + exeStack.toString() + "\n" + symTable.toString() +
                "\n" + out.toString() + "\n" + heap.toString() + "\n" + fileTable.toString() + "\n";
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
            } catch (DivisionByZeroException | UninitializedVarException | FileException e) {
                System.out.println("Error encountered.");
            }
        }
        return this;
    }

    public int getState_id() {
        return state_id;
    }
}