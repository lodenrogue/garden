package com.arkvis.garden;

import java.time.LocalDate;

public class Crop {
    private final String name;
    private final String family;
    private final int daysToHarvest;
    private final int size;

    private LocalDate sowingDate;
    private LocalDate harvestedDate;

    public Crop(String name, String family, int daysToHarvest, int size) {
        this.name = name;
        this.family = family;
        this.daysToHarvest = daysToHarvest;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public LocalDate getSowingDate() {
        return sowingDate;
    }

    public LocalDate getProjectedHarvestDate() {
        if (sowingDate == null) {
            throw new IllegalStateException("Crop has not been sown. Projected harvest date cannot be calculated");
        }
        return sowingDate.plusDays(daysToHarvest);
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

    boolean wasHarvestedBefore(LocalDate rotationDate) {
        return harvestedDate.isBefore(rotationDate);
    }

    boolean isSameFamilyAs(Crop target) {
        return family.equals(target.getFamily());
    }
}
