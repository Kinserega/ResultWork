package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.service.FlightFilterImpl;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterImpl flightFilter = new FlightFilterImpl();

        System.out.println("Все перелеты:");
        flightFilter.AllFlight(flights);

        System.out.println("Вылет до текущего момента времени:");
        flightFilter.departureBeforeTheCurrentTime(flights);

        System.out.println("Сегменты с датой прилёта раньше даты вылета:");
        flightFilter.SegmentsWithArrivalDateBeforeDepartureDate(flights);

        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа:");
        flightFilter.FlightsWhereTotalTimeSpentOnTheGroundExceedsTwoHours(flights);
    }
}