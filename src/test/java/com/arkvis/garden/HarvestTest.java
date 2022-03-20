package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HarvestTest {

    @Test
    void should_returnCorrectHarvestDate_when_sowingVegetable() {
        Bed bed = new Bed(1);

        int daysToHarvest = 36;
        bed.sow(0, new Vegetable("tomato", daysToHarvest, 16));

        Vegetable tomato = getVegetableByName("tomato", bed.getSownVegetables(0));
        assertEquals(LocalDate.now().plusDays(daysToHarvest), tomato.getHarvestDate());
    }

    @Test
    void should_returnCorrectHarvestDate_when_sowingMultipleVegetables() {
        int basilDaysToHarvest = 36;
        int cornDaysToHarvest = 45;

        Bed bed = new Bed(1);
        bed.sow(0, new Vegetable("basil", basilDaysToHarvest, 4));
        bed.sow(0, new Vegetable("corn", cornDaysToHarvest, 4));

        List<Vegetable> sownVegetables = bed.getSownVegetables(0);
        Vegetable basil = getVegetableByName("basil", sownVegetables);
        Vegetable corn = getVegetableByName("corn", sownVegetables);

        assertEquals(LocalDate.now().plusDays(basilDaysToHarvest), basil.getHarvestDate());
        assertEquals(LocalDate.now().plusDays(cornDaysToHarvest), corn.getHarvestDate());
    }

    private Vegetable getVegetableByName(String name, List<Vegetable> vegetables) {
        return vegetables.stream()
                .filter(veg -> veg.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
