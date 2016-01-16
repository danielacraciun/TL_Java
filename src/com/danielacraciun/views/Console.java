package com.danielacraciun.views;

import com.danielacraciun.controller.Controller;
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
import com.danielacraciun.repository.RepositoryException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private Controller ctrl;
    private Boolean printFlag;
    private Boolean logFlag;
    private Scanner scanner;
    private String crtfile;


    public Console(Controller ctrl) {
        this.ctrl = ctrl;
        printFlag = true;
        logFlag = false;
        scanner = new Scanner(System.in);
        crtfile = "default.txt";
    }

    private void mainMenu() throws ConsoleException {

        System.out.println("Hello. Please choose an option below:");
        System.out.println("1. Input a program; ");
        System.out.println("2. Do a one-step evaluation of the current program;");
        System.out.println("3. Do a full-step evaluation of the current program;");
        System.out.println("4. Edit print flag and logging;");
        System.out.println("5. Load initial program state;");
        System.out.println("Exit by pressing 0.");

        try {
            Integer opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    addProgram();
                    break;
                case 2:
                    oneStep();
                    break;
                case 3:
                    fullStep();
                    break;
                case 4:
                    setFlag();
                    break;
                case 5:
                    seeInitPrgState();
                case 0:
                    break;
                default:
                    System.out.println("Wrong option. Try again.");
            }
        } catch (InputMismatchException e) {
            throw new ConsoleException();
        }
    }

    private void seeInitPrgState() {
        try {
            System.out.println(ctrl.deserialize().toString());
            mainMenu();
        } catch (ConsoleException e) {
            System.out.println("Wrong option. Try again.");
        }
    }

    private void setFlag() {
        System.out.println("1. Edit print flag (currently set to: " + this.printFlag.toString() + ");");
        System.out.println("2. Save logging to a file (currently set to: " + this.logFlag.toString() + ");");
        System.out.println("Return to main menu by pressing 0.");

        Integer opt = scanner.nextInt();

        switch (opt) {
            case 1:
                this.printFlag = !this.printFlag;
                System.out.println("Print flag changed. It is now " + this.printFlag.toString() + ".");
                setFlag();
                break;
            case 2:
                this.logFlag = !this.logFlag;
                if (logFlag) {
                    System.out.println("File to print to: ");
                    this.crtfile = scanner.next();
                    System.out.println("Printing to " + this.crtfile + ".");
                }
                System.out.println("Log flag changed. It is now " + this.logFlag.toString() + ".");
                setFlag();
                break;
            case 0:
                try {
                    mainMenu();
                } catch (ConsoleException e) {
                    System.out.println("Wrong option. Try again.");
                }
                break;
            default:
                System.out.println("Wrong option. Try again.");
        }

    }

    private void fullStep() {
        try {
            System.out.println(ctrl.getPrgList().get(0));
            ctrl.fullStep();
            mainMenu();
        } catch (ConsoleException e) {
            System.out.println("Wrong option. Try again.");
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
        }
    }

    private void oneStep() {
        try {
            ctrl.oneStepForAllPrg(ctrl.getPrgList());
            mainMenu();
        } catch (ConsoleException e) {
            System.out.println("Wrong option. Try again.");
        } catch (InterruptedException e) {
            System.out.println("Interrupted.");
        }
    }

    private void addProgram() {
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
                new CmpStmt(st8, new CmpStmt(st6, st7))));
