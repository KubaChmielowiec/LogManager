package pl.paytel.LogManager.service;

import pl.paytel.LogManager.model.Log;

import java.time.LocalDateTime;

public class LogFactory {

    public static Log createLogFromString(String logString) {
        Log log = new Log();
        log.setDate(LocalDateTime.now());
        log.setContent("Content");
        log.setLevel("Level");
        log.setLoggingClass("Logging class");
        return log;
    }
}
