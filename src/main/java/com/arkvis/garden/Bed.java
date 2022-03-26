package com.arkvis.garden;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bed {
    public static final int DEFAULT_ROTATION_PERIOD_IN_YEARS = 3;
    private final int rotationPeriodInYears;
    private final List<Square> squares;

    public Bed(int numOfSquares) {
        this(numOfSquares, DEFAULT_ROTATION_PERIOD_IN_YEARS);
    }

    public Bed(int numOfSquares, int rotationPeriodInYears) {
        this.rotationPeriodInYears = rotationPeriodInYears;
        squares = createSquares(numOfSquares);
    }

    public void sow(int square, Crop crop) {
        sow(square, crop, LocalDate.now());
    }

    public void sow(int square, Crop crop, LocalDate sowingDate) {
        squares.get(square).sow(crop, sowingDate);
    }

    public List<Crop> getSownCrops(int square) {
        return squares.get(square).getSownCrops();
    }

    public List<Crop> getHistory(int square) {
        return squares.get(square).getHistory();
    }

    public void harvest(Crop crop) {
        harvest(crop, LocalDate.now());
    }

    public void harvest(Crop crop, LocalDate harvestDate) {
        if (Objects.isNull(crop.getSowingDate())) {
            throw new IllegalStateException("Attempted to harvest crop that has not been sown");
        }
        squares.forEach(square -> square.harvest(crop, harvestDate));
    }

    public boolean hasSpaceToSow(int square, Crop crop) {
        return squares.get(square).hasSpaceToSow(crop);
    }

    public int getNumberOfSquares() {
        return squares.size();
    }

    public boolean isPastRotationPeriodFor(Crop crop) {
        return squares.stream()
                .allMatch(square -> square.isPastRotationPeriodFor(crop));
    }

    public LocalDate getRotationEndDateFor(Crop crop) {
        return squares.stream()
                .map(square -> square.getRotationEndDateFor(crop))
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    private List<Square> createSquares(int numOfSquares) {
        return IntStream.range(0, numOfSquares)
                .mapToObj(i -> new Square(rotationPeriodInYears))
                .collect(Collectors.toList());
    }
}