//        IStmt prgStmt = addNewStmt();
        IStack<IStmt> exeStk = new ArrayStack<>();
        Dictionary<String, Integer> tbl = new ArrayDictionary<>();
        List<Integer> out = new ArrayList<>();
        IHeap<Integer> h = new MyHeap<>();
        exeStk.push(prgStmt);

        PrgState newPrg = new PrgState(exeStk, tbl, out, h);
        ctrl.addPrgState(newPrg);

        try {
            ctrl.serialize();
        } catch (RepositoryException e) {
            System.out.println("Program state error.");
        }

        try {
            mainMenu();
        } catch (ConsoleException e) {
            System.out.println("Wrong option. Try again.");
        }
    }

    private IStmt addNewStmt() {
        System.out.println("Choose a type of statement:");
        System.out.println("1. Compound statement");
        System.out.println("2. Assignment statement");
        System.out.println("3. If/then/else statement");
        System.out.println("4. Print statement");
        System.out.println("5. While statement");
        System.out.println("6. Skip statement");
        System.out.println("7. If/then statement");
        System.out.println("8. Switch statement");
        System.out.println("9. New statement");
        System.out.println("10. Write to heap statement");
        System.out.println("11. Fork statement");

        Integer opt = scanner.nextInt();

        IStmt st;
        switch (opt) {
            case 1:
                System.out.println("First statement:");
                IStmt st1 = addNewStmt();
                System.out.println("Second statement:");
                IStmt st2 = addNewStmt();
                st = new CmpStmt(st1, st2);
                break;
            case 2:
                System.out.println("Variable name:");
                String varName = scanner.next();
                System.out.println("Right side expression:");
                Exp exp = addNewExp();
                st = new AssignStmt(varName, exp);
                break;
            case 3:
                System.out.println("Expression to evaluate:");
                Exp expr = addNewExp();
                System.out.println("Then Statement:");
                IStmt then = addNewStmt();
                System.out.println("Else Statement:");
                IStmt el = addNewStmt();
                st = new IfStmt(expr, then, el);
                break;
            case 4:
                Exp e = addNewExp();
                st = new PrintStmt(e);
                break;
            case 5:
                System.out.println("Expression to evaluate:");
                Exp expression = addNewExp();
                System.out.println("Statement:");
                IStmt statement = addNewStmt();
                st = new WhileStmt(expression, statement);
                break;
            case 6:
                st = new SkipStmt();
                break;
            case 7:
                System.out.println("Expression to evaluate:");
                Exp exp1 = addNewExp();
                System.out.println("Then Statement:");
                IStmt then1 = addNewStmt();
                st = new IfThenStmt(exp1, then1);
                break;
            case 8:
                System.out.println("Switch operator:");
                String variableName = scanner.next();
                expr = new VarExp(variableName);

                System.out.println("Case 1 expression:");
                Exp expCase1 = addNewExp();
                System.out.println("Case 1 Statement:");
                IStmt case1 = addNewStmt();

                System.out.println("Case 2 expression:");
                Exp expCase2 = addNewExp();
                System.out.println("Case 2 Statement:");
                IStmt case2 = addNewStmt();

                System.out.println("Default case Statement:");
                IStmt caseDefault = addNewStmt();

                st = new SwitchStmt(expr, expCase1, case1, expCase2, case2, caseDefault);
                break;
            case 9:
                System.out.println("Variable name:");
                String newVar = scanner.next();
                System.out.println("Assigned expression:");
                Exp exp9 = addNewExp();
                st = new NewStmt(newVar, exp9);
                break;
            case 10:
                System.out.println("Variable name:");
                String heapVar = scanner.next();
                System.out.println("Assigned expression:");
                Exp exp10 = addNewExp();
                st = new WriteHeapStmt(heapVar, exp10);
                break;
            case 11:
                System.out.println("To fork statement:");
                IStmt st11 = addNewStmt();
                st = new ForkStmt(st11);
                break;
            default:
                System.out.println("Please try one of the options above.");
                st = addNewStmt();
        }

        return st;
    }

    private Exp addNewExp() {
        System.out.println("Choose a type of expression:");
        System.out.println("1. Arithmetical expression");
        System.out.println("2. Constant expression");
        System.out.println("3. Variable expression");
        System.out.println("4. Comparison expression");
        System.out.println("5. Logical expression");
        System.out.println("6. Read expression");
        System.out.println("7. Read heap expression");

        Integer opt = scanner.nextInt();

        Exp expr;
        switch (opt) {
            case 1:
                System.out.println("Choose operation: +, -, *, /");
                String op = scanner.next();
                if (Arrays.asList("+", "-", "*", "/").contains(op)) {
                    System.out.println("Left hand side:");
                    Exp left = addNewExp();
                    System.out.println("Right hand side:");
                    Exp right = addNewExp();
                    expr = new ArithmExp(left, right, op.charAt(0));
                } else {
                    System.out.println("Operand MUST be +, -, *, /. Try again");
                    expr = addNewExp();
                }
                break;
            case 2:
                System.out.println("Give a number:");
                Integer no = scanner.nextInt();
                expr = new ConstExp(no);
                break;
            case 3:
                System.out.println("Variable name:");
                String varName = scanner.next();
                expr = new VarExp(varName);
                break;
            case 4:
                System.out.println("Choose comp. operand: <, <=, ==, !=, >=, >");
                String op1 = scanner.next();
                if (Arrays.asList("<", "<=", "==", "!=", ">=", ">").contains(op1)) {
                    System.out.println("Left hand side:");
                    Exp left = addNewExp();
                    System.out.println("Right hand side:");
                    Exp right = addNewExp();
                    expr = new CompExp(left, right, op1);
                } else {
                    System.out.println("Operand MUST be a comp. operator. Try again");
                    expr = addNewExp();
                }
                break;
            case 5:
                System.out.println("Choose logical operand: && (and), ||(or), !(not)");
                String op2 = scanner.next();
                if (Arrays.asList("&&", "||").contains(op2)) {
                    System.out.println("Left hand side:");
                    Exp left = addNewExp();
                    System.out.println("Right hand side:");
                    Exp right = addNewExp();
                    expr = new LogicExp(left, right, op2);
                } else if (op2.equals("!")) {
                    System.out.println("Expression:");
                    Exp singleExp = addNewExp();
                    expr = new LogicExp(singleExp, op2);
                } else {
                    System.out.println("Operand MUST be a logical operator. Try again");
                    expr = addNewExp();
                }
                break;
            case 6:
                expr = new ReadExp();
                break;
            case 7:
                System.out.println("Variable name:");
                String varNew = scanner.next();
                expr = new ReadHeapExp(varNew);
                break;
            default:
                System.out.println("Please try one of the options above.");
                expr = addNewExp();
                break;
        }
        return expr;
    }

    public void run() {
        try {
            mainMenu();
        } catch (ConsoleException e) {
            System.out.println("Wrong option. Try again.");
        }
    }
}
