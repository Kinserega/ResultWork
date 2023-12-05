package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.interfase.FlightFilter;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterImpl implements FlightFilter {
    private final List<Segment> segmentList = new ArrayList<>();
    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final List<Flight> temporaryListFlights = new ArrayList<>();

    /** метод для вывода информации о каждом сегменте из списка сегментов каждого рейса в списке flights
     * @param flights  список объектов типа Flight
     */
    @Override
    public void AllFlight(List<Flight> flights) {
        for (Flight flight: flights) {
            for (int i = 0; i < flight.getSegments().size(); i++) {
                System.out.println(flight.getSegments().get(i));
            }
        }
    }

    /** Этот метод фильтрует и возвращает список рейсов, у которых имеются сегменты, где время отправления раньше
     * текущего времени.
     * @param flights  список объектов типа Flight
     * @return         возвращает временный список temporaryListFlights, содержащий рейсы, у которых имеются сегменты,
     *                 где время отправления раньше текущего времени.
     */
    @Override
    public List<Flight> departureBeforeTheCurrentTime(List<Flight> flights) {
        for (Flight flight : flights) {
            segmentList.addAll(flight.getSegments());
            while (segmentList.size() > 0) {
                LocalDateTime dep = segmentList.get(0).getDepartureDate();
                LocalDateTime arr = segmentList.remove(0).getArrivalDate();
                if (dep.isBefore(currentDateTime)) {
                    showFlight(flight, dep, arr);
                    temporaryListFlights.add(flight);
                }
            }
        }
        return temporaryListFlights;
    }

    /** Этот метод фильтрует и возвращает список рейсов, у которых имеются сегменты, где дата прибытия раньше
     *  даты отправления.
     * @param flights  список объектов типа Flight
     * @return         возвращает временный список temporaryListFlights, содержащий рейсы, у которых имеются сегменты,
     *                 где дата прибытия раньше даты отправления.
     */
    @Override
    public List<Flight> SegmentsWithArrivalDateBeforeDepartureDate(List<Flight> flights) {
        for (Flight flight : flights) {
            segmentList.addAll(flight.getSegments());
            while (segmentList.size() > 0) {
                LocalDateTime dep = segmentList.get(0).getDepartureDate();
                LocalDateTime arr = segmentList.remove(0).getArrivalDate();
                if (arr.isBefore(dep)) {
                    showFlight(flight, dep, arr);
                    temporaryListFlights.add(flight);
                }
            }
        }
        return temporaryListFlights;
    }

    /**
     * Этот метод фильтрует и возвращает список рейсов, в которых общее время проведенное на земле превышает два часа
     * @param flights  список объектов типа Flight
     * @return         временный список temporaryListFlights, содержащий рейсы, в которых общее время проведенное
     *                 на земле превышает два часа
     */
    @Override
    public List<Flight> FlightsWhereTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights) {
        for (Flight flight : flights) {
            segmentList.addAll(flight.getSegments());
            if (segmentList.size() > 2) {
                while (segmentList.size() > 2) {
                    LocalDateTime arr = segmentList.remove(1).getArrivalDate();
                    LocalDateTime dep = segmentList.remove(1).getDepartureDate();
                    if (arr.plusHours(2).isBefore(dep)) {
                        showFlight(flight, dep, arr);
                        temporaryListFlights.add(flight);
                    }
                }
            }
        }
        return temporaryListFlights;
    }

    /**
     * Этот метод принимает объект типа Flight и два объекта типа LocalDateTime (dep и arr) в качестве параметров.
     * После этого он выводит на консоль информацию о рейсе, используя форматированный вывод данных.
     * @param flight  объект типа Flight
     * @param dep  -  время отправления
     * @param arr  -  время прибытия
     */
    private void showFlight (Flight flight, LocalDateTime dep, LocalDateTime arr){
        System.out.println("dep: " + dateTimeFormatter.format(dep)+ "\n"
                + "arr: " + dateTimeFormatter.format(arr) + "\n");
    }
}
