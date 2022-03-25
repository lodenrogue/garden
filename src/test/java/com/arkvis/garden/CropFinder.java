package com.arkvis.garden;

import java.util.List;

class CropFinder {

    private final List<Crop> crops;

    CropFinder(List<Crop> crops) {
        this.crops = crops;
    }

    Crop findByName(String name) {
        return crops.stream()
                .filter(veg -> veg.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
