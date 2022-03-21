package com.arkvis.garden;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Square {
    private final List<Vegetable> sownVegetables;
    private int pointsAvailable = 16;

    Square() {
        sownVegetables = new ArrayList<>();
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

    void harvest(Vegetable vegetable) {
        sownVegetables.remove(vegetable);
    }
}
