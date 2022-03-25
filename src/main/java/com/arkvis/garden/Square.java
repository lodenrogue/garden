package com.arkvis.garden;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Square {
    private final int rotationPeriodInYears;
    private final List<Vegetable> sownVegetables;
    private final List<Vegetable> history;
    private int pointsAvailable = 16;

    Square(int rotationPeriodInYears) {
        this.rotationPeriodInYears = rotationPeriodInYears;
        sownVegetables = new ArrayList<>();
        history = new ArrayList<>();
    }

    void sow(Vegetable vegetable, LocalDate sowingDate) {
        if (canSow(vegetable)) {
            vegetable.sow(sowingDate);
            pointsAvailable -= vegetable.getSize();
            sownVegetables.add(vegetable);
        }
    }

    List<Vegetable> getSownVegetables() {
        return Collections.unmodifiableList(sownVegetables);
    }

    boolean canSow(Vegetable vegetable) {
        return pointsAvailable - vegetable.getSize() >= 0;
    }

    void harvest(Vegetable vegetable, LocalDate harvestDate) {
        if (sownVegetables.contains(vegetable)) {
            vegetable.harvest(harvestDate);
            pointsAvailable += vegetable.getSize();
            sownVegetables.remove(vegetable);

            history.add(vegetable);
            history.sort(Comparator.comparing(Vegetable::getHarvestedDate));
        }
    }

    List<Vegetable> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public boolean isPastRotationPeriodFor(Vegetable target) {
        LocalDate rotationCutOffDate = LocalDate.now().minusYears(rotationPeriodInYears);
        return history.stream()
                .filter(vegetable -> vegetable.isSameFamilyAs(target))
                .allMatch(vegetable -> vegetable.wasHarvestedBefore(rotationCutOffDate));
    }
}
