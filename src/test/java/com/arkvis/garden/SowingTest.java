package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SowingTest {

    @Test
    void should_returnCorrectSowingDate_when_gettingSowingDateFromCropSownInTheFuture() {
        LocalDate sowingDate = LocalDate.now().plusDays(10);
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato, sowingDate);
        assertEquals(sowingDate, tomato.getSowingDate());
    }

    @Test
    void should_returnCorrectSowingDate_when_gettingSowingDateFromCropInBedSownInTheFuture() {
        LocalDate sowingDate = LocalDate.now().plusDays(10);
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato, sowingDate);

        List<Crop> crops = bed.getSownCrops(0);
        assertEquals(sowingDate, crops.get(0).getSowingDate());
    }

    @Test
    void should_returnCorrectSowingDate_when_gettingSowingDateFromCropSownNow() {
        Bed bed = new Bed(1);
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);

        bed.sow(0, tomato);
        assertEquals(LocalDate.now(), tomato.getSowingDate());
    }

    @Test
    void should_returnCorrectSowingDate_when_gettingSowingDateFromCropInBedSownNow() {
        Bed bed = new Bed(1);
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);
        bed.sow(0, tomato);

        List<Crop> crops = bed.getSownCrops(0);
        assertEquals(LocalDate.now(), crops.get(0).getSowingDate());
    }

    @Test
    void should_returnTrue_when_checkingIfCanSowInSquareWithEnoughPoints() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);
        Bed bed = new Bed(1);
        assertTrue(bed.hasSpaceToSow(0, tomato));
    }

    @Test
    void should_returnFalse_when_checkingIfCanSowInSquareWithNotEnoughPoints() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);
        Crop corn = new Crop("tomato", "nightshade", 45, 4);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        assertFalse(bed.hasSpaceToSow(0, corn));
    }

    @Test
    void should_returnTrue_when_checkingIfCanSowInSquareAfterCropHasBeenHarvested() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);
        Crop kale = new Crop("kale", "cabbage", 40, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        bed.harvest(tomato);

        assertTrue(bed.hasSpaceToSow(0, kale));
    }

    @Test
    void should_throwException_when_sowingButNotEnoughPointsInSquare() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);
        Bed bed = new Bed(1);
        bed.sow(0, tomato);

        Crop testCrop = new Crop("tomato", "nightshade", 36, 16);
        assertThrows(NotEnoughSpaceException.class, () -> bed.sow(0, testCrop));
    }

    @Test
    void should_throwException_when_sowingCropThatHasAlreadyBeenSown() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);
        Bed bed = new Bed(2);

        bed.sow(0, tomato);
        assertThrows(IllegalStateException.class, () -> bed.sow(1, tomato));
    }
}
