package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveCropTest {

    @Test
    void should_removeCropFromSownCrops_when_removingSownCrop() {
        Crop crop = new Crop("tomato", "nightshade", 36, 16);
        Bed bed = new Bed(1);
        bed.sow(0, crop);

        bed.remove(crop);
        List<Crop> sownCrops = bed.getSownCrops(0);
        assertTrue(sownCrops.isEmpty());
    }

    @Test
    void should_removeCropFromHistory_when_removingHarvestedCrop() {
        Crop crop = new Crop("tomato", "nightshade", 36, 16);
        Bed bed = new Bed(1);
        bed.sow(0, crop);
        bed.harvest(crop);

        bed.remove(crop);
        List<Crop> history = bed.getHistory(0);
        assertTrue(history.isEmpty());
    }

    @Test
    void should_notRemoveOtherSownCrops_when_removingSownCrop() {
        Crop basil = new Crop("basil", "mint", 36, 4);
        Crop corn = new Crop("corn", "grass", 36, 4);
        Crop carrot = new Crop("carrot", "parsley", 36, 1);

        Bed bed = new Bed(2);
        bed.sow(0, basil);
        bed.sow(0, corn);
        bed.sow(1, carrot);

        bed.remove(basil);

        assertEquals(1, bed.getSownCrops(0).size());
        assertTrue(bed.getSownCrops(0).contains(corn));

        assertEquals(1, bed.getSownCrops(1).size());
        assertTrue(bed.getSownCrops(1).contains(carrot));
    }

    @Test
    void should_notRemoveOtherCropsFromHistory_when_removingHarvestedCrop() {
        Crop basil = new Crop("basil", "mint", 36, 4);
        Crop corn = new Crop("corn", "grass", 36, 4);
        Crop carrot = new Crop("carrot", "parsley", 36, 1);

        Bed bed = new Bed(2);
        bed.sow(0, basil);
        bed.sow(0, corn);
        bed.sow(1, carrot);

        bed.harvest(basil);
        bed.harvest(corn);
        bed.harvest(carrot);

        bed.remove(basil);

        assertEquals(1, bed.getHistory(0).size());
        assertTrue(bed.getHistory(0).contains(corn));

        assertEquals(1, bed.getHistory(1).size());
        assertTrue(bed.getHistory(1).contains(carrot));
    }

    @Test
    void should_returnTrue_when_checkingIfHasSpaceToSowAfterRemovingCrop() {
        Crop crop = new Crop("tomato", "nightshade", 36, 16);
        Bed bed = new Bed(1);
        bed.sow(0, crop);

        bed.remove(crop);
        assertTrue(bed.hasSpaceToSow(0, crop));
    }
}
