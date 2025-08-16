package za.co.series.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import za.co.series.Series;
import za.co.series.SeriesModel;

public class SeriesTests {

    private Series series;

    @BeforeEach
    void setup() {
        series = new Series();
    }

    @Test
    void TestSearchSeries() {
        SeriesModel s = series.getById("101");
        assertNotNull(s);
        assertEquals("Extreme Sports", s.SeriesName);
    }

    @Test
    void TestSearchSeries_SeriesNotFound() {
        assertNull(series.getById("999"));
    }

    @Test
    void TestUpdateSeries() {
        assertTrue(series.updateSeriesById("101", "Extreme Sports 2", "13", "11"));
        assertEquals("Extreme Sports 2", series.getById("101").SeriesName);
    }

    @Test
    void TestDeleteSeries() {
        assertTrue(series.deleteSeriesById("103"));
        assertNull(series.getById("103"));
    }

    @Test
    void TestDeleteSeries_SeriesNotFound() {
        assertFalse(series.deleteSeriesById("999"));
    }

    @Test
    void TestSeriesAgeRestriction_AgeValid() {
        assertTrue(series.isValidAge("2"));
        assertTrue(series.isValidAge("18"));
    }

    @Test
    void TestSeriesAgeRestriction_SeriesAgeInvalid() {
        assertFalse(series.isValidAge("1"));
        assertFalse(series.isValidAge("19"));
        assertFalse(series.isValidAge("abc"));
    }
}
