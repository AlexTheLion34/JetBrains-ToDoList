package com.toDoList.parser;

import com.toDoList.model.Task;
import com.toDoList.model.ToDoList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ToDoListParserTest {

    private ToDoListParser parser = new ToDoListParser();

    @Test
    public void shouldReturnToDoListObject() {
        ToDoList toDoList = new ToDoList("Imported list");
        toDoList.addTask(new Task("Imported task"));

        Optional<ToDoList> expectedResult = Optional.of(toDoList);
        Optional<ToDoList> actualResult = parser.parse("/resources/test_list.json");

        actualResult.ifPresent(list -> Assert.assertEquals(expectedResult.get().getName(), list.getName()));
        actualResult.ifPresent(list -> Assert.assertEquals(expectedResult.get().getTasks().get(0).getText(),
                list.getTasks().get(0).getText()));
    }

    @Test
    public void shouldReturnEmptyObjectDueToFileDoesNotExist() {
        Optional<ToDoList> expectedResult = Optional.empty();
        Optional<ToDoList> actualResult = parser.parse("/resources/no_file.json");

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnEmptyObjectDueToEmptyFile() {
        Optional<ToDoList> expectedResult = Optional.empty();
        Optional<ToDoList> actualResult = parser.parse("/resources/test_empty.json");

        Assert.assertEquals(expectedResult, actualResult);
    }
}
