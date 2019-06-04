package no.statkart.wsclient;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateHjelper {

    public static XMLGregorianCalendar fromLocalDate(LocalDate localDate) {
        if (localDate == null) return null;

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMLGregorianCalendar fromLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDateTime.toString());
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static LocalDateTime dateTimeFromXMLGregorianCalendar(XMLGregorianCalendar timestamp) {
        if (timestamp == null) return null;

        return timestamp.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }

    public static LocalDate dateFromXMLGregorianCalendar(XMLGregorianCalendar timestamp) {
        if (timestamp == null) return null;

        return timestamp.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }

    public static XMLGregorianCalendar xmlGregorianCalendarForCurrentSnapshotVersion() {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(9999, 1, 1, 0, 0, 0, 0, 60);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Kunne ikke opprette XML Calendar", e);
        }
    }

}
