package com.toDoList.gui.controllers;

import com.toDoList.gui.helpers.LoadManager;
import com.toDoList.model.ToDoList;
import com.toDoList.parser.ToDoListParser;
import com.toDoList.storage.LocalStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML
    private ListView<String> lists;

    @FXML
    private TextField listName;

    @FXML
    private TextField path;

    private ObservableList<String> items = FXCollections.observableArrayList();

    private ToDoListParser parser = new ToDoListParser();

    @FXML
    private void onListItemClick() {
        String name = lists.getSelectionModel().getSelectedItem();
        Optional<FXMLLoader> loader = LoadManager.INSTANCE.getLoader("/fxml/ListScreen.fxml");
        if (loader.isPresent()) {
            try {
                Parent root = loader.get().load();
                ListController listController = loader.get().getController();
                // populate selected list with its tasks
                if (LocalStorage.INSTANCE.getListByName(name).isPresent()) {
                    listController.setListOfTasks(
                            FXCollections.observableArrayList(
                            LocalStorage.INSTANCE
                                    .getListByName(name)
                                    .get()
                                    .getTasks())
                    );
                }
                // go to next screen
                Stage listStage = new Stage();
                Scene scene = new Scene(root);
                listStage.setScene(scene);
                listStage.setTitle(name);
                listStage.setResizable(false);
                Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
                listStage.setX(bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) / 4);
                listStage.setY(bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) / 4);
                listStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onCreate() {
        String name = listName.getText();
        // preventing addition of a list with the same name
        if (!items.contains(name)) {
            items.add(name);
            lists.setItems(items);
            LocalStorage.INSTANCE.addToDoList(new ToDoList(name));
        }
        listName.clear();
    }

    @FXML
    private void onImport() {
        // loading of file is performed in another thread to avoid blocking UI
        new Thread(() -> {
            Optional<ToDoList> toDoList = parser.parse(path.getText());
            if (toDoList.isPresent()) {
                // preventing addition of a list with the same name
                if (!items.contains(toDoList.get().getName())) {
                    items.add(toDoList.get().getName());
                    lists.setItems(items);
                    LocalStorage.INSTANCE.addToDoList(toDoList.get());
                }
            }
            path.clear();
        }).start();
    }
}
