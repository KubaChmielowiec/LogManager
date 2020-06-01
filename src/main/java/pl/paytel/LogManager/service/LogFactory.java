package pl.paytel.LogManager.service;

import pl.paytel.LogManager.model.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.IllegalFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LogFactory {

    public static Log createLogFromString(String logString) {
        Log log = new Log();
        log.setDate(extractDate(logString));
        log.setLevel(extractLevel(logString));
        log.setLoggingClass(extractLoggingClass(logString));
        log.setContent(extractContent(logString));
        return log;
    }

    private static String extractContent(String logString) {
        Matcher matcher = Pattern.compile("\\s:\\s(.+)").matcher(logString);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    private static String extractLoggingClass(String logString) {
        Matcher matcher = Pattern.compile("\\s(\\w+(\\.\\w+)+|\\.?\\S+)\\s+:").matcher(logString);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    private static String extractLevel(String logString) {
        Matcher matcher = Pattern.compile("\\s[A-Z]{3,6}\\s").matcher(logString);
        if (matcher.find()) {
            return matcher.group(0).trim();
        }
        return null;
    }

    private static LocalDateTime extractDate(String logString) throws IllegalFormatException {
        Matcher matcher = Pattern.compile("\\s?\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}").matcher(logString);
        if (matcher.find()) {
            String date = matcher.group(0).trim();
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        return null;
    }
}
