package com.arkvis.garden;

import java.time.LocalDate;
import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return rotationPeriodInYears == square.rotationPeriodInYears && pointsAvailable == square.pointsAvailable && sownCrops.equals(square.sownCrops) && history.equals(square.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rotationPeriodInYears, sownCrops, history, pointsAvailable);
    }

    void sow(Crop crop, LocalDate sowingDate) {
        if (hasSpaceToSow(crop)) {
            crop.sow(sowingDate);
            pointsAvailable -= crop.getSize();
            sownCrops.add(crop);
        } else {
            String message = String.format("Not enough space to sow crop in square. %s points available.", pointsAvailable);
            throw new NotEnoughSpaceException(message);
        }
    }

    List<Crop> getSownCrops() {
        return Collections.unmodifiableList(sownCrops);
    }

    boolean hasSpaceToSow(Crop crop) {
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

    boolean isPastRotationPeriodFor(String cropFamily) {
        LocalDate rotationCutOffDate = LocalDate.now().minusYears(rotationPeriodInYears);
        return history.stream()
                .filter(crop -> crop.getFamily().equals(cropFamily))
                .allMatch(crop -> crop.wasHarvestedBefore(rotationCutOffDate));
    }

    LocalDate getRotationEndDateFor(String cropFamily) {
        return history.stream()
                .filter(crop -> crop.getFamily().equals(cropFamily))
                .map(Crop::getHarvestedDate)
                .max(Comparator.naturalOrder())
                .map(date -> date.plusYears(rotationPeriodInYears))
                .orElse(null);
    }

    void remove(Crop crop) {
        if (sownCrops.contains(crop)) {
            pointsAvailable += crop.getSize();
            sownCrops.remove(crop);
        } else {
            history.remove(crop);
        }
    }
}
