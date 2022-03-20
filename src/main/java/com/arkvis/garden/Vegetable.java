package com.arkvis.garden;

import java.time.LocalDate;

public class Vegetable {
    private final String name;
    private final int daysToHarvest;
    private final int size;

    private LocalDate harvestDate;

    public Vegetable(String name, int daysToHarvest, int size) {
        this.name = name;
        this.daysToHarvest = daysToHarvest;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    void sow(LocalDate sowingDate) {
        harvestDate = sowingDate.plusDays(daysToHarvest);
    }

    int getSize() {
        return size;
    }
}
