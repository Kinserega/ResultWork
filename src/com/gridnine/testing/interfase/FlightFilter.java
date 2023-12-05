package com.gridnine.testing.interfase;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightFilter {
    void AllFlight(List<Flight> flights);
    List<Flight> departureBeforeTheCurrentTime(List<Flight> flights);
    List<Flight> SegmentsWithArrivalDateBeforeDepartureDate(List<Flight> flights);
    List<Flight> FlightsWhereTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights);
}
