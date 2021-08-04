package Database;

import java.time.*;
import java.util.*;

/**
 * This public class TimeZoneConversion handles the timezone conversions.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class TimeZoneConversion {

    /**
     * This static class converts the local date time to Eastern time and returns the time based on whether appointment
     * scheduled time are within office hours.
     * @param appointmentStartTime LocalDateTime
     * @param appointmentEndTime LocalDateTime
     * @return 1, 2
     */
    public static int estConversion(LocalDateTime appointmentStartTime, LocalDateTime appointmentEndTime) {

        ZoneId estZoneId = ZoneId.of("America/New_York");

        //Business Hours Declaration for EST
        LocalTime businessStartingTime = LocalTime.of(8, 00);
        LocalTime businessClosingTime = LocalTime.of(22, 00);
        LocalDate businessDate = appointmentStartTime.toLocalDate();
        ZonedDateTime estOfficeStartZDT = ZonedDateTime.of(businessDate, businessStartingTime, estZoneId);
        ZonedDateTime estOfficeEndZDT = ZonedDateTime.of(businessDate, businessClosingTime, estZoneId);


        ZoneId localZoneId = ZoneId.of( TimeZone.getDefault().getID());
        LocalDate localStartDate = appointmentStartTime.toLocalDate();
        LocalTime localStartTime = appointmentStartTime.toLocalTime();
        ZonedDateTime appointmentStartLocalZDT = ZonedDateTime.of(localStartDate, localStartTime, localZoneId);
        LocalDate localEndDate = appointmentEndTime.toLocalDate();
        LocalTime localEndTime = appointmentEndTime.toLocalTime();
        ZonedDateTime appointmentEndLocalZDT = ZonedDateTime.of(localEndDate, localEndTime, localZoneId);


        if (appointmentStartLocalZDT.isBefore(estOfficeStartZDT) || appointmentEndLocalZDT.isBefore(estOfficeStartZDT)
                || appointmentStartLocalZDT.isAfter(estOfficeEndZDT) || appointmentEndLocalZDT.isAfter(estOfficeEndZDT)) {
            //System.out.println("business is closed");
            return 1;
        } else {
            //System.out.println("business is open");
            return 2;
        }

    }

}
