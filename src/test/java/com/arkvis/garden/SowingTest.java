package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

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
    void should_returnCorrectSowingDate_when_gettingSowingDateFromVegetableInBedSownInTheFuture() {
        LocalDate sowingDate = LocalDate.now().plusDays(10);
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato, sowingDate);

        List<Vegetable> vegetables = bed.getSownVegetables(0);
        assertEquals(sowingDate, vegetables.get(0).getSowingDate());
    }

    @Test
    void should_returnCorrectSowingDate_when_gettingSowingDateFromVegetableSownNow() {
        Bed bed = new Bed(1);
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        bed.sow(0, tomato);
        assertEquals(LocalDate.now(), tomato.getSowingDate());
    }

    @Test
    void should_returnCorrectSowingDate_when_gettingSowingDateFromVegetableInBedSownNow() {
        Bed bed = new Bed(1);
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        bed.sow(0, tomato);

        List<Vegetable> vegetables = bed.getSownVegetables(0);
        assertEquals(LocalDate.now(), vegetables.get(0).getSowingDate());
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

    @Test
    void should_returnTrue_when_checkingIfCanSowInSquareAfterVegetableHasBeenHarvested() {
        Vegetable tomato = new Vegetable("tomato", 36, 16);
        Vegetable kale = new Vegetable("kale", 40, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        bed.harvest(tomato);

        assertTrue(bed.canSow(0, kale));
    }
}
