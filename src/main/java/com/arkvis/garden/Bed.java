package com.arkvis.garden;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bed {
    private final List<Square> squares;

    public Bed(int numOfSquares) {
        squares = createSquares(numOfSquares);
    }

    public void sow(int square, Vegetable vegetable) {
        sow(square, vegetable, LocalDate.now());
    }

    public void sow(int square, Vegetable vegetable, LocalDate sowingDate) {
        squares.get(square).sow(vegetable, sowingDate);
    }

    public List<Vegetable> getSownVegetables(int square) {
        return squares.get(square).getSownVegetables();
    }

    private List<Square> createSquares(int numOfSquares) {
        return IntStream.range(0, numOfSquares)
                .mapToObj(i -> new Square())
                .collect(Collectors.toList());
    }

    public boolean canSow(int square, Vegetable vegetable) {
        return squares.get(square).canSow(vegetable);
    }
}
