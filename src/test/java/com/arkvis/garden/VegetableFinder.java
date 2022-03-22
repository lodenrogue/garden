package com.arkvis.garden;

import java.util.List;

class VegetableFinder {

    private final List<Vegetable> vegetables;

    VegetableFinder(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    Vegetable findByName(String name) {
        return vegetables.stream()
                .filter(veg -> veg.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
