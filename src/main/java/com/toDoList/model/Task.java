package com.toDoList.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;

public class Task {

    private ReadOnlyStringWrapper text = new ReadOnlyStringWrapper();

    private BooleanProperty selected = new SimpleBooleanProperty(false);

    public Task(String text) {
        this.text.set(text);
    }

    public Task() {}

    @JsonSetter("text")
    public void setText(String text) {
        this.text.set(text);
    }

    public String getText() {
        return text.get();
    }

    public ReadOnlyStringProperty textProperty() {
        return text.getReadOnlyProperty();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }
    public boolean isSelected() {
        return selected.get();
    }
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    @Override
    public String toString() {
        return text.get();
    }
}
