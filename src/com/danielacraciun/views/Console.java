package com.danielacraciun.views;

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

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private Controller ctrl;
    private Boolean printFlag;
    private Scanner scanner;

    public Console(Controller ctrl) {
        this.ctrl = ctrl;
        printFlag = true;
        scanner = new Scanner(System.in);
    }

    private void mainMenu() {
        System.out.println("Hello. Please choose an option below:");
        System.out.println("1. Input a program; ");
        System.out.println("2. Do a one-step evaluation of the current program;");
        System.out.println("3. Do a full-step evaluation of the current program;");
        System.out.println("4. Change print flag (currently set to: " + this.printFlag.toString() + ")");
        System.out.println("Exit by pressing 0.");

        try {
            Integer opt = scanner.nextInt();
            if (ctrl.getCrtPrgState() == null && opt > 1) {
                System.out.println("There is no current program. Please add one.");
                addProgram();
            } else {
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
                    case 0:
                        System.out.println("Goodbye.");
                        break;
                    default:
                        System.out.println("Wrong option. Try again.");
                        mainMenu();
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input incorrect, program will terminate.");
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void setFlag() {
        this.printFlag = !this.printFlag;
        System.out.println("Flag changed. It is now " + this.printFlag.toString() + ".");
        System.out.println("Press Enter to go back.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainMenu();
    }

    private void fullStep() {
        ctrl.fullStep(printFlag);
        mainMenu();
    }

    private void oneStep() {
        ctrl.oneStepEval(printFlag);
        mainMenu();
    }

    private void addProgram() {
        IStmt prgStmt = addNewStmt();
        IStack<IStmt> exeStk = new ArrayStack<>();
        Dictionary<String, Integer> tbl = new ArrayDictionary<>();
        List<Integer> out = new ArrayList<>();
        exeStk.push(prgStmt);

        PrgState crtPrg = new PrgState(exeStk, tbl, out);
        ctrl.addPrgState(crtPrg);
        mainMenu();
    }

    private IStmt addNewStmt() {
        System.out.println("Choose a type of statement:");
        System.out.println("1. Compound statement");
        System.out.println("2. Assignment statement");
        System.out.println("3. If statement");
        System.out.println("4. Print statement");
        System.out.println("5. While statement");

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
                } else if(op2.equals("!")) {
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
            default:
                System.out.println("Please try one of the options above.");
                expr = addNewExp();
                break;
        }
        return expr;
    }

    public void run() {
        mainMenu();
    }
}
