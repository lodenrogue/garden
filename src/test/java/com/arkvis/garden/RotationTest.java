package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RotationTest {

    @Test
    void should_returnTrue_when_checkingBedThatHasNoHistoryIsPastRotationPeriod() {
        Crop crop = new Crop("tomato", "nightshade", 16, 16);
        Bed bed = new Bed(1);
        assertTrue(bed.isPastRotationPeriodFor(crop));
    }

    @Test
    void should_returnTrue_when_checkingBedThatIsPastPeriodForGivenCrop() {
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

        Crop testCrop = new Crop("tomato", family, 36, 16);
        assertTrue(bed.isPastRotationPeriodFor(testCrop));
    }

    @Test
    void should_returnFalse_when_checkingBedThatIsNotPastPeriodForGivenCrop() {
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

        Crop testCrop = new Crop("tomato", family, 36, 16);
        assertFalse(bed.isPastRotationPeriodFor(testCrop));
    }

    @Test
    void should_returnTrue_when_checkingBedThatHasOtherHarvestedCropsButIsPastRotationPeriodForGivenCrop() {
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

        Crop testCrop = new Crop("tomato", "nightshade", 36, 16);
        assertTrue(bed.isPastRotationPeriodFor(testCrop));
    }
}
