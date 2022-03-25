package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HarvestTest {

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingVegetable() {
        int daysToHarvest = 36;
        LocalDate projectedHarvestDate = LocalDate.now().plusDays(daysToHarvest);

        Bed bed = new Bed(1);
        bed.sow(0, new Vegetable("tomato", "nightshade", daysToHarvest, 16));

        Vegetable tomato = bed.getSownVegetables(0).get(0);
        assertEquals(projectedHarvestDate, tomato.getProjectedHarvestDate());
    }

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingMultipleVegetables() {
        int basilDaysToHarvest = 36;
        LocalDate basilProjectedHarvestDate = LocalDate.now().plusDays(basilDaysToHarvest);

        int cornDaysToHarvest = 45;
        LocalDate cornProjectedHarvestDate = LocalDate.now().plusDays(cornDaysToHarvest);

        Bed bed = new Bed(1);
        bed.sow(0, new Vegetable("basil", "mint", basilDaysToHarvest, 4));
        bed.sow(0, new Vegetable("corn", "grass", cornDaysToHarvest, 4));

        List<Vegetable> sownVegetables = bed.getSownVegetables(0);
        VegetableFinder vegetableFinder = new VegetableFinder(sownVegetables);

        Vegetable basil = vegetableFinder.findByName("basil");
        Vegetable corn = vegetableFinder.findByName("corn");

        assertEquals(basilProjectedHarvestDate, basil.getProjectedHarvestDate());
        assertEquals(cornProjectedHarvestDate, corn.getProjectedHarvestDate());
    }

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingVegetableWithSowingDate() {
        LocalDate sowingDate = LocalDate.now().plusDays(20);
        int daysToHarvest = 36;

        Bed bed = new Bed(1);
        bed.sow(0, new Vegetable("tomato", "nightshade", daysToHarvest, 16), sowingDate);

        Vegetable tomato = bed.getSownVegetables(0).get(0);
        assertEquals(sowingDate.plusDays(daysToHarvest), tomato.getProjectedHarvestDate());
    }

    @Test
    void should_notReturnAnyVegetables_when_gettingSownVegetablesFromBedThatHasBeenFullyHarvested() {
        Vegetable tomato = new Vegetable("tomato", "nightshade", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        bed.harvest(tomato);

        List<Vegetable> sownVegetables = bed.getSownVegetables(0);
        assertTrue(sownVegetables.isEmpty());
    }

    @Test
    void should_returnCorrectHarvestedDate_when_harvestingFromBed() {
        Vegetable tomato = new Vegetable("tomato", "nightshade",36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);

        bed.harvest(tomato);
        assertEquals(LocalDate.now(), tomato.getHarvestedDate());
    }

    @Test
    void should_returnCorrectHarvestedDate_when_harvestingFromBedWithGivenHarvestDate() {
        LocalDate harvestDate = LocalDate.now().minusDays(10);
        Vegetable tomato = new Vegetable("tomato", "nightshade",36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);

        bed.harvest(tomato, harvestDate);
        assertEquals(harvestDate, tomato.getHarvestedDate());
    }
}
