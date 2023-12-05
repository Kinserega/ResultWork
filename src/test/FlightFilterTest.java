package test;

import com.gridnine.testing.interfase.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightFilterImpl;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс тестов для FlightFilter
 */
public class FlightFilterTest {
    private final List<Flight> flights = new ArrayList<>();
    private final FlightFilter flightFilter = new FlightFilterImpl();
    LocalDateTime time = LocalDateTime.now();

    @Test
    public void departureBeforeTheCurrentTimeTest() {
        Segment segment = new Segment(LocalDateTime.now().minusHours(5),LocalDateTime.now());
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        List<Flight> filteredFlights = flightFilter.departureBeforeTheCurrentTime(flights);
        assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void SegmentsWithArrivalDateBeforeDepartureDateTest() {
        Segment segment = new Segment(LocalDateTime.now(),LocalDateTime.now().minusHours(1));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        List<Flight> filteredFlights = flightFilter.SegmentsWithArrivalDateBeforeDepartureDate(flights);
        assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void FlightsWhereTotalTimeSpentOnTheGroundExceedsTwoHoursTest() {

        Segment segment1 = new Segment(time.plusHours(2), time);
        Segment segment2 = new Segment(time.plusHours(2).plusMinutes(2), time.plusHours(6));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        List<Flight> filteredFlights = flightFilter.FlightsWhereTotalTimeSpentOnTheGroundExceedsTwoHours(flights);
        assertTrue(filteredFlights.isEmpty());
    }
}
