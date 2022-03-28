package com.arkvis.garden;

import java.time.LocalDate;
import java.util.Objects;

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
        if (Objects.isNull(sowingDate)) {
            String message = "Crop has not been sown. Projected harvest date cannot be calculated";
            throw new IllegalStateException(message);
        }
        return sowingDate.plusDays(daysToHarvest);
    }

    public LocalDate getHarvestedDate() {
        return harvestedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crop crop = (Crop) o;
        return daysToHarvest == crop.daysToHarvest
                && size == crop.size
                && name.equals(crop.name)
                && family.equals(crop.family)
                && Objects.equals(sowingDate, crop.sowingDate)
                && Objects.equals(harvestedDate, crop.harvestedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, family, daysToHarvest, size, sowingDate, harvestedDate);
    }

    void sow(LocalDate sowingDate) {
        if (Objects.nonNull(this.sowingDate)) {
            String message = String.format("Crop was already sown on %s", sowingDate);
            throw new IllegalStateException(message);
        }
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
}
