package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BedTest {

    @Test
    void should_returnCorrectNumberOfSquares_when_gettingSquaresFromBed() {
        int numOfSquares = 9;
        Bed bed = new Bed(numOfSquares);
        assertEquals(numOfSquares, bed.getNumberOfSquares());
    }

    @Test
    void should_returnCorrectVegetables_when_gettingSownVegetablesFromDifferentSquares() {
        Vegetable tomato = new Vegetable("tomato", 36, 16);
        Vegetable corn = new Vegetable("corn", 45, 4);

        Bed bed = new Bed(2);
        bed.sow(0, tomato);
        bed.sow(1, corn);

        Vegetable sownTomato = new VegetableFinder(bed.getSownVegetables(0))
                .findByName(tomato.getName());

        Vegetable sownCorn = new VegetableFinder(bed.getSownVegetables(1))
                .findByName(corn.getName());

        assertEquals(tomato.getName(), sownTomato.getName());
        assertEquals(corn.getName(), sownCorn.getName());
    }
}
