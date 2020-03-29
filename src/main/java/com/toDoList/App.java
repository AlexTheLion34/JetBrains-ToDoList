package com.toDoList;

import com.toDoList.gui.helpers.LoadManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Optional;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Optional<Scene> scene = LoadManager.INSTANCE.loadScene("/fxml/MainScreen.fxml");
        if (scene.isPresent()) {
            primaryStage.setScene(scene.get());
            primaryStage.setResizable(false);
            primaryStage.setTitle("To-Do");
            primaryStage.show();
        }
    }
}
