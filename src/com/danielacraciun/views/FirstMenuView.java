package com.danielacraciun.views;

import com.danielacraciun.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstMenuView {

    public CheckBox checkSer;
    public CheckBox checkLog;
    private Controller ctrl;

    public void insertNewPrg(ActionEvent actionEvent) throws IOException {
        if (ctrl.getPrgList().size() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Program already inserted");
            alert.setHeaderText("Warning!");
            alert.setContentText("There is another program loaded.");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InsertView.fxml"));

            Parent root = fxmlLoader.load();
            InsertMenuView controller = fxmlLoader.<InsertMenuView>getController();
            controller.setCtrl(ctrl);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Toy Language: Insert a program");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            Stage stage = (Stage) checkSer.getScene().getWindow();
            stage.close();
        }
    }

    public void oneStepEval(ActionEvent actionEvent) throws IOException {
        if (ctrl.allPrgsCompleted()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Execution finished.");
            alert.setHeaderText("Warning!");
            alert.setContentText("Programs have finished executing.");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OneStepView.fxml"));

            Parent root = fxmlLoader.load();
            OneStepMenuView controller = fxmlLoader.<OneStepMenuView>getController();
            controller.setCtrl(ctrl);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Toy Language: One step over program");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            Stage stage = (Stage) checkSer.getScene().getWindow();
            stage.close();
        }
    }

    public void deserializePrg(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeserializeView.fxml"));

        Parent root = fxmlLoader.load();
        DeserializeMenuView controller = fxmlLoader.<DeserializeMenuView>getController();
        ctrl.deserialize();
        controller.setPrg(ctrl.getPrgList().get(0));
        controller.setCtrl(ctrl);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Toy Language: Deserialize");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Stage stage = (Stage) checkSer.getScene().getWindow();
        stage.close();
    }

    public void fullStepEval(ActionEvent actionEvent) throws IOException {
        if (ctrl.allPrgsCompleted()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Execution finished.");
            alert.setHeaderText("Warning!");
            alert.setContentText("Programs have finished executing.");
            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AllStepView.fxml"));

            Parent root = fxmlLoader.load();
            AllStepMenuView controller = fxmlLoader.<AllStepMenuView>getController();
            controller.setCtrl(ctrl);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Toy Language: Full step over program");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            Stage stage = (Stage) checkSer.getScene().getWindow();
            stage.close();
        }
    }

    public void modifySerFlag(ActionEvent actionEvent) {
        this.ctrl.setSerFlag(!this.ctrl.isSerFlag());
    }

    public void modifyLogFlag(ActionEvent actionEvent) {
        this.ctrl.setLogFlag(!this.ctrl.isLogFlag());
    }

    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
        checkSer.setSelected(this.ctrl.isSerFlag());
        checkLog.setSelected(this.ctrl.isLogFlag());
    }
}
