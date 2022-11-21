package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckOut {

    public static final int ONE_HOUR_IN_MINUTES = 60;
    public static final int TWENTY_FOR_HOUR_IN_MINUTES = ONE_HOUR_IN_MINUTES * 24;
    public static final Double ONE_HOUR_VALUE = 5d;
    public static final Double ADDITIONAL_PER_HOUR_VALUE = 2d;
    public static final Double DAY_VALUE = 20d;

    public static Double getBill(Parking parking) {
        return getBill(parking.getEntryDate(), parking.getExitDate());
    }

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        double bill = 0d;
        double minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);
        //less or equals then one hour
        if (minutes <= ONE_HOUR_IN_MINUTES) return ONE_HOUR_VALUE;
        //between more than one hour and less or equals than 24 hours
        if (minutes <= TWENTY_FOR_HOUR_IN_MINUTES) {
            double additionalHours = (minutes-60)/60;
            return ONE_HOUR_VALUE + (additionalHours * ADDITIONAL_PER_HOUR_VALUE);
        }
        //more than 24 hours
        double days = entryDate.until(exitDate, ChronoUnit.DAYS);
        return days * DAY_VALUE;
    }
}
