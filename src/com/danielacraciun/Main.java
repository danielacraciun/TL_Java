package com.danielacraciun;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.controller.ControllerException;
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
import com.danielacraciun.repository.RepositoryException;
import com.danielacraciun.views.Console;

class Main {

    public static void main(String[] args) throws ControllerException, RepositoryException {
        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        Console console = new Console(ctrl);
        console.run();
//
//        IStack<IStmt> stk = new ArrayStack<>();
//        Dictionary<String, Integer> d = new ArrayDictionary<>();
//        List<Integer> l = new ArrayList<>();
//        IStmt prg1 = new CmpStmt(new AssignStmt("a", new ConstExp(0)),
//                new IfThenStmt(new LogicExp(new VarExp("a"), "!"), new PrintStmt(new VarExp("a"))));
//        IStmt prg2 = new CmpStmt (new AssignStmt ("a", new ConstExp(2)),
//                new WhileStmt(new LogicExp(new VarExp("a"), new ConstExp(2), "&&"),
//                new AssignStmt("a", new ArithmExp(new VarExp("a"), new ConstExp(1), '-'))));
//        IStmt prg3 = new CmpStmt (new AssignStmt ("a", new ConstExp(2)),
//                    new SwitchStmt(new VarExp("a"), new ConstExp(1), new PrintStmt(new ReadExp()),
//                            new ConstExp(2), new PrintStmt(new ArithmExp(new VarExp("a"), new ConstExp(2), '/')),
//                            new PrintStmt(new ConstExp(10))));
//        stk.push(prg3);
//        PrgState ps = new PrgState(stk, d, l);
//
//        IRepository repo = new Repository();
//        repo.add(ps);
//        Controller ctrl = new Controller(repo);
//        try {
//            ctrl.oneStepEval(true);
//            repo.serialize();
//        } catch (DivisionByZeroException e) {
//            System.out.println("Tried to divide by 0.");
//        } catch (UninitializedVarException e) {
//            System.out.println("Variable uninitialized.");
//        }
    }
}