package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RotationTest {

    @Test
    void should_returnTrue_when_checkingBedThatHasNoHistoryIsPastRotationPeriod() {
        String family = "nightshade";
        Bed bed = new Bed(1);
        assertTrue(bed.isPastRotationPeriodFor(family));
    }

    @Test
    void should_returnTrue_when_checkingBedThatIsPastPeriodForGivenCropFamily() {
        int rotationPeriod = 3;

        String family = "nightshade";
        Crop firstHarvestedTomato = new Crop("tomato", family, 36, 16);
        Crop secondHarvestedTomato = new Crop("tomato", family, 36, 16);

        LocalDate sowingDate = LocalDate.now().minusYears(rotationPeriod + 1);
        LocalDate harvestDate = sowingDate.plusDays(36);

        Bed bed = new Bed(2, rotationPeriod);
        bed.sow(0, firstHarvestedTomato, sowingDate);
        bed.sow(1, secondHarvestedTomato, sowingDate);

        bed.harvest(firstHarvestedTomato, harvestDate);
        bed.harvest(secondHarvestedTomato, harvestDate);

        assertTrue(bed.isPastRotationPeriodFor(family));
    }

    @Test
    void should_returnFalse_when_checkingBedThatIsNotPastPeriodForGivenCropFamily() {
        int rotationPeriod = 3;

        String family = "nightshade";
        Crop firstHarvestedTomato = new Crop("tomato", family, 36, 16);
        Crop secondHarvestedTomato = new Crop("tomato", family, 36, 16);

        LocalDate sowingDate = LocalDate.now().minusYears(rotationPeriod - 1);
        LocalDate harvestDate = sowingDate.plusDays(36);

        Bed bed = new Bed(2, rotationPeriod);
        bed.sow(0, firstHarvestedTomato, sowingDate);
        bed.sow(1, secondHarvestedTomato, sowingDate);

        bed.harvest(firstHarvestedTomato, harvestDate);
        bed.harvest(secondHarvestedTomato, harvestDate);

        assertFalse(bed.isPastRotationPeriodFor(family));
    }

    @Test
    void should_returnTrue_when_checkingBedThatHasOtherHarvestedCropFamiliesAndIsPastRotationPeriodForGivenCropFamily() {
        int rotationPeriod = 3;

        Crop basil = new Crop("basil", "mint", 36, 4);
        Crop corn = new Crop("corn", "grass", 36, 4);

        LocalDate sowingDate = LocalDate.now().minusYears(rotationPeriod - 1);
        LocalDate harvestDate = sowingDate.plusDays(36);

        Bed bed = new Bed(2, rotationPeriod);
        bed.sow(0, basil, sowingDate);
        bed.sow(1, corn, sowingDate);

        bed.harvest(basil, harvestDate);
        bed.harvest(corn, harvestDate);

        assertTrue(bed.isPastRotationPeriodFor("nightshade"));
    }

    @Test
    void should_returnNoDate_when_gettingRotationEndDateInBedThatHasNoHistory() {
        Bed bed = new Bed(1);
        assertNull(bed.getRotationEndDateFor("nightshade"));
    }

    @Test
    void should_returnCorrectDate_when_gettingRotationEndDateInBedThatHasHistoryForCropFamilyInSingleSquare() {
        String family = "mint";
        Crop firstBasil = new Crop("basil", family, 36, 4);
        Crop secondBasil = new Crop("basil", family, 36, 4);

        LocalDate sowingDate = LocalDate.now().minusYears(5);
        LocalDate harvestDate = sowingDate.plusDays(36);

        int rotationPeriod = 4;
        Bed bed = new Bed(1, rotationPeriod);

        bed.sow(0, firstBasil, sowingDate);
        bed.sow(0, secondBasil, sowingDate);

        bed.harvest(firstBasil, harvestDate.minusDays(10));
        bed.harvest(secondBasil, harvestDate);

        LocalDate rotationEndDate = bed.getRotationEndDateFor(family);
        LocalDate expectedDate = harvestDate.plusYears(rotationPeriod);

        assertEquals(expectedDate, rotationEndDate);
    }

    @Test
    void should_returnCorrectDate_when_gettingRotationEndDateInBedThatHasHistoryForCropFamilyInMultipleSquares() {
        String family = "mint";
        Crop firstBasil = new Crop("basil", family, 36, 4);
        Crop secondBasil = new Crop("basil", family, 36, 4);

        LocalDate sowingDate = LocalDate.now().minusYears(5);
        LocalDate harvestDate = sowingDate.plusDays(36);

        int rotationPeriod = 4;
        Bed bed = new Bed(2, rotationPeriod);

        bed.sow(0, firstBasil, sowingDate);
        bed.sow(1, secondBasil, sowingDate);

        bed.harvest(firstBasil, harvestDate.minusDays(10));
        bed.harvest(secondBasil, harvestDate);

        LocalDate rotationEndDate = bed.getRotationEndDateFor(family);
        LocalDate expectedDate = harvestDate.plusYears(rotationPeriod);

        assertEquals(expectedDate, rotationEndDate);
    }
}
