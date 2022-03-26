package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BedTest {

    @Test
    void should_returnCorrectNumberOfSquares_when_gettingSquaresFromBed() {
        int numOfSquares = 9;
        Bed bed = new Bed(numOfSquares);
        assertEquals(numOfSquares, bed.getNumberOfSquares());
    }

    @Test
    void should_returnCorrectCrops_when_gettingSownCropsFromDifferentSquares() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);
        Crop corn = new Crop("corn", "grass", 45, 4);

        Bed bed = new Bed(2);
        bed.sow(0, tomato);
        bed.sow(1, corn);

        Crop sownTomato = bed.getSownCrops(0).get(0);
        Crop sownCorn = bed.getSownCrops(1).get(0);

        assertEquals(tomato.getName(), sownTomato.getName());
        assertEquals(corn.getName(), sownCorn.getName());
    }

    @Test
    void should_throwException_when_creatingBedWithNoSquares() {
        assertThrows(IllegalArgumentException.class, () -> new Bed(0));
    }

    @Test
    void should_throwException_when_creatingBedWithNegativeNumberSquares() {
        assertThrows(IllegalArgumentException.class, () -> new Bed(-1));
    }
}
