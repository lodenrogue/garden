package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HarvestTest {

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingCrop() {
        int daysToHarvest = 36;
        LocalDate projectedHarvestDate = LocalDate.now().plusDays(daysToHarvest);

        Bed bed = new Bed(1);
        bed.sow(0, new Crop("tomato", "nightshade", daysToHarvest, 16));

        Crop tomato = bed.getSownCrops(0).get(0);
        assertEquals(projectedHarvestDate, tomato.getProjectedHarvestDate());
    }

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingMultipleCrops() {
        int basilDaysToHarvest = 36;
        LocalDate basilProjectedHarvestDate = LocalDate.now().plusDays(basilDaysToHarvest);

        int cornDaysToHarvest = 45;
        LocalDate cornProjectedHarvestDate = LocalDate.now().plusDays(cornDaysToHarvest);

        Bed bed = new Bed(1);
        bed.sow(0, new Crop("basil", "mint", basilDaysToHarvest, 4));
        bed.sow(0, new Crop("corn", "grass", cornDaysToHarvest, 4));

        List<Crop> sownCrops = bed.getSownCrops(0);
        CropFinder cropFinder = new CropFinder(sownCrops);

        Crop basil = cropFinder.findByName("basil");
        Crop corn = cropFinder.findByName("corn");

        assertEquals(basilProjectedHarvestDate, basil.getProjectedHarvestDate());
        assertEquals(cornProjectedHarvestDate, corn.getProjectedHarvestDate());
    }

    @Test
    void should_returnCorrectProjectedHarvestDate_when_sowingCropWithSowingDate() {
        LocalDate sowingDate = LocalDate.now().plusDays(20);
        int daysToHarvest = 36;

        Bed bed = new Bed(1);
        bed.sow(0, new Crop("tomato", "nightshade", daysToHarvest, 16), sowingDate);

        Crop tomato = bed.getSownCrops(0).get(0);
        assertEquals(sowingDate.plusDays(daysToHarvest), tomato.getProjectedHarvestDate());
    }

    @Test
    void should_notReturnAnyCrops_when_gettingSownCropsFromBedThatHasBeenFullyHarvested() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);
        bed.harvest(tomato);

        List<Crop> sownCrops = bed.getSownCrops(0);
        assertTrue(sownCrops.isEmpty());
    }

    @Test
    void should_returnCorrectHarvestedDate_when_harvestingFromBed() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);

        bed.harvest(tomato);
        assertEquals(LocalDate.now(), tomato.getHarvestedDate());
    }

    @Test
    void should_returnCorrectHarvestedDate_when_harvestingFromBedWithGivenHarvestDate() {
        LocalDate harvestDate = LocalDate.now().minusDays(10);
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato);

        bed.harvest(tomato, harvestDate);
        assertEquals(harvestDate, tomato.getHarvestedDate());
    }

    @Test
    void should_throwException_when_gettingProjectedHarvestDateOfCropThatHasNotBeenSown() {
        Crop crop = new Crop("tomato", "nightshade", 36, 16);
        assertThrows(IllegalStateException.class, crop::getProjectedHarvestDate);
    }

    @Test
    void should_throwException_when_harvestingCropThatHasNotBeenSown() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);
        Bed bed = new Bed(1);
        assertThrows(IllegalStateException.class, () -> bed.harvest(tomato));
    }
}
