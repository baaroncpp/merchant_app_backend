package com.pbu.commons.utils;

import com.pbu.commons.exceptions.UnProcessableContentException;
import com.pbu.commons.text.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 12:41 PM
 **/
public class DateTimeUtil {

    private static final Logger logger;
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_00_00_00 = "yyyy-MM-dd 00:00:00";
    public static final String FORMAT_DEFAULT = "yyyyMMddHHmmssSSS";
    public static final String YYYY_MM_DD_23_59_59 = "yyyy-MM-dd 23:59:59";
    public static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYMMDDHHMMSS = "yyMMddHHmmss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String HH_MM = "HH:mm";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String RSS_RFC822_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
    public static final String DAY = "DAY";
    public static final String SECOND = "SECOND";
    public static final String MINUTE = "MINUTE";
    public static final String HOUR = "HOUR";
    public static final String MONTH = "MONTH";
    public static final TimeZone UTC_TIMEZONE;

    static {
        UTC_TIMEZONE = TimeZone.getTimeZone("UTC");
        logger = LogManager.getLogger();
    }

    public static String dateToString(final LocalDateTime date, final String dateFormat) {
        return dateToString(date, dateFormat, null);
    }

    public static Date getCurrentUTCTime(String value) {

        final TimeZone toTimeZone = TimeZone.getTimeZone("UTC");
        final Calendar calendar = Calendar.getInstance(toTimeZone);

        return (new Date(calendar.getTimeInMillis()));

    }

    public static LocalDateTime getCurrentUTCTime() {
        //return LocalDateTime.now(ZoneOffset.UTC);
        return LocalDateTime.now(ZoneId.of("Africa/Kampala"));
    }

    public static java.sql.Date getSqlDateFromString(String string){
        final String format = "yyyy-mm-dd";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date d = null;
        try {
            d = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();

        }

        return new java.sql.Date(d.getTime());
    }



    public static LocalDateTime stringToDate(final String time, final String format) {
        Objects.requireNonNull(time, "time string cannot be null");

        if (time.isBlank()) {
            return LocalDateTime.now();
        }

        var effectivePattern = (format == null || format.isBlank())
                ? "yyyy-MM-dd HH:mm:ss"
                : format;

        var formatter = DateTimeFormatter.ofPattern(effectivePattern);
        return LocalDateTime.parse(time.trim(), formatter);
    }

    private static String dateToString(final LocalDateTime date, final String format, final String timeZoneID) {
        if (null == date || StringUtil.isEmpty(format)) {
            return "";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (!StringUtil.isEmpty(timeZoneID)) {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        }
        return sdf.format(date);
    }

    public static String localToUTCString(final String localDateStr, final String format, String timeZone) {
        Date localDate = null;
        String utcTimeStr = null;
        if (StringUtil.isEmpty(localDateStr)) {
            return "";
        }
        if (localDateStr.length() != format.length()) {
            logger.error("parse [{}] to date with pattern [{}] failed. The length is mismatch.", localDateStr, format);
            return "";
        }
        try {
            timeZone = timeZone.replaceAll("UTC", "GMT");
            final DateFormat formatter = new SimpleDateFormat(format);
            TimeZone zone = TimeZone.getTimeZone(timeZone);
            formatter.setTimeZone(zone);
            localDate = formatter.parse(localDateStr);
            final String timeZoneAString = "UTC";
            zone = TimeZone.getTimeZone(timeZoneAString);
            formatter.setTimeZone(zone);
            if (null != localDate) {
                utcTimeStr = formatter.format(localDate);
            }
        } catch (ParseException e) {
            logger.error("parse date error", e);
        }
        return utcTimeStr;
    }

    public static String utcToLocal(final String utcTimeStr, final String format, final String timeZone) {
        if (StringUtil.isEmpty(utcTimeStr)) {
            return "";
        }
        if (utcTimeStr.length() != format.length()) {
            logger.error("parse [{}] to date with pattern [{}] failed. The length is mismatch.", utcTimeStr, format);
            return "";
        }
        Date utcDate = null;
        String localDateStr = null;
        try {
            final DateFormat formatter = new SimpleDateFormat(format);
            TimeZone zone = TimeZone.getTimeZone("GMT");
            formatter.setTimeZone(zone);
            utcDate = formatter.parse(utcTimeStr);
            if (null != timeZone) {
                final String timeZoneAString = timeZone.replaceAll("UTC", "GMT");
                zone = TimeZone.getTimeZone(timeZoneAString);
                formatter.setTimeZone(zone);
            }
            if (null != utcDate) {
                localDateStr = formatter.format(utcDate);
            }
        } catch (ParseException e) {
            logger.error("parse date error", e);
        }
        return localDateStr;
    }

    public static String getCurrentLocalTime(final String dateFormat) {
        if (StringUtil.isEmpty(dateFormat)) {
            return "";
        }
        return dateToString(LocalDateTime.now(), dateFormat);
    }

    public static LocalDateTime addIntervals(final String dateString, final int interval, final String unit) {
        Objects.requireNonNull(dateString, "dateStr cannot be null");
        if (dateString.length() != 14) {
            throw new UnProcessableContentException("Date string must be exactly 14 characters (yyyyMMddHHmmss)");
        }

        var formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        var dateTime = LocalDateTime.parse(dateString, formatter);

        return switch ((unit != null ? unit.toUpperCase() : "")) {
            case "DAY"    -> dateTime.plusDays(interval);
            case "HOUR"   -> dateTime.plusHours(interval);
            case "MINUTE" -> dateTime.plusMinutes(interval);
            case "SECOND" -> dateTime.plusSeconds(interval);
            case "MONTH"  -> dateTime.plusMonths(interval);
            default       -> throw new UnProcessableContentException("Unsupported unit: " + unit
                    + ". Supported: DAY, HOUR, MINUTE, SECOND, MONTH");
        };
    }

    public static String addDays(final String date, final int day) {
        final var dateResult = addIntervals(date, day, "DAY");
        return dateToString(dateResult, "yyyyMMddHHmmss");
    }

    public static String addSeconds(final String date, final int second) {
        final var dateResult = addIntervals(date, second, "SECOND");
        return dateToString(dateResult, "yyyyMMddHHmmss");
    }

    public static String addMonths(final String date, final int month) {
        final var dateResult = addIntervals(date, month, "MONTH");
        return dateToString(dateResult, "yyyyMMddHHmmss");
    }

    public static boolean checkTimeFormat(final String time, final String format, final Boolean isRss) {
        boolean result = true;
        if (isRss) {
            if (null == stringToDate(time, "EEE, dd MMM yyyy HH:mm:ss z")) {
                result = false;
            }
        } else {
            if (null == stringToDate(time, format)) {
                result = false;
            }
            try {
                DatatypeFactory.newInstance().newXMLGregorianCalendar(time);
            } catch (Exception e) {
                result = false;
            }
        }
        return result;
    }

    public static String getCurrentTimeStamp(final String style) {
        String style2 = style;
        if (null == style2 || "".equals(style2.trim())) {
            style2 = "yyyyMMddHHmmssSSS";
        }
        final SimpleDateFormat format = new SimpleDateFormat(style2.trim());
        return format.format(Calendar.getInstance().getTime());
    }

    public static LocalDate getCurrentLocalDate(){
        return LocalDate.now();
    }

    public static LocalTime getCurrentLocalTime(){
        return LocalTime.now();
    }

    public Date addDaysToDate(final Date date, final int day) {
        final var cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

}
