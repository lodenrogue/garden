package com.arkvis.garden;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HistoryTest {

    @Test
    void should_returnEmptyHistory_when_gettingHistoryOfBedWithNoHistory() {
        Bed bed = new Bed(1);
        List<Vegetable> history = bed.getHistory(0);
        assertTrue(history.isEmpty());
    }
}
