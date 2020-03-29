package com.toDoList.gui.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.util.Optional;

/**
 * Class-helper for loading scenes and controllers from .fxml files
 * @author Aleksey Petrenko
 */
public class LoadManager {

    public static LoadManager INSTANCE = new LoadManager();

    private LoadManager() {}

    public Optional<Scene> loadScene(String path) {
        Optional<Scene> scene = Optional.empty();
        try {
            scene = Optional.of(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scene;
    }

    public Optional<FXMLLoader> getLoader(String path) {
        return Optional.of(new FXMLLoader(getClass().getResource(path)));
    }
}
