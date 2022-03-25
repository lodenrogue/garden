package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RotationTest {

    @Test
    void should_returnTrue_when_checkingIfBedThatHasNoHistoryIsPastRotationPeriod() {
        Vegetable veg = new Vegetable("tomato", "nightshade", 16, 16);
        Bed bed = new Bed(1);
        assertTrue(bed.isPastRotationPeriodFor(veg));
    }
}
