package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SowingTest {

    @Test
    void should_returnCorrectSowingDate_when_gettingSowingDateFromVegetableSownInTheFuture() {
        LocalDate sowingDate = LocalDate.now().plusDays(10);
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato, sowingDate);
        assertEquals(sowingDate, tomato.getSowingDate());
    }

    @Test
    void should_returnCorrectSowingDate_when_gettingSowingDateFromVegetableSownNow() {
        Bed bed = new Bed(1);
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        bed.sow(0, tomato);
        assertEquals(LocalDate.now(), tomato.getSowingDate());
    }

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
