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
    void should_throwException_when_creatingBedWithNoSquares() {
        assertThrows(IllegalArgumentException.class, () -> new Bed(0));
    }

    @Test
    void should_throwException_when_creatingBedWithNegativeNumberSquares() {
        assertThrows(IllegalArgumentException.class, () -> new Bed(-1));
    }

    @Test
    void should_throwException_when_creatingBedWithNegativeRotationPeriod() {
        assertThrows(IllegalArgumentException.class, () -> new Bed(1, -1));
    }
}
