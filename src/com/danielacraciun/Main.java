package com.danielacraciun;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.models.dictionary.ArrayDictionary;
import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.*;
import com.danielacraciun.models.list.ArrayList;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.models.stack.ArrayStack;
import com.danielacraciun.models.stack.IStack;
import com.danielacraciun.models.statement.*;
import com.danielacraciun.repository.IRepository;
import com.danielacraciun.repository.Repository;
import com.danielacraciun.views.Console;
//import com.danielacraciun.views.Console;

class Main {

    public static void main(String[] args) {
        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        Console console = new Console(ctrl);
        console.run();

//        IStack<IStmt> stk = new ArrayStack<>();
//        Dictionary<String, Integer> d = new ArrayDictionary<>();
//        List<Integer> l = new ArrayList<>();
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
//        stk.push(fprg);
//        PrgState ps = new PrgState(stk, d, l);
//
//        IRepository repo = new Repository();
//        repo.add(ps);
//        Controller ctrl = new Controller(repo);
//
//        ctrl.fullStep(true);
    }
}