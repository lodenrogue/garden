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
}
