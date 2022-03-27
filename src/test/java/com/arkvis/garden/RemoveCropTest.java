package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.util.List;

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
    void should_returnTrue_when_checkingIfHasSpaceToSowAfterRemovingCrop() {
        Crop crop = new Crop("tomato", "nightshade", 36, 16);
        Bed bed = new Bed(1);
        bed.sow(0, crop);

        bed.remove(crop);
        assertTrue(bed.hasSpaceToSow(0, crop));
    }
}
