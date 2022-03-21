package com.arkvis.garden;

import java.time.LocalDate;

public class Vegetable {
    private final String name;
    private final int daysToHarvest;
    private final int size;

    private LocalDate sowingDate;
    private LocalDate harvestedDate;

    public Vegetable(String name, int daysToHarvest, int size) {
        this.name = name;
        this.daysToHarvest = daysToHarvest;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public LocalDate getProjectedHarvestDate() {
        return sowingDate.plusDays(daysToHarvest);
    }

    public LocalDate getSowingDate() {
        return sowingDate;
    }

    public LocalDate getHarvestedDate() {
        return harvestedDate;
    }

    void sow(LocalDate sowingDate) {
        this.sowingDate = sowingDate;
    }

    void harvest(LocalDate harvestDate) {
        this.harvestedDate = harvestDate;
    }

    int getSize() {
        return size;
    }
}
