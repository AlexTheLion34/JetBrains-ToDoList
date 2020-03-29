package com.toDoList.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toDoList.model.ToDoList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Parser for list from json files
 * Ignores files which does not exist and empty files
 * @author Aleksey Petrenko
 */
public class ToDoListParser {

    public Optional<ToDoList> parse(String pathToFile) {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(pathToFile);
        try {
            // check if file exists and is not empty
            if (path.toFile().exists() && path.toFile().length() != 0)
                return Optional.of(mapper.readValue(path.toFile(), ToDoList.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
