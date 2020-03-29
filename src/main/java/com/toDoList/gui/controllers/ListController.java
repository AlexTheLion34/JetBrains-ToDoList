package com.toDoList.gui.controllers;

import com.toDoList.model.Task;
import com.toDoList.model.ToDoList;
import com.toDoList.storage.LocalStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ListController {

    @FXML
    private ListView<Task> listOfTasks;

    @FXML
    private TextField taskField;

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    private ObservableList<Task> filteredTasks = FXCollections.observableArrayList();

    private int counterFiltered;

    private String listName;

    @FXML
    public void initialize() {

        counterFiltered = 0;

        listOfTasks.setCellFactory(CheckBoxListCell.forListView(Task::selectedProperty, new StringConverter<Task>() {
            @Override
            public String toString(Task object) {
                return object.getText();
            }

            @Override
            public Task fromString(String string) {
                return null;
            }
        }));
    }

    public void setListOfTasks(ObservableList<Task> tasks) {
        this.tasks = tasks;
        listOfTasks.setItems(tasks);
    }

    @FXML
    private void onAdd() {
        listName = getListName();
        String text = taskField.getText();
        Task task = new Task(text);
        tasks.add(task);
        listOfTasks.setItems(tasks);
        if (LocalStorage.INSTANCE.getListByName(listName).isPresent()) {
            LocalStorage.INSTANCE.getListByName(listName).get().addTask(task);
        }
        taskField.clear();
    }

    @FXML
    private void onDelete() {
        listName = getListName();
        int selectedIndex = listOfTasks.getSelectionModel().getSelectedIndex();
        if (LocalStorage.INSTANCE.getListByName(listName).isPresent()) {
            ToDoList list = LocalStorage.INSTANCE.getListByName(listName).get();
            list.deleteTask(listOfTasks.getItems().get(selectedIndex));
        }
        listOfTasks.getItems().remove(selectedIndex);
    }

    @FXML
    private void onFilter() {
        if (counterFiltered % 2 == 0) {
            tasks.forEach(task -> {
                if (!task.isSelected()) {
                    filteredTasks.add(task);

                }
            });
            listOfTasks.setItems(filteredTasks);
        } else {
            filteredTasks.clear();
            listOfTasks.setItems(tasks);
        }
        counterFiltered++;
    }

    // ugly workaround on getting current list's name
    private String getListName() {
        Stage stage = (Stage) listOfTasks.getScene().getWindow();
        return stage.getTitle();
    }
}
