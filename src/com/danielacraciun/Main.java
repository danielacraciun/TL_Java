package com.danielacraciun;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.models.dictionary.ArrayDictionary;
import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.*;
import com.danielacraciun.models.list.ArrayList;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.models.stack.ArrayStack;
import com.danielacraciun.models.stack.Stack;
import com.danielacraciun.models.statement.*;
import com.danielacraciun.repository.IRepository;
import com.danielacraciun.repository.Repository;
import com.danielacraciun.views.Console;

class Main {

    public static void main(String[] args) {
//        IRepository repo = new Repository();
//        Controller ctrl = new Controller(repo);
//        Console console = new Console(ctrl);
//        console.run();

        Stack stk = new ArrayStack();
        Dictionary d = new ArrayDictionary();
        List l = new ArrayList();

//        IStmt prg1 = new CmpStmt(new AssignStmt("a", new ConstExp(0)),
//                new AssignStmt("b", new ArithmExp(new VarExp("a"),
//                        new ConstExp(2), '*')));
//        IStmt prg2 = new CmpStmt (new AssignStmt ("c",
//                new ArithmExp(new VarExp("a"), new ConstExp(2), '+')),
//                new IfStmt(new VarExp("a"),
//                        new PrintStmt(new ArithmExp(new VarExp("b"), new VarExp("c"), '+')),
//                        new PrintStmt(new ArithmExp(new VarExp("b"), new VarExp("c"), '-'))));
//
//        IStmt fprg = new CmpStmt (prg1, prg2);
        IStmt st1 = new AssignStmt("a", new ConstExp(2));
        IStmt st2 = new AssignStmt("c", new ConstExp(5));
        Exp ex1 = new ArithmExp(new VarExp("a"), new ConstExp(12), '-');
        Exp ex2 = new ArithmExp(new VarExp("c"), new ConstExp(3), '*');
        IStmt st3 = new AssignStmt("b", new ArithmExp(new VarExp("c"), new ConstExp(3), '+'));
        IStmt st4 = new AssignStmt("b", new ArithmExp(new VarExp("a"), new ConstExp(10), '-'));

        IStmt st5 = new IfStmt(new CompExp(ex1, ex2, "=="), st3, st4);

        IStmt st6 = new PrintStmt(new VarExp("b"));

        IStmt fprg = new CmpStmt(new CmpStmt(st1, st2), new CmpStmt(st5, st6));
        stk.push(fprg);
        PrgState ps = new PrgState(stk, d, l);

        IRepository repo = new Repository();
        repo.add(ps);
        Controller ctrl = new Controller(repo);

        ctrl.fullStep(true);
    }
}