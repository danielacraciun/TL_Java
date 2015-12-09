package com.danielacraciun;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.controller.ControllerException;
import com.danielacraciun.models.dictionary.ArrayDictionary;
import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.*;
import com.danielacraciun.models.heap.IHeap;
import com.danielacraciun.models.heap.MyHeap;
import com.danielacraciun.models.list.ArrayList;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.models.stack.ArrayStack;
import com.danielacraciun.models.stack.IStack;
import com.danielacraciun.models.statement.*;
import com.danielacraciun.repository.IRepository;
import com.danielacraciun.repository.Repository;
import com.danielacraciun.repository.RepositoryException;
import com.danielacraciun.views.Console;

class Main {

    public static void main(String[] args) throws ControllerException, RepositoryException {
//        IRepository repo = new Repository();
//        Controller ctrl = new Controller(repo);
//        Console console = new Console(ctrl);
//        console.run();

        IStack<IStmt> es = new ArrayStack<>();
        Dictionary<String, Integer> st = new ArrayDictionary<>();
        List<Integer> out = new ArrayList<>();
        IHeap<Integer> heap = new MyHeap<>();

        PrgState ps = new PrgState(es, st, out, heap);
        IRepository repo = new Repository();
        repo.add(ps);
        Controller ctrl = new Controller(repo);

        // New Statement
//
//        		IStmt st1 = new AssignStmt("v", new ConstExp(10));
//        		IStmt st2 = new NewStmt("v", new ConstExp(20));
//        		IStmt st3 = new NewStmt("a", new ConstExp(22));
//        		IStmt st4 = new PrintStmt(new VarExp("v"));
//
//        		IStmt prgStmt = new CmpStmt(new CmpStmt(st1, st2), new CmpStmt(st3, st4));
//
//        		es.push(prgStmt);

        // Read Heap Exp

//        		IStmt st1 = new AssignStmt("v", new ConstExp(10));
//        		IStmt st2 = new NewStmt("v", new ConstExp(20));
//        		IStmt st3 = new NewStmt("a", new ConstExp(22));
//        		IStmt st4 = new PrintStmt(new ArithmExp(new ConstExp(100), new ReadHeapExp("v"), '+'));
//        		IStmt st5 = new PrintStmt(new ArithmExp(new ConstExp(100), new ReadHeapExp("a"), '+'));
//
//        		IStmt prgStmt = new CmpStmt(st1, new CmpStmt(new CmpStmt(st2,st3), new CmpStmt(st4,st5)));
//
//        		es.push(prgStmt);

        // Write Heap Exp

        IStmt st1 = new AssignStmt("v", new ConstExp(10));
        IStmt st2 = new NewStmt("v", new ConstExp(20));
        IStmt st3 = new NewStmt("a", new ConstExp(22));
        IStmt st4 = new WriteHeapStmt("a", new ConstExp(30));
        IStmt st5 = new PrintStmt(new VarExp("a"));
        IStmt st6 = new PrintStmt(new ReadHeapExp("a"));

        IStmt prgStmt = new CmpStmt(new CmpStmt(st1, st2), new CmpStmt(new CmpStmt(st3, st4), new CmpStmt(st5, st6)));

        es.push(prgStmt);

        try {
            ctrl.fullStep(repo.getCrtPrg(), true, false, "file.txt");
        } catch (DivisionByZeroException | UninitializedVarException e) {
            e.printStackTrace();
        }
    }
}