package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HistoryTest {

    @Test
    void should_returnEmptyHistory_when_gettingHistoryOfSquareWithNoHistory() {
        Bed bed = new Bed(1);
        List<Crop> history = bed.getHistory(0);
        assertTrue(history.isEmpty());
    }

    @Test
    void should_returnEmptyHistory_when_gettingHistoryOfSquareWithNoCropsHarvested() {
        Bed bed = new Bed(1);
        bed.sow(0, new Crop("tomato", "nightshade", 36, 16));

        List<Crop> history = bed.getHistory(0);
        assertTrue(history.isEmpty());
    }

    @Test
    void should_returnCorrectHistory_when_gettingHistoryOfSquareWithSingleCropHarvested() {
        Crop tomato = new Crop("tomato", "nightshade", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato, LocalDate.now().plusDays(10));
        bed.harvest(tomato, LocalDate.now().plusDays(20));

        List<Crop> history = bed.getHistory(0);
        assertEquals(1, history.size());

        Crop historicTomato = history.get(0);
        assertHistory(tomato, historicTomato);
    }

    @Test
    void should_returnCorrectHistory_when_gettingHistoryOfSquareWithMultipleCropsHarvested() {
        Crop basil = new Crop("basil", "mint", 36, 4);
        Crop corn = new Crop("corn", "grass", 45, 4);

        Bed bed = new Bed(1);
        bed.sow(0, basil, LocalDate.now().plusDays(10));
        bed.sow(0, corn, LocalDate.now().minusDays(15));

        bed.harvest(basil);
        bed.harvest(corn);

        List<Crop> history = bed.getHistory(0);
        assertEquals(2, history.size());

        Crop historicBasil = history.get(0);
        Crop historyCorn = history.get(1);

        assertHistory(basil, historicBasil);
        assertHistory(corn, historyCorn);
    }

    @Test
    void should_returnCorrectHistory_when_gettingHistoryOfMultipleSquares() {
        Crop basil = new Crop("basil", "mint", 36, 4);
        Crop corn = new Crop("corn", "grass", 45, 4);

        Bed bed = new Bed(2);
        bed.sow(0, basil, LocalDate.now().plusDays(10));
        bed.sow(1, corn, LocalDate.now().minusDays(15));

        bed.harvest(basil);
        bed.harvest(corn);

        Crop historicBasil = bed.getHistory(0).get(0);
        Crop historyCorn = bed.getHistory(1).get(0);

        assertHistory(basil, historicBasil);
        assertHistory(corn, historyCorn);
    }

    @Test
    void should_returnHistorySortedByHarvestDate_when_gettingHistory() {
        Crop newestCrop = new Crop("New", "New", 16, 1);
        Crop oldestCrop = new Crop("Old", "Old", 16, 1);

        Bed bed = new Bed(1);
        bed.sow(0, newestCrop);
        bed.sow(0, oldestCrop);

        bed.harvest(newestCrop, LocalDate.now().plusDays(100));
        bed.harvest(oldestCrop, LocalDate.now().plusDays(10));

        List<Crop> history = bed.getHistory(0);
        assertHistory(oldestCrop, history.get(0));
        assertHistory(newestCrop, history.get(1));
    }

    private void assertHistory(Crop sownCrop, Crop historyCrop) {
        assertEquals(sownCrop.getName(), historyCrop.getName());
        assertEquals(sownCrop.getSowingDate(), historyCrop.getSowingDate());
        assertEquals(sownCrop.getHarvestedDate(), historyCrop.getHarvestedDate());
    }
}
