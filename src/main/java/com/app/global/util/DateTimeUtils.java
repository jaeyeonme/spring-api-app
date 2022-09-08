package com.app.global.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Date -> LocalDateTime 변경
 */
public class DateTimeUtils {

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
