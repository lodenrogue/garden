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

        Vegetable sownTomato = bed.getSownVegetables(0).get(0);
        Vegetable sownCorn = bed.getSownVegetables(1).get(0);

        assertEquals(tomato.getName(), sownTomato.getName());
        assertEquals(corn.getName(), sownCorn.getName());
    }
}
