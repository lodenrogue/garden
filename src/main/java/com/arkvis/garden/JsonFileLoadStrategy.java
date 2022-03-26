package com.arkvis.garden;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileLoadStrategy implements LoadStrategy {

    private final String fileName;

    public JsonFileLoadStrategy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Bed load() {
        String json = getJson();
        return new Gson().fromJson(json, Bed.class);
    }

    private String getJson() {
        try {
            return Files.readString(Paths.get(fileName));
        } catch (IOException ex) {
            String message = String.format("Unable to load file with given name %s", fileName);
            throw new RuntimeException(message, ex);
        }
    }
}
