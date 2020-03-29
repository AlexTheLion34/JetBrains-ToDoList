package com.toDoList;

import com.toDoList.gui.helpers.LoadManager;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Optional<Scene> scene = LoadManager.INSTANCE.loadScene("/fxml/MainScreen.fxml");
        if (scene.isPresent()) {
            primaryStage.setScene(scene.get());
            primaryStage.setResizable(false);
            primaryStage.setTitle("To-Do");
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX(bounds.getMinX() + (bounds.getWidth() - scene.get().getWidth()) / 4);
            primaryStage.setY(bounds.getMinY() + (bounds.getHeight() - scene.get().getHeight()) / 4);
            primaryStage.show();
        }
    }
}
