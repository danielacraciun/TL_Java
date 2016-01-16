package com.danielacraciun.views;

import com.danielacraciun.controller.Controller;
import com.danielacraciun.repository.IRepository;
import com.danielacraciun.repository.Repository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));

        Parent root = fxmlLoader.load();
        FirstMenuView controller = fxmlLoader.<FirstMenuView>getController();
        controller.setCtrl(ctrl);

        primaryStage.setTitle("Toy Language: Main menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
