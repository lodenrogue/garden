package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HistoryTest {

    @Test
    void should_returnEmptyHistory_when_gettingHistoryOfBedWithNoHistory() {
        Bed bed = new Bed(1);
        List<Vegetable> history = bed.getHistory(0);
        assertTrue(history.isEmpty());
    }

    @Test
    void should_returnEmptyHistory_when_gettingHistoryOfBedWithNoVegetablesHarvested() {
        Bed bed = new Bed(1);
        bed.sow(0, new Vegetable("tomato", 36, 16));

        List<Vegetable> history = bed.getHistory(0);
        assertTrue(history.isEmpty());
    }

    @Test
    void should_returnCorrectHistory_when_gettingHistoryOfBedWithSingleVegetableHarvested() {
        Vegetable tomato = new Vegetable("tomato", 36, 16);

        Bed bed = new Bed(1);
        bed.sow(0, tomato, LocalDate.now().plusDays(10));
        bed.harvest(tomato, LocalDate.now().plusDays(20));

        List<Vegetable> history = bed.getHistory(0);
        assertEquals(1, history.size());

        Vegetable historicTomato = history.get(0);
        assertHistory(tomato, historicTomato);
    }

    @Test
    void should_returnCorrectHistory_when_gettingHistoryOfBedWithMultipleVegetablesHarvested() {
        Vegetable basil = new Vegetable("basil", 36, 4);
        Vegetable corn = new Vegetable("corn", 45, 4);

        Bed bed = new Bed(1);
        bed.sow(0, basil, LocalDate.now().plusDays(10));
        bed.sow(0, corn, LocalDate.now().minusDays(15));

        bed.harvest(basil, LocalDate.now().plusDays(20));
        bed.harvest(corn, LocalDate.now().plusDays(25));

        List<Vegetable> history = bed.getHistory(0);
        assertEquals(2, history.size());

        Vegetable historicBasil = history.get(0);
        Vegetable historyCorn = history.get(1);

        assertHistory(basil, historicBasil);
        assertHistory(corn, historyCorn);
    }

    private void assertHistory(Vegetable sownVegetable, Vegetable historyVegetable) {
        assertEquals(sownVegetable.getName(), historyVegetable.getName());
        assertEquals(sownVegetable.getSowingDate(), historyVegetable.getSowingDate());
        assertEquals(sownVegetable.getHarvestedDate(), historyVegetable.getHarvestedDate());
    }
}
