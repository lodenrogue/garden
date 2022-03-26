package com.arkvis.garden;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SaveLoadTest {
    private static final Logger logger = LoggerFactory.getLogger(SaveLoadTest.class);
    private static final String FILE_NAME = "bed.json";

    @BeforeEach
    void setUp() {
        deleteTestFile();
    }

    @AfterEach
    void tearDown() {
        deleteTestFile();
    }

    @Test
    void should_loadCorrectBed_when_havingSavedBed() {
        Crop harvestedCrop = new Crop("tomato", "nightshade", 36, 16);

        Bed savedBed = new Bed(2);
        savedBed.sow(0, harvestedCrop);
        savedBed.sow(1, new Crop("basil", "mint", 36, 4));
        savedBed.sow(1, new Crop("corn", "grass", 36, 4));

        savedBed.harvest(harvestedCrop);
        savedBed.sow(0, new Crop("potato", "nightshade", 36, 16));

        new JsonFileSaveStrategy(FILE_NAME).save(savedBed);
        Bed loadedBed = new JsonFileLoadStrategy(FILE_NAME).load();

        assertEquals(savedBed, loadedBed);
    }

    @Test
    void should_throwException_when_loadingBedFromFileThatDoesNotExist() {
        assertThrows(RuntimeException.class, () -> new JsonFileLoadStrategy(FILE_NAME).load());
    }

    private void deleteTestFile() {
        try {
            Files.delete(Paths.get(FILE_NAME));
        } catch (IOException ex) {
            logger.info(() -> "Test file did not exist so it was not deleted");
        }
    }
}
