package com.arkvis.garden;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Square {
    private final int rotationPeriodInYears;
    private final List<Crop> sownCrops;
    private final List<Crop> history;
    private int pointsAvailable = 16;

    Square(int rotationPeriodInYears) {
        this.rotationPeriodInYears = rotationPeriodInYears;
        sownCrops = new ArrayList<>();
        history = new ArrayList<>();
    }

    void sow(Crop crop, LocalDate sowingDate) {
        if (canSow(crop)) {
            crop.sow(sowingDate);
            pointsAvailable -= crop.getSize();
            sownCrops.add(crop);
        }
    }

    List<Crop> getSownCrops() {
        return Collections.unmodifiableList(sownCrops);
    }

    boolean canSow(Crop crop) {
        return pointsAvailable - crop.getSize() >= 0;
    }

    void harvest(Crop crop, LocalDate harvestDate) {
        if (sownCrops.contains(crop)) {
            crop.harvest(harvestDate);
            pointsAvailable += crop.getSize();
            sownCrops.remove(crop);

            history.add(crop);
            history.sort(Comparator.comparing(Crop::getHarvestedDate));
        }
    }

    List<Crop> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public boolean isPastRotationPeriodFor(Crop target) {
        LocalDate rotationCutOffDate = LocalDate.now().minusYears(rotationPeriodInYears);
        return history.stream()
                .filter(crop -> crop.isSameFamilyAs(target))
                .allMatch(crop -> crop.wasHarvestedBefore(rotationCutOffDate));
    }
}
