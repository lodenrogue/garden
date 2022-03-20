package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SowingTest {

    @Test
    void should_returnTrue_when_checkingIfCanSowInSquareWithEnoughPoints() {
        Vegetable tomato = new Vegetable("tomato", 36, 16);
        Bed bed = new Bed(1);
        assertTrue(bed.canSow(0, tomato));
    }

    @Test
    void should_returnFalse_when_checkingIfCanSowInSquareWithNotEnoughPoints() {
        Vegetable tomato = new Vegetable("tomato", 36, 16);
        Vegetable corn = new Vegetable("tomato", 45, 4);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        assertFalse(bed.canSow(0, corn));
    }
}
