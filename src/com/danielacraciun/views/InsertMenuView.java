package com.danielacraciun.views;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.models.dictionary.ArrayDictionary;
import com.danielacraciun.models.dictionary.Dictionary;
import com.danielacraciun.models.expression.*;
import com.danielacraciun.models.filetable.Buffer;
import com.danielacraciun.models.filetable.FileTable;
import com.danielacraciun.models.heap.IHeap;
import com.danielacraciun.models.heap.MyHeap;
import com.danielacraciun.models.list.ArrayList;
import com.danielacraciun.models.list.List;
import com.danielacraciun.models.prgstate.PrgState;
import com.danielacraciun.models.stack.ArrayStack;
import com.danielacraciun.models.stack.IStack;
import com.danielacraciun.models.statement.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class InsertMenuView {
    private final String[] STMT_CHOICES = {"Compound Statement", "Assign Statement", "Print Statement",
            "If Statement", "While Statement", "IfThen Statement", "Switch Statement", "Skip Statement",
            "New Statement", "Write Heap", "Fork Statement", "File Open Statement", "File Write Statement",
            "File Close Statement"};
    private final String[] EXPR_CHOICES = {"Arithmetic Expression", "Constant Expression",
            "Variable Expression", "Compare Expression", "Logical Expression", "Read value", "Read Heap"};
    public TextArea prgArea;
    public Button btnInsert;
    public Button btnReturn;
    ChoiceDialog<String> stmtDialog;
    ChoiceDialog<String> expDialog;
    TextInputDialog textDialog;
    private Controller ctrl;
    private IStmt crtPrg;

    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public void returnMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));

        Parent root = fxmlLoader.load();
        FirstMenuView controller = fxmlLoader.<FirstMenuView>getController();
        controller.setCtrl(ctrl);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Toy Language: Main menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }

    private String newTextInput(TextInputDialog dialog, String content) throws IOException {
        dialog.setContentText(content);
        dialog.setHeaderText("Input text");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        throw new IOException();
    }

    private Exp newExpr(ChoiceDialog<String> dialog, String content) throws IOException {
        dialog.setContentText(content);
        dialog.setHeaderText("Input expression");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equals(EXPR_CHOICES[0])) {
                String operator = newTextInput(textDialog, "Enter operator (+, -, *): ");
                Exp left = newExpr(expDialog, "Left Expression: ");
                Exp right = newExpr(expDialog, "Right Expression: ");
                return new ArithmExp(left, right, operator.charAt(0));
            }
            if (choice.equals(EXPR_CHOICES[1])) {
                Integer value = Integer.valueOf(newTextInput(textDialog, "Enter constant value: "));
                return new ConstExp(value);
            }
            if (choice.equals(EXPR_CHOICES[2])) {
                String name = newTextInput(textDialog, "Name: ");
                return new VarExp(name);
            }
            if (choice.equals(EXPR_CHOICES[3])) {
                String operator = newTextInput(textDialog, "Enter operator (<, <=, !=, ==, >=, >): ");
                Exp left = newExpr(expDialog, "Left Expression: ");
                Exp right = newExpr(expDialog, "Right Expression: ");
                return new CompExp(left, right, operator);
            }
            if (choice.equals(EXPR_CHOICES[4])) {
                String operator = newTextInput(textDialog, "Enter operator (!, &&, ||): ");
                Exp left = newExpr(expDialog, "Left Expression: ");
                Exp right = newExpr(expDialog, "Right Expression: ");
                return new LogicExp(left, right, operator);
            }
            if (choice.equals(EXPR_CHOICES[5])) {
                return new ReadExp();
            }
            if (choice.equals(EXPR_CHOICES[6])) {
                String name = newTextInput(textDialog, "Name: ");
                return new ReadHeapExp(name);
            }
        }
        throw new IOException();
    }

    private IStmt newStatement(ChoiceDialog<String> dialog, String content) throws IOException {
        stmtDialog.setContentText(content);
        dialog.setHeaderText("Input statement");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            if (choice.equals(STMT_CHOICES[0])) {
                IStmt first = newStatement(dialog, "First Statement:");
                IStmt second = newStatement(dialog, "Second Statement:");
                return new CmpStmt(first, second);
            }
            if (choice.equals(STMT_CHOICES[1])) {
                String name = newTextInput(textDialog, "Variable Name: ");
                Exp value = newExpr(expDialog, "Assigned value: ");
                return new AssignStmt(name, value);
            }
            if (choice.equals(STMT_CHOICES[2])) {
                Exp expression = newExpr(expDialog, "Expression: ");
                return new PrintStmt(expression);
            }
            if (choice.equals(STMT_CHOICES[3])) {
                Exp condition = newExpr(expDialog, "Condition: ");
                IStmt thenStatement = newStatement(dialog, "Then branch: ");
                IStmt elseStatement = newStatement(dialog, "Else branch: ");
                return new IfStmt(condition, thenStatement, elseStatement);
            }
            if (choice.equals(STMT_CHOICES[4])) {
                Exp condition = newExpr(expDialog, "Condition: ");
                IStmt body = newStatement(dialog, "Body: ");
                return new WhileStmt(condition, body);
            }
            if (choice.equals(STMT_CHOICES[5])) {
                Exp condition = newExpr(expDialog, "Condition: ");
                IStmt thenStatement = newStatement(dialog, "Then branch: ");
                return new IfThenStmt(condition, thenStatement);
            }
            if (choice.equals(STMT_CHOICES[6])) {
                Exp condition = newExpr(expDialog, "Condition: ");
                Exp case1Expression = newExpr(expDialog, "Case 1 Expression:");
                IStmt case1Statement = newStatement(dialog, "Case 1 Statement:");
                Exp case2Expression = newExpr(expDialog, "Case 2 Expression:");
                IStmt case2Statement = newStatement(dialog, "Case 2 Statement:");
                IStmt defaultStatement = newStatement(dialog, "Default Statement:");
                return new SwitchStmt(condition, case1Expression, case1Statement, case2Expression,
                        case2Statement, defaultStatement);
            }
            if (choice.equals(STMT_CHOICES[7])) {
                return new SkipStmt();
            }
            if (choice.equals(STMT_CHOICES[8])) {
                String name = newTextInput(textDialog, "Name: ");
                Exp expression = newExpr(expDialog, "Expression: ");
                return new NewStmt(name, expression);
            }
            if (choice.equals(STMT_CHOICES[9])) {
                String name = newTextInput(textDialog, "Name: ");
                Exp expression = newExpr(expDialog, "Expression: ");
                return new WriteHeapStmt(name, expression);
            }
            if (choice.equals(STMT_CHOICES[10])) {
                return new ForkStmt(newStatement(dialog, "Statement: "));
            }
            if (choice.equals(STMT_CHOICES[11])) {
                String filename = newTextInput(textDialog, "File to open: ");
                return new OpenFileStmt(filename);
            }
            if (choice.equals(STMT_CHOICES[12])) {
                String filename = newTextInput(textDialog, "File to write to: ");
                String varname = newTextInput(textDialog, "Variable Name: ");
                return new WriteFileStmt(filename, varname);
            }
            if (choice.equals(STMT_CHOICES[13])) {
                String filename = newTextInput(textDialog, "File to close: ");
                return new CloseFileStmt(filename);
            }
        }
        throw new IOException();
    }

    public void addNewPrg(Event event) throws IOException {
        stmtDialog = new ChoiceDialog<>(STMT_CHOICES[0], STMT_CHOICES);
        stmtDialog.setTitle("Inserting a new program");

        expDialog = new ChoiceDialog<>(EXPR_CHOICES[0], EXPR_CHOICES);
        expDialog.setTitle("Inserting a new program");

        textDialog = new TextInputDialog();
        textDialog.setContentText("Name:");
        crtPrg = newStatement(stmtDialog, "Choose a statement: ");
        IStack<IStmt> exeStk = new ArrayStack<>();
        Dictionary<String, Integer> tbl = new ArrayDictionary<>();
        List<Integer> out = new ArrayList<>();
        IHeap<Integer> h = new MyHeap<>();
        Dictionary<String, Buffer> file_tbl = new FileTable<>();
        exeStk.push(crtPrg);
        PrgState newPrg = new PrgState(exeStk, tbl, out, h, file_tbl);
        prgArea.setText(crtPrg.toString());
        ctrl.addPrgState(newPrg);
        btnInsert.setDisable(true);
    }

}
