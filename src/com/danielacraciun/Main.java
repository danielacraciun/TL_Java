package com.danielacraciun;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.controller.ControllerException;
import com.danielacraciun.models.dictionary.ArrayDictionary;
import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.ConstExp;
import com.danielacraciun.models.expression.ReadHeapExp;
import com.danielacraciun.models.expression.VarExp;
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
//import com.danielacraciun.views.Console;

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

        IStmt st1 = new AssignStmt("v", new ConstExp(10));
        IStmt st2 = new NewStmt("a", new ConstExp(22));
        IStmt st3 = new AssignStmt("v", new ConstExp(32));
        IStmt st4 = new PrintStmt(new VarExp("v"));
        IStmt st5 = new PrintStmt(new ReadHeapExp("a"));
        IStmt st8 = new ForkStmt(new CmpStmt(new WriteHeapStmt("a", new ConstExp(30)),
                new CmpStmt(st3, new CmpStmt(st4, st5))));
        IStmt st6 = new PrintStmt(new VarExp("v"));
        IStmt st7 = new PrintStmt(new ReadHeapExp("a"));
        IStmt prgStmt = new CmpStmt(st1, new CmpStmt(st2,
                new CmpStmt(st8, new CmpStmt(st6, new CmpStmt(st7,
                        new CmpStmt(new SkipStmt(), new CmpStmt(new SkipStmt(), new SkipStmt())))))));

        es.push(prgStmt);

        try {
            ctrl.fullStep(ps, true, true, "prg.txt");
            System.out.println("Done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}