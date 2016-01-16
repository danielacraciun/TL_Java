package com.danielacraciun.views;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.models.prgstate.PrgState;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class DeserializeMenuView {
    public TextArea deserArea;
    private PrgState prg;
    private Controller ctrl;

    public void setPrg(PrgState prg) {
        this.prg = prg;
    }

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
        Stage stage = (Stage) deserArea.getScene().getWindow();
        stage.close();
    }

    public void showDeser(ActionEvent actionEvent) {
        if (prg != null) {
            deserArea.setText(prg.toString());
        } else {
            deserArea.setText("No serialized program.");
        }
    }
}
