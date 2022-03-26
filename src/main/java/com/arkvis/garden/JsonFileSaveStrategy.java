package com.arkvis.garden;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileSaveStrategy implements SaveStrategy {

    private final String fileName;

    public JsonFileSaveStrategy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(Bed bed) {
        try {
            String json = new Gson().toJson(bed);
            Files.writeString(Paths.get(fileName), json);
        } catch (IOException ex) {
            String message = String.format("Error saving file with given name %s", fileName);
            throw new RuntimeException(message, ex);
        }
    }
}
