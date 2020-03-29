package com.toDoList.storage;

import com.toDoList.model.ToDoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class represents in-memory storage for all to-do lists
 * @author Aleksey Petrenko
 */
public class LocalStorage {

    public static final LocalStorage INSTANCE = new LocalStorage();

    private List<ToDoList> toDoLists;

    private LocalStorage() {
        toDoLists = new ArrayList<>();
    }

    public void addToDoList(ToDoList list) {
        toDoLists.add(list);
    }

    public Optional<ToDoList> getListByName(String name) {
        for (ToDoList toDoList : toDoLists) {
            if (toDoList.getName().equals(name)) {
                return Optional.of(toDoList);
            }
        }
        return Optional.empty();
    }
}
