package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HarvestTest {

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingVegetable() {
        int daysToHarvest = 36;

        Bed bed = new Bed(1);
        bed.sow(0, new Vegetable("tomato", daysToHarvest, 16));

        List<Vegetable> sownVegetables = bed.getSownVegetables(0);
        Vegetable tomato = new VegetableFinder(sownVegetables).findByName("tomato");

        assertEquals(LocalDate.now().plusDays(daysToHarvest), tomato.getProjectedHarvestDate());
    }

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingMultipleVegetables() {
        int basilDaysToHarvest = 36;
        int cornDaysToHarvest = 45;

        Bed bed = new Bed(1);
        bed.sow(0, new Vegetable("basil", basilDaysToHarvest, 4));
        bed.sow(0, new Vegetable("corn", cornDaysToHarvest, 4));

        List<Vegetable> sownVegetables = bed.getSownVegetables(0);
        VegetableFinder vegetableFinder = new VegetableFinder(sownVegetables);

        Vegetable basil = vegetableFinder.findByName("basil");
        Vegetable corn = vegetableFinder.findByName("corn");

        assertEquals(LocalDate.now().plusDays(basilDaysToHarvest), basil.getProjectedHarvestDate());
        assertEquals(LocalDate.now().plusDays(cornDaysToHarvest), corn.getProjectedHarvestDate());
    }

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingVegetableWithSowingDate() {
        LocalDate sowingDate = LocalDate.now().plusDays(20);
        int daysToHarvest = 36;

        Bed bed = new Bed(1);
        bed.sow(0, new Vegetable("tomato", daysToHarvest, 16), sowingDate);

        List<Vegetable> sownVegetables = bed.getSownVegetables(0);
        Vegetable tomato = new VegetableFinder(sownVegetables).findByName("tomato");
        assertEquals(sowingDate.plusDays(daysToHarvest), tomato.getProjectedHarvestDate());
    }

    @Test
    void should_notReturnVegetable_when_gettingSownVegetablesFromBedThatHasBeenHarvested() {
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        bed.harvest(tomato);

        List<Vegetable> sownVegetables = bed.getSownVegetables(0);
        Vegetable result = new VegetableFinder(sownVegetables).findByName("tomato");
        assertNull(result);
    }

    @Test
    void should_returnCorrectHarvestedDate_when_harvestingFromBed() {
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        bed.harvest(tomato);

        assertEquals(LocalDate.now(), tomato.getHarvestedDate());
    }

    @Test
    void should_returnCorrectHarvestedDate_when_harvestingFromBedWithGivenHarvestDate() {
        LocalDate harvestDate = LocalDate.now().minusDays(10);
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        bed.harvest(tomato, harvestDate);

        assertEquals(harvestDate, tomato.getHarvestedDate());
    }
}
