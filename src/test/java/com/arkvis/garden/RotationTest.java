package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RotationTest {

    @Test
    void should_returnTrue_when_checkingBedThatHasNoHistoryIsPastRotationPeriod() {
        Vegetable veg = new Vegetable("tomato", "nightshade", 16, 16);
        Bed bed = new Bed(1);
        assertTrue(bed.isPastRotationPeriodFor(veg));
    }

    @Test
    void should_returnTrue_when_checkingBedThatIsPastPeriodForGivenVegetable() {
        int rotationPeriod = 3;

        String family = "nightshade";
        Vegetable firstHarvestedTomato = new Vegetable("tomato", family, 36, 16);
        Vegetable secondHarvestedTomato = new Vegetable("tomato", family, 36, 16);

        LocalDate sowingDate = LocalDate.now().minusYears(rotationPeriod + 1);
        LocalDate harvestDate = sowingDate.plusDays(36);

        Bed bed = new Bed(2, rotationPeriod);
        bed.sow(0, firstHarvestedTomato, sowingDate);
        bed.sow(1, secondHarvestedTomato, sowingDate);

        bed.harvest(firstHarvestedTomato, harvestDate);
        bed.harvest(secondHarvestedTomato, harvestDate);

        Vegetable testVeg = new Vegetable("tomato", family, 36, 16);
        assertTrue(bed.isPastRotationPeriodFor(testVeg));
    }

    @Test
    void should_returnFalse_when_checkingBedThatIsNotPastPeriodForGivenVegetable() {
        int rotationPeriod = 3;

        String family = "nightshade";
        Vegetable firstHarvestedTomato = new Vegetable("tomato", family, 36, 16);
        Vegetable secondHarvestedTomato = new Vegetable("tomato", family, 36, 16);

        LocalDate sowingDate = LocalDate.now().minusYears(rotationPeriod - 1);
        LocalDate harvestDate = sowingDate.plusDays(36);

        Bed bed = new Bed(2, rotationPeriod);
        bed.sow(0, firstHarvestedTomato, sowingDate);
        bed.sow(1, secondHarvestedTomato, sowingDate);

        bed.harvest(firstHarvestedTomato, harvestDate);
        bed.harvest(secondHarvestedTomato, harvestDate);

        Vegetable testVeg = new Vegetable("tomato", family, 36, 16);
        assertFalse(bed.isPastRotationPeriodFor(testVeg));
    }

    @Test
    void should_returnTrue_when_checkingBedThatHasOtherHarvestedVegetablesButIsPastRotationPeriodForGivenVegetable() {
        int rotationPeriod = 3;

        Vegetable basil = new Vegetable("basil", "mint", 36, 4);
        Vegetable corn = new Vegetable("corn", "grass", 36, 4);

        LocalDate sowingDate = LocalDate.now().minusYears(rotationPeriod - 1);
        LocalDate harvestDate = sowingDate.plusDays(36);

        Bed bed = new Bed(2, rotationPeriod);
        bed.sow(0, basil, sowingDate);
        bed.sow(1, corn, sowingDate);

        bed.harvest(basil, harvestDate);
        bed.harvest(corn, harvestDate);

        Vegetable testVeg = new Vegetable("tomato", "nightshade", 36, 16);
        assertTrue(bed.isPastRotationPeriodFor(testVeg));
    }
}
