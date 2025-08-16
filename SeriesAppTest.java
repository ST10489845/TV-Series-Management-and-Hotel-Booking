import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesAppTest {

    @Test
    public void testSearchSeries() {
        SeriesApp.series.add(new SeriesApp.SeriesModel("1", "Series Name", "16", "10"));
        SeriesApp.SeriesModel result = SeriesApp.findSeriesById("1");
        assertNotNull(result);
        assertEquals("1", result.SeriesId);
        assertEquals("Series Name", result.SeriesName);
    }

    @Test
    public void testSearchSeries_SeriesNotFound() {
        SeriesApp.SeriesModel result = SeriesApp.findSeriesById("2");
        assertNull(result);
    }

    @Test
    public void testUpdateSeries() {
        SeriesApp.SeriesModel series = new SeriesApp.SeriesModel("1", "Series Name", "16", "10");
        SeriesApp.series.add(series);
        boolean result = SeriesApp.updateSeries(series, "New Name", "17", "11");
        assertTrue(result);
        assertEquals("New Name", series.SeriesName);
        assertEquals("17", series.SeriesAge);
        assertEquals("11", series.SeriesNumberOfEpisodes);
    }

    @Test
    public void testUpdateSeries_NullSeries() {
        boolean result = SeriesApp.updateSeries(null, "New Name", "17", "11");
        assertFalse(result);
    }

    @Test
    public void testDeleteSeries() {
        SeriesApp.SeriesModel series = new SeriesApp.SeriesModel("1", "Series Name", "16", "10");
        SeriesApp.series.add(series);
        boolean result = SeriesApp.deleteSeries(series);
        assertTrue(result);
        assertTrue(SeriesApp.series.isEmpty());
    }

    @Test
    public void testDeleteSeries_NullSeries() {
        boolean result = SeriesApp.deleteSeries(null);
        assertFalse(result);
    }
}
