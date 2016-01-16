package com.danielacraciun.views;

import com.danielacraciun.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class AllStepMenuView {
    public TextArea prgArea;
    public Button btnAllStep;
    private Controller ctrl;

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
        Stage stage = (Stage) prgArea.getScene().getWindow();
        stage.close();
    }

    public void doAllStep(ActionEvent actionEvent) {
        String s = "";
        try {
            s = ctrl.fullStep();
        } catch (InterruptedException ignored) {
        }
        prgArea.setText(s);
        btnAllStep.setDisable(true);
    }
}

